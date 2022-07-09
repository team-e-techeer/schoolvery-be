package net.schoolvery.schoolveryserver.domain.chat.service;
import net.minidev.json.JSONObject;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.ChatMessageResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    void sendMessage(MessageCreateRequestDto messageCreateRequestDto);
    List<Message> getMessages(UUID room_id);

    JSONObject findMessageV1(UUID id);

    default ChatMessageResponseDto EntityToDto(Message message) {
        ChatMessageResponseDto dto = ChatMessageResponseDto.builder()
                .room_id(message.getRoom().getId())
                .member_id(message.getMemberId())
                .message(message.getMessage())
                .regDate(message.getRegDate().toString())
                .build();

        return dto;
    }
}
