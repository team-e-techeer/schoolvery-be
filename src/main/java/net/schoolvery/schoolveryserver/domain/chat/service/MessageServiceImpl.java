package net.schoolvery.schoolveryserver.domain.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import net.schoolvery.schoolveryserver.domain.chat.repository.MessageRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final MessageRepository messageRepository;

    @Override
    public void createMessage(MessageCreateRequestDto dto){
        Message entity = dtoToEntity(dto);
        entityToDto(messageRepository.save(entity));
    }

    //    @MessageMapping(value = "/chat/enter")
//    public void enter(ChatMessageDto message){
//        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//
//    @MessageMapping(value = "/chat/message")
//    public void message(ChatMessageDto message){
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
}
