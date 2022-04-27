package net.schoolvery.schoolveryserver.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.request.UserUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserCreateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.dto.response.UserUpdateResponseDto;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.service.UserService;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
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

    // All Users find
    @GetMapping("")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
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
    public ResponseEntity<UserUpdateResponseDto> updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserUpdateResponseDto update = userService.modifyUser(id, userUpdateRequestDto);

        return ResponseEntity.ok()
                .body(update);
    }

    // delete Users
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok()
                .body(null);
    }
}
