package net.schoolvery.schoolveryserver.domain.user.service;

import java.util.UUID;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserUpdateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;

import java.util.List;

public interface UserService {

    UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto);
    List<User> getAllUsers();
    UserUpdateResponseDto modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(UUID id);
    UserCreateResponseDto getUserById(UUID id);
    String login(UserLoginRequestDto userLoginRequestDto);

    default User createUserRequest(UserCreateRequestDto userCreateRequestDto) {
        User user = User.builder()
                .name(userCreateRequestDto.getName())
                .nickname(userCreateRequestDto.getNickname())
                .email(userCreateRequestDto.getEmail())
                .schoolNum(userCreateRequestDto.getSchoolNum())
                .phoneNum(userCreateRequestDto.getPhoneNum())
                .school(userCreateRequestDto.getSchool())
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
                .schoolNum(user.getSchoolNum())
                .phoneNum(user.getPhoneNum())
                .school(user.getSchool())
                .build();

        return userDto;

    }

    default User updateUser(UUID id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = User.builder()
                .nickname(userUpdateRequestDto.getNickname())
                .phoneNum(userUpdateRequestDto.getPhoneNum())
                .build();

        return user;
    }

}
