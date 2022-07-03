package net.schoolvery.schoolveryserver.domain.chat.service;

import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomJoinRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomJoinResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;

import java.util.UUID;

public interface MemberService {
    void addMembers(UUID room_id, UUID member_id);
    RoomJoinResponseDto joinMemebers(RoomJoinRequestDto requestDto);

    default Member dtoToEntity(RoomJoinRequestDto dto) {

        return Member.builder()
                .member_id(dto.getMember_id())
                .room(Room.builder().id(dto.getRoom_id()).build())
                .build();
    }

    default RoomJoinResponseDto EntityToDto(Member member) {

        return RoomJoinResponseDto.builder()
                .room_id(member.getRoom().getId())
                .build();
    }
}

