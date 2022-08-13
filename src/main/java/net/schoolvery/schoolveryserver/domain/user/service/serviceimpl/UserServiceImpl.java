package net.schoolvery.schoolveryserver.domain.user.service.serviceimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.aws.service.FileUploadService;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserLoginResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.repository.UserRepository;
import net.schoolvery.schoolveryserver.domain.user.service.UserService;
import net.schoolvery.schoolveryserver.global.error.exception.BusinessException;
import net.schoolvery.schoolveryserver.global.utils.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final FileUploadService fileUploadService;


    // User Create
    @Override
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) throws IllegalAccessException {
        try {
            String password = passwordEncoder.encode(userCreateRequestDto.getPassword());
            userCreateRequestDto.setPassword(password);

        } catch (Exception e) {
            throw new BusinessException(PASSWORD_ENCRYPTION_ERROR);
        }
        String imgUrl = fileUploadService.uploadImage(userCreateRequestDto.getProfileImageUrl());
        userCreateRequestDto.setImgUrlString(imgUrl);

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
        return result.map(this::findUserResponse).orElse(null);
    }

    @Override
    public boolean findByUserEmail(String email) {
        if(Pattern.matches("\"^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]" , email));
             Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean findByUserNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);

        if (user.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> modifyUser(UUID id, UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> user_result = userRepository.findById(id);
        try {
            String password = passwordEncoder.encode(userUpdateRequestDto.getPassword());
            userUpdateRequestDto.setPassword(password);
            System.err.println(password);

        } catch (Exception e) {
            throw new BusinessException(PASSWORD_ENCRYPTION_ERROR);
        }

        if (user_result.isPresent()) {
            User user = user_result.get();
            user.modifyUser(
                    userUpdateRequestDto.getNickname(),
                    userUpdateRequestDto.getPassword(),
                    userUpdateRequestDto.getPhoneNum(),
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
    public GetUserResponseDto findByUserToken(String token) {
        Optional<User> user = userRepository.findByEmail(token);

            return user.map(this::findUserResponse).orElse(null);
        }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        // 로그인 시도시, login_pw 암호화
        try {
          User user = userRepository.findByEmail(userLoginRequestDto.getEmail()).get();

            if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
                throw new BusinessException(PASSWORD_WRONG_ERROR);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.createToken(authentication);

            return new UserLoginResponseDto(jwt, userLoginRequestDto.getEmail());

        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException(LOGIN_FAILED);
        }
    }
}
