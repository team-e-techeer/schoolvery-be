package net.schoolvery.schoolveryserver.domain.chat.service;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import java.util.UUID;

public interface RoomService {
    default Room dtoToEntity (RoomCreateRequestDto dto) {
        Room entity = Room.builder()
                .name(dto.getName())
                .post_id(dto.getPost_id())
                .build();
        return entity;
    }
    default RoomResponseDto entityToDto (Room entity) {
        RoomResponseDto dto = RoomResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .post_id(entity.getPost_id())
                .build();
        return dto;
    }
    default Room updateDtoToEntity (RoomUpdateRequestDto dto) {
        Room entity = Room.builder()
                .name(dto.getName())
                .post_id(dto.getPost_id())
                .build();
        return entity;
    }
    RoomResponseDto createChatRoom(RoomCreateRequestDto dto);
    void deleteChatRoom(UUID room_id);
    RoomResponseDto updateChatRoom(UUID room_id, RoomUpdateRequestDto dto);
    PageResultDto<RoomResponseDto, Room> getRooms(PageRequestDto requestDto);

    RoomResponseDto getByPostId(Long id);
}
