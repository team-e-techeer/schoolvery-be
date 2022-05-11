package net.schoolvery.schoolveryserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
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
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    @Autowired
    private final JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("Schoolvery회원가입 이메일 인증");

        String msgg = "";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 Schoolvery입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ats36574@gmail.com","schoolvery"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int idx = rnd.nextInt(3);

            switch (idx) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;

            }
        }

        return key.toString();
    }


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
                    userUpdateRequestDto.getPhoneNum()
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
    public UserCreateResponseDto getUserById(UUID id) {
        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? createUserResponse(result.get()) : null;
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

    @Override
    public String sendimpleMessage(String to) throws Exception {

        MimeMessage message = createMessage(to);

        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalAccessException();
        }

        return ePw;

    }


}
