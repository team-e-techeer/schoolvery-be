package net.schoolvery.schoolveryserver.domain.chat.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
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
    @Transactional
    public void sendMessage(MessageCreateRequestDto dto){
        log.info("SEND MESSAGE");
        UUID room_id = dto.getRoom_id();
        Room r = findRoom(room_id);
        Message message = Message.builder()
                .room(r)
                .member_id(dto.getMember_id())
                .message(dto.getMessage())
                .build();
        messageRepository.save(message);
        r.getMessage().add(message);
        //template.convertAndSend("/topic/chat/room/"+ message.getRoom_id(), message);
    }
}
