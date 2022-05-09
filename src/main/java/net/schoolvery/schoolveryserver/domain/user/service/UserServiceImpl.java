package net.schoolvery.schoolveryserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserUpdateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.repository.UserRepository;
import net.schoolvery.schoolveryserver.global.error.exception.BusinessException;
import net.schoolvery.schoolveryserver.global.error.exception.ErrorCode;
import net.schoolvery.schoolveryserver.global.utils.AES128;
import net.schoolvery.schoolveryserver.global.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.schoolvery.schoolveryserver.global.error.exception.ErrorCode.*;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;


    // User Create
    @Override
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        try {
            String password = new AES128("${spring.security.user.password}").encrypt(userCreateRequestDto.getPassword());
            userCreateRequestDto.setPassword(password);

        } catch (Exception e) {
            throw new BusinessException(PASSWORD_ENCRYPTION_ERROR);
        }

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

    @Override
    public GetUserResponseDto findByUserid(UUID id) {

        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? findUserResponse(result.get()) : null;
    }

    @Override
    public boolean findByUserEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean findByUserNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);

        if (user.isPresent()) {
            return false;
        }

        return false;
    }


    // User modify ( Update )
    @Override
    public UserUpdateResponseDto modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> user_result = userRepository.findById(id);

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
    public void deleteUser(UUID id) {
        userRepository.deleteAllById(id);
    }

    @Override
    public String login(UserLoginRequestDto userLoginRequestDto) {
        // 로그인 시도시, login_pw 암호화
        try {
            String password = new AES128("${spring.security.user.password}").encrypt(userLoginRequestDto.getPassword());
            userLoginRequestDto.setPassword(password);

        } catch (Exception e) {
            throw new BusinessException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            User user = userRepository.findByPassword(userLoginRequestDto.getPassword()).get();
            return jwtTokenProvider.createToken(user.getEmail());

        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException(LOGIN_FAILED);
        }

    }

}
