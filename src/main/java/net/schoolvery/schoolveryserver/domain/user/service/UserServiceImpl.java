package net.schoolvery.schoolveryserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserUpdateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.repository.UserRepository;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;


    // User Create
    @Override
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = createUserRequest(userCreateRequestDto);
        userRepository.save(user);

        return createUserResponse(user);
    }

    // User Read ( All )
    @Override
    public List<User> getAllUsers() {
        log.info("Find all users");
        return userRepository.findAll();
    }


    // User modify ( Update )
    @Override
    public UserUpdateResponseDto modifyUser(String id, UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> user_result = userRepository.findById(UUID.fromString(id));

        if (user_result.isPresent()) {
            User user = user_result.get();
            user.modifyUser(
                    userUpdateRequestDto.getNickname(),
                    userUpdateRequestDto.getPassword()
            );
            userRepository.save(user);
        }
        return null;
    }

    // User delete
    @Override
    public void deleteUser(String id) {
        userRepository.deleteAllById(id);
    }


}
