package net.schoolvery.schoolveryserver.domain.user.service;

import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserUpdateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

public interface UserService {

    UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto);

    PageResultDto<GetUserResponseDto, User> getAllUser(PageRequestDto pageRequestDto);
    UserUpdateResponseDto modifyUser(String id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(String id);

    default User createUserRequest(UserCreateRequestDto userCreateRequestDto) {
        User user = User.builder()
                .name(userCreateRequestDto.getName())
                .nickname(userCreateRequestDto.getNickname())
                .email(userCreateRequestDto.getEmail())
                .password(userCreateRequestDto.getPassword())
                .school_num(userCreateRequestDto.getSchool_num())
                .school_id(userCreateRequestDto.getSchool_id())
                .build();

        return user;
    }

    default UserCreateResponseDto createUserResponse(User user) {
        UserCreateResponseDto userDto = UserCreateResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .password(user.getPassword())
                .school_num(user.getSchool_num())
                .school_id(user.getSchool_id())
                .build();

        return userDto;

    }

    default User updateUser(String id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = User.builder()
                .nickname(userUpdateRequestDto.getNickname())
                .phone_num(userUpdateRequestDto.getPhone_num())
                .school_id(userUpdateRequestDto.getSchool_id())
                .school_num(userUpdateRequestDto.getSchool_num())
                .school(userUpdateRequestDto.getSchool())
                .email(userUpdateRequestDto.getEmail())
                .password(userUpdateRequestDto.getPassword())
                .build();

        return user;
    }

}
