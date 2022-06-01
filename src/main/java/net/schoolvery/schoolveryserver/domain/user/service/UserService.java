package net.schoolvery.schoolveryserver.domain.user.service;

import java.util.Optional;
import java.util.UUID;

import net.schoolvery.schoolveryserver.domain.school.entity.School;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;

import java.util.List;

public interface UserService {

    UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto);
    List<User> getAllUsers();
    Optional<User> modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(UUID id);

    UserCreateResponseDto getUserById(UUID id);
    String login(UserLoginRequestDto userLoginRequestDto);
    GetUserResponseDto findByUserid(UUID id);
    boolean findByUserEmail(String email);
    boolean findByUserNickname(String nickname);


    default User createUserRequest(UserCreateRequestDto userCreateRequestDto) {
        User user = User.builder()
                .name(userCreateRequestDto.getName())
                .nickname(userCreateRequestDto.getNickname())
                .password(userCreateRequestDto.getPassword())
                .email(userCreateRequestDto.getEmail())
                .phoneNum(userCreateRequestDto.getPhoneNum())
                .school(School.builder().id(userCreateRequestDto.getSchoolId()).build())
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
                .phoneNum(user.getPhoneNum())
                .schoolId(user.getSchool().getId())
                .build();

        return userDto;

    }

    default GetUserResponseDto findUserResponse(User user) {
        GetUserResponseDto dto = GetUserResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .nickname(user.getNickname())
            .password(user.getPassword())
            .email(user.getEmail())
            .schoolId(user.getSchool().getId())
            .phoneNum(user.getPhoneNum())
            .build();

        return dto;
    }
          
    default User updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        User user = User.builder()
                .nickname(userUpdateRequestDto.getNickname())
                .phoneNum(userUpdateRequestDto.getPhoneNum())
                .build();

        return user;
    }


}
