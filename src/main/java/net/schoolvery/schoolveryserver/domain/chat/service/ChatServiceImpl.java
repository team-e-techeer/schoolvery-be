package net.schoolvery.schoolveryserver.domain.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.ChatResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.domain.chat.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public ChatResponseDto createChatRoom(RoomCreateRequestDto dto){
        Room entity = dtoToEntity(dto);
        return entityToDto(chatRepository.save(entity));
    }
}
