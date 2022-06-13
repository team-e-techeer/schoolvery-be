package net.schoolvery.schoolveryserver.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserLoginRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.GetUserResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserLoginResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.exception.EmailDuplicateException;
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

    // All Users find
    @GetMapping("")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }

    // find User by id
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

    // Create Users
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<UserCreateResponseDto> CreateUsers(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {

        UserCreateResponseDto create = userService.createUser(userCreateRequestDto);

        return ResponseEntity.ok()
                .body(create);
    }

    // Update Users
    @PatchMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> update = userService.modifyUser(id, userUpdateRequestDto);

        return ResponseEntity.ok()
                .body(update);
    }

    // delete Users
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

    @GetMapping("/check/email")
    public ResponseEntity<Boolean> checkUserEmail(@RequestBody UserCreateRequestDto userCreateRequestDto) throws EmailDuplicateException {
        String email = userCreateRequestDto.getEmail();

        Boolean result = userService.findByUserEmail(email);

        if (!result)
            throw new EmailDuplicateException("이메일이 중복입니다.");

        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/check/nickname")
    public ResponseEntity<Boolean> checkUserNickname(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        String nickname = userCreateRequestDto.getNickname();
        Boolean result = userService.findByUserNickname(nickname);

        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody String email) throws Exception {
        String confirm = emailService.sendEmailMessage(email);

        return ResponseEntity.ok()
                .body(confirm);
    }

}
