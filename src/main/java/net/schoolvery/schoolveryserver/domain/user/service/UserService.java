package net.schoolvery.schoolveryserver.domain.user.service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import net.schoolvery.schoolveryserver.domain.aws.service.FileUploadService;
import net.schoolvery.schoolveryserver.domain.school.entity.School;
import net.schoolvery.schoolveryserver.domain.user.dto.request.*;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserLoginResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.Authority;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {

    UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) throws IllegalAccessException;
    List<User> getAllUsers();
    Optional<User> modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto);
    void deleteUser(UUID id);

    GetUserResponseDto findByUserToken(String token);
    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);
    GetUserResponseDto findByUserid(UUID id);
    boolean findByUserEmail(String email);
    boolean findByUserNickname(String nickname);

    default User createUserRequest(UserCreateRequestDto userCreateRequestDto)  {

        User user = User.builder()
                .name(userCreateRequestDto.getName())
                .nickname(userCreateRequestDto.getNickname())
                .password(userCreateRequestDto.getPassword())
                .profileImageUrl(userCreateRequestDto.getProfileImageUrl())
                .email(userCreateRequestDto.getEmail())
                .phoneNum(userCreateRequestDto.getPhoneNum())
                .school(School.builder().schoolId(userCreateRequestDto.getSchoolId()).build())
                .authorities(Collections.singleton(Authority.builder().authorityName("ROLE_USER").build()))
                .build();

        return user;
    }

    default UserCreateResponseDto createUserResponse(User user) {
        UserCreateResponseDto userDto = UserCreateResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .password(user.getPassword())
                .phoneNum(user.getPhoneNum())
                .schoolId(user.getSchool().getSchoolId())
                .userAuthority(user.getAuthorities().stream()
                        .map(authority -> AuthorityRequestDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
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
            .schoolId(user.getSchool().getSchoolId())
            .phoneNum(user.getPhoneNum())
            .profileImageUrl(user.getProfileImageUrl())
                .modDate(user.getModDate())
                .regDate(user.getRegDate())
                .build();

        return dto;
    }
}
