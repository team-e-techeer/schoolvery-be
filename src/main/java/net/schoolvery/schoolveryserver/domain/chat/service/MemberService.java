package net.schoolvery.schoolveryserver.domain.chat.service;

import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomJoinRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.MemberResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomFindResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomJoinResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    void addMembers(UUID room_id, UUID member_id);
    RoomJoinResponseDto joinMemebers(RoomJoinRequestDto requestDto);
    boolean exitMembers(RoomJoinRequestDto requestDto);
    List<RoomFindResponseDto> findMember(UUID id);


    default Member dtoToEntity(RoomJoinRequestDto dto) {

        return Member.builder()
                .userId(dto.getUser_id())
                .room(Room.builder().id(dto.getRoom_id()).build())
                .build();
    }

    default RoomJoinResponseDto EntityToDto(Member member) {
        return RoomJoinResponseDto.builder()
                .room_id(member.getRoom().getId())
                .build();
    }

    default RoomFindResponseDto EntityToFindResponseDto(Member member) {
        return RoomFindResponseDto.builder()
                .id(member.getId())
                .room_id(member.getRoom().getId())
                .user_id(member.getUserId())
                .build();
    }

    default MemberResponseDto entityToMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .user_id(member.getUserId())
                .build();
    }

    PageResultDto<MemberResponseDto,Member> checkMembers(UUID roomId, PageRequestDto pageRequestDto);
}

