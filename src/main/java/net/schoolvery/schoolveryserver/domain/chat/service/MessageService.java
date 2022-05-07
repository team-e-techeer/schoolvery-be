package net.schoolvery.schoolveryserver.domain.chat.service;

import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.MessageResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    default Message dtoToEntity (MessageCreateRequestDto dto) {
        Message entity = Message.builder()
                .room_id(dto.getRoom_id())
                .user_id(dto.getUser_id())
                .message(dto.getMessage())
                .build();
        return entity;
    }
    default MessageResponseDto entityToDto (Message entity) {
        MessageResponseDto dto = MessageResponseDto.builder()
                .id(entity.getId())
                .room_id(entity.getRoom_id())
                .user_id(entity.getUser_id())
                .message((entity.getMessage()))
                .build();
        return dto;
    }

    void sendMessage(MessageCreateRequestDto messageCreateRequestDto);
    List<MessageResponseDto> getMessages(UUID room_id);
}
