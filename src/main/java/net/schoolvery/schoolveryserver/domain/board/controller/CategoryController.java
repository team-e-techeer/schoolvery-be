package net.schoolvery.schoolveryserver.domain.board.controller;

import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
@RestController
@RequestMapping(value = "/api/v1/posts/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    // 카테고리 생성
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryCreateRequestDto categoryCreateRequestDto){
        CategoryResponseDto result = categoryService.createCategory(categoryCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //카테고리 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok()
                .body(null);
    }

}