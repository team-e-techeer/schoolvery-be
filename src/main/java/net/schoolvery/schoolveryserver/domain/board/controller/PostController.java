package net.schoolvery.schoolveryserver.domain.board.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PageResultDto> list(PageRequestDto pageRequestDto, HttpServletRequest request) {

        PageResultDto result = postService.getPosts(pageRequestDto);
        return ResponseEntity.ok()
            .body(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id, HttpServletRequest request) {

        PostResponseDto dto = postService.getPostById(id);
        return ResponseEntity.ok()
            .body(dto);
    }

    @GetMapping("/school/{schoolId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PageResultDto> getPostBySchoolId(@PathVariable UUID schoolId, PageRequestDto pageRequestDto, HttpServletRequest request) {

        pageRequestDto.setSchoolId(schoolId);
        PageResultDto dto = postService.getPosts(pageRequestDto);
        return ResponseEntity.ok()
            .body(dto);
    }

    @GetMapping("/school/{schoolId}/category/{categoryId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PageResultDto> getPostBySchoolIdAndCategoryId(@PathVariable UUID schoolId, @PathVariable UUID categoryId
            , PageRequestDto pageRequestDto, HttpServletRequest request) {

        pageRequestDto.setSchoolId(schoolId);
        pageRequestDto.setCategoryId(categoryId);

        PageResultDto dto = postService.getPosts(pageRequestDto);
        return ResponseEntity.ok()
            .body(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PostResponseDto> create (@Valid @RequestBody PostCreateRequestDto dto, HttpServletRequest request) {

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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, HttpServletRequest request) {

        postService.remove(id);
        return ResponseEntity.ok()
            .body(null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto dto, HttpServletRequest request) {

        postService.modify(id, dto);
        return ResponseEntity.ok()
            .body(null);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PageResultDto> getPostByUserId(@PathVariable UUID userId, PageRequestDto pageRequestDto, HttpServletRequest request) {

        PageResultDto dto = postService.getPostsByUserId(userId, pageRequestDto);
        return ResponseEntity.ok()
            .body(dto);
    }
}
