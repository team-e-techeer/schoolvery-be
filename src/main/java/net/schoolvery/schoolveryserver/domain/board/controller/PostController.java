package net.schoolvery.schoolveryserver.domain.board.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.PostResponseDto;
import net.schoolvery.schoolveryserver.domain.board.service.PostService;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@Log4j2
@RequiredArgsConstructor
@RestController
@Tag(name = "Post Controller", description = "Post Controller REST API")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<PageResultDto> list(PageRequestDto pageRequestDto) {

        PageResultDto result = postService.getPosts(pageRequestDto);
        return ResponseEntity.ok()
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {

        PostResponseDto dto = postService.getPostById(id);
        return ResponseEntity.ok()
            .body(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<PostResponseDto> create (@Valid @RequestBody PostCreateRequestDto dto) {

        PostResponseDto result = postService.create(dto);
        return ResponseEntity.created(
            WebMvcLinkBuilder
                .linkTo(PostController.class)
                .slash(result.getId())
                .toUri()
        )
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {

        postService.remove(id);
        return ResponseEntity.ok()
            .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto dto) {

        postService.modify(id, dto);
        return ResponseEntity.ok()
            .body(null);
    }
}
