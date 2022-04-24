package net.schoolvery.schoolveryserver.domain.board.controller;

import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.board.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping(value = "/api/v1/posts/category")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Category Controller REST API")
public class CategoryController {
    private final CategoryService categoryService;
    // 카테고리 생성
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryCreateRequestDto categoryCreateRequestDto){
        CategoryResponseDto result = categoryService.createCategory(categoryCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // 카테고리 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok()
                .body(null);
    }
    // 카테고리 수정
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateRequestDto  categoryUpdateRequestDto) {
        CategoryResponseDto updatedDto = categoryService.updateCategory(id, categoryUpdateRequestDto);
        return ResponseEntity.ok()
                .body(updatedDto);
    }
    // 해당 카테고리 조회
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Integer id) {
        CategoryResponseDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok()
                .body(dto);
    }
    // 전체 카테고리 조회
    @GetMapping("/list")
    public ResponseEntity<PageResultDto> getCategoryList(PageRequestDto pageRequestDto) {
        PageResultDto result = categoryService.getAllCategory(pageRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }
}
