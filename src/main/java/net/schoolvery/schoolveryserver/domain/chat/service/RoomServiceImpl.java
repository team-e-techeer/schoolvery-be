package net.schoolvery.schoolveryserver.domain.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.domain.chat.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public RoomResponseDto createChatRoom(RoomCreateRequestDto dto){
        Room entity = dtoToEntity(dto);
        return entityToDto(roomRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteChatRoom(UUID room_id){
        roomRepository.deleteByRoomId(room_id);
    }
}
