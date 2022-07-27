package net.schoolvery.schoolveryserver.domain.chat.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageFindRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.ChatMessageResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.domain.chat.exception.ChatException;
import net.schoolvery.schoolveryserver.domain.chat.repository.MessageRepository;
import net.schoolvery.schoolveryserver.domain.chat.repository.RoomRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    private Room findRoom(UUID room_id){
        try {
            Room r =  roomRepository.getByRoomId(room_id);
            return r;
        }
        catch(ChatException e){
            System.out.println("오호 예외가 발생했지요~?");
            System.out.println(e.RoomNotFoundException());
            e.RoomNotFoundException();
        }
        return null;
    }

    @Override
    public List<Message> getMessages(UUID room_id){
        Room r = findRoom(room_id);
        return r.getMessage();
    }

    @Override
    public JSONObject findMessageV1(UUID id) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        List<ChatMessageResponseDto> dtos = Stream.of(messageRepository.findByRoomId(id))
                .findAny()
                .orElseThrow(ChatException::new)
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());

        for (int i = 0; i < dtos.size(); i++) {
            JSONObject sObject = new JSONObject();
            sObject.put("member_id", dtos.get(i).getUser_id());
            sObject.put("message", dtos.get(i).getMessage());
            sObject.put("regDate", dtos.get(i).getRegDate());

            arr.add(sObject);

        }

        obj.put("room_id", id);
        obj.put("room_messages", arr);

        return obj;

    }

    @Override
    public JSONObject findMessageV2(UUID id, MessageFindRequestDto dto) {
        JSONObject obj = new JSONObject();
        JSONArray all_messages = new JSONArray();
        JSONArray user_messages = new JSONArray();

        List<ChatMessageResponseDto> dtos = Stream.of(messageRepository.findByRoomId(id))
                .findAny()
                .orElseThrow(ChatException::new)
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());

        for (int i = 0; i < dtos.size(); i++) {
            JSONObject sObject = new JSONObject();
            sObject.put("member_id", dtos.get(i).getUser_id());
            sObject.put("message", dtos.get(i).getMessage());
            sObject.put("regDate", dtos.get(i).getRegDate());

            all_messages.add(sObject);

        }

        List<ChatMessageResponseDto> users = Stream.of(messageRepository.findByUserId(dto.getUser_id()))
                .findAny()
                .orElseThrow(ChatException::new)
                .stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());

        for (int i = 0; i < users.size(); i++) {
            JSONObject sObject = new JSONObject();
            sObject.put("member_id", dtos.get(i).getUser_id());
            sObject.put("message", dtos.get(i).getMessage());
            sObject.put("regDate", dtos.get(i).getRegDate());

            user_messages.add(sObject);

        }

        obj.put("room_id", id);
        obj.put("room_messages", all_messages);
        obj.put("my_messages", user_messages);

        return obj;

    }

    @Override
    @Transactional
    public void sendMessage(MessageCreateRequestDto dto){
        log.info("SEND MESSAGE");
        UUID room_id = dto.getRoom_id();
        Room r = findRoom(room_id);
        Message message = Message.builder()
                .room(r)
                .userId(dto.getUser_id())
                .message(dto.getMessage())
                .build();
        messageRepository.save(message);
        r.getMessage().add(message);
        //template.convertAndSend("/topic/chat/room/"+ message.getRoom_id(), message);
    }
}
