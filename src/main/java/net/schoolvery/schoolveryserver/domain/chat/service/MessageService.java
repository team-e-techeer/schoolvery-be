package net.schoolvery.schoolveryserver.domain.chat.service;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    void sendMessage(MessageCreateRequestDto messageCreateRequestDto);
    List<Message> getMessages(UUID room_id);
}
