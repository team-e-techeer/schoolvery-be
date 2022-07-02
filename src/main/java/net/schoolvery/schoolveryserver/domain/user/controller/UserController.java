package net.schoolvery.schoolveryserver.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.*;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserLoginResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.exception.EmailDuplicateException;
import net.schoolvery.schoolveryserver.domain.user.exception.NicknameDuplicateException;
import net.schoolvery.schoolveryserver.domain.user.service.EmailService;
import net.schoolvery.schoolveryserver.domain.user.service.UserService;
import net.schoolvery.schoolveryserver.global.error.exception.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User Controller", description = "User Controller REST API")
@RestController
@Log4j2
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailService emailService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> getuserId(@PathVariable UUID id) {

        try {
            GetUserResponseDto user = userService.findByUserid(id);

            return ResponseEntity.ok()
                    .body(user);

        } catch (Exception e) {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<UserCreateResponseDto> CreateUsers(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {

        UserCreateResponseDto create = userService.createUser(userCreateRequestDto);

        return ResponseEntity.ok()
                .body(create);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> update = userService.modifyUser(id, userUpdateRequestDto);

        return ResponseEntity.ok()
                .body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok()
                .body("유저 정보가 삭제되었습니다.");
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {

        String token = userService.login(userLoginRequestDto);

        return ResponseEntity.ok()
                .body(new UserLoginResponseDto(token, userLoginRequestDto.getEmail()));
    }

    @PostMapping("/send/email")
    public ResponseEntity<String> emailConfirm(@RequestBody ConfirmEmailRequestDto email) throws Exception {
        String confirm = emailService.sendEmailMessage(email.getEmail());

        return ResponseEntity.ok()
                .body(confirm);
    }

    @PostMapping("/verifycode")
    public ResponseEntity<Boolean> verifyCode(@RequestBody VerifyCodeRequestDto code) {
        boolean vertifycode = emailService.vertifyCode(code.getCode());

        return ResponseEntity.ok()
                .body(vertifycode);
    }


    @GetMapping("/duplicate/email")
    public ResponseEntity<Boolean> checkUserEmail(@RequestBody DuplicateEmailRequestDto requestDto) throws EmailDuplicateException {
        String email = requestDto.getEmail();

        Boolean result = userService.findByUserEmail(email);

        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/duplicate/nickname")
    public ResponseEntity<Boolean> checkUserNickname(@RequestBody DuplicateNicknameRequestDto requestDto) throws NicknameDuplicateException {
        String nickname = requestDto.getNickname();
        Boolean result = userService.findByUserNickname(nickname);

        return ResponseEntity.ok()
                .body(result);
    }

}
