package net.schoolvery.schoolveryserver.domain.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageGetRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.MessageResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import net.schoolvery.schoolveryserver.domain.chat.repository.MessageRepository;
import net.schoolvery.schoolveryserver.domain.chat.repository.RoomRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<MessageResponseDto> getMessages(MessageGetRequestDto messageGetRequestDto){
        Message entity = getDtoToEntity(messageGetRequestDto);
        List <Message> dto =  messageRepository.findAllByRoomId(entity.getRoom_id());
        List <MessageResponseDto> result = new ArrayList<>();
        for (Message mm : dto) {
            result.add(entityToDto(mm));
            log.info(mm);
        }
        return result;
    }

    @Override
    public void sendMessage(MessageCreateRequestDto dto){
        Message message = dtoToEntity(dto);
        messageRepository.save(message);
        //template.convertAndSend("/topic/chat/room/"+ message.getRoom_id(), message);
    }
}
