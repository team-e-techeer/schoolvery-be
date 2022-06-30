package net.schoolvery.schoolveryserver.domain.user.service.serviceimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.repository.UserRepository;
import net.schoolvery.schoolveryserver.domain.user.service.UserService;
import net.schoolvery.schoolveryserver.global.error.exception.BusinessException;
import net.schoolvery.schoolveryserver.global.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

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

    @Autowired
    private final PasswordEncoder passwordEncoder;


    // User Create
    @Override
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        try {
            String password = passwordEncoder.encode(userCreateRequestDto.getPassword());
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
        if(Pattern.matches("\"^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]" , email));
             Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
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
    public Optional<User> modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> user_result = userRepository.findById(id);
        try {
            String password = passwordEncoder.encode(userUpdateRequestDto.getPassword());
            userUpdateRequestDto.setPassword(password);

        } catch (Exception e) {
            throw new BusinessException(PASSWORD_ENCRYPTION_ERROR);
        }

        if (user_result.isPresent()) {
            User user = user_result.get();
            user.modifyUser(
                    userUpdateRequestDto.getNickname(),
                    userUpdateRequestDto.getPhoneNum(),
                    userUpdateRequestDto.getPassword(),
                    userUpdateRequestDto.getProfileImageUrl()
            );
            userRepository.save(user);
        }
        return user_result;
    }


    // User delete
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteAllById(id);
    }

    @Override
    public UserCreateResponseDto getUserById(UUID id) {
        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? createUserResponse(result.get()) : null;
    }

    @Override
    public String login(UserLoginRequestDto userLoginRequestDto) {
        // 로그인 시도시, login_pw 암호화
        try {
            User user = userRepository.findByEmail(userLoginRequestDto.getEmail()).get();

            if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
                throw new BusinessException(PASSWORD_WRONG_ERROR);

            return jwtTokenProvider.createToken(user.getEmail());

        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException(LOGIN_FAILED);
        }

    }
}
