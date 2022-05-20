package net.schoolvery.schoolveryserver.domain.hello.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.controller.CategoryController;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.response.HelloResponseDto;
import net.schoolvery.schoolveryserver.domain.hello.service.HelloService;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/hellos")
@Log4j2
@RequiredArgsConstructor
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private final HelloService helloService;

    @GetMapping
    public ResponseEntity<PageResultDto> list(PageRequestDto pageRequestDto) {
        logger.info("Hello. This is LogManager's logger");
        log.info("Hello. This is Lombok's logger");
        PageResultDto result = helloService.getAllUser(pageRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HelloResponseDto> getUserById(@PathVariable Long id) {
        HelloResponseDto dto = helloService.getUserById(id);
        return ResponseEntity.ok()
                .body(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<HelloResponseDto> create (@Valid @RequestBody HelloCreateRequestDto dto) {
        HelloResponseDto result = helloService.create(dto);
        return ResponseEntity.created(
                WebMvcLinkBuilder
                        .linkTo(HelloController.class)
                        .slash(result.getName())
                        .toUri()
        )
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        helloService.remove(id);
        return ResponseEntity.ok()
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HelloResponseDto> updateUser(@PathVariable Long id, @RequestBody HelloUpdateRequestDto dto) {
        helloService.modify(id, dto);
        return ResponseEntity.ok()
                .body(null);
    }
}
