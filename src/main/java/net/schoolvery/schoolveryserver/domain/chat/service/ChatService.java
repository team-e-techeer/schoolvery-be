package net.schoolvery.schoolveryserver.domain.chat.service;

import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.ChatResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
public interface ChatService {
    default Room dtoToEntity (RoomCreateRequestDto dto) {
        Room entity = Room.builder()
                .name(dto.getName())
                .post_id(dto.getPost_id())
                .build();
        return entity;
    }
    default ChatResponseDto entityToDto (Room entity) {
        ChatResponseDto dto = ChatResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .post_id(entity.getPost_id())
                .build();
        return dto;
    }

    ChatResponseDto createChatRoom(RoomCreateRequestDto dto);
}
