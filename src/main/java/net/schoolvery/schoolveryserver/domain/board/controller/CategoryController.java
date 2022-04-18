package net.schoolvery.schoolveryserver.domain.board.controller;

import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.board.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
@RestController
@RequestMapping(value = "/api/v1/posts/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    // 카테고리 생성
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(CategoryCreateRequestDto cat){
        CategoryResponseDto result = categoryService.create(cat);
        return ResponseEntity.created(
                        WebMvcLinkBuilder
                                .linkTo(CategoryController.class)
                                .slash(result.getName())
                                .toUri()
                )
                .body(result);
    }
}