package net.schoolvery.schoolveryserver.domain.chat.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.domain.chat.repository.RoomRepository;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public RoomResponseDto createChatRoom(RoomCreateRequestDto dto){
        Room entity = dtoToEntity(dto);
        roomRepository.save(entity);
        return entityToDto(roomRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteChatRoom(UUID room_id){
        roomRepository.deleteByRoomId(room_id);
    }

    @Override
    @Transactional
    public RoomResponseDto updateChatRoom(UUID room_id, RoomUpdateRequestDto dto) {
        Room entity = updateDtoToEntity(dto);
        roomRepository.updateRoomInfo(room_id,entity.getName(),dto.getPost_id());
        entity = roomRepository.getByRoomId(room_id);
        return entityToDto(entity);
    }

    @Override
    public PageResultDto<RoomResponseDto, Room> getRooms(PageRequestDto requestDto){
        Pageable pageable = requestDto.getPageable(Sort.by("id"));
        Page<Room> result = roomRepository.findAll(pageable);
        Function<Room, RoomResponseDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

}
