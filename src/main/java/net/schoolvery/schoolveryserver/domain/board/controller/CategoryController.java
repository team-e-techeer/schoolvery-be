package net.schoolvery.schoolveryserver.domain.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.schoolvery.schoolveryserver.domain.board.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Category Controller REST API")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryCreateRequestDto categoryCreateRequestDto){
        CategoryResponseDto result = categoryService.createCategory(categoryCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok()
                .body(null);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable UUID id, @RequestBody CategoryUpdateRequestDto  categoryUpdateRequestDto) {
        CategoryResponseDto updatedDto = categoryService.updateCategory(id, categoryUpdateRequestDto);
        return ResponseEntity.ok()
                .body(updatedDto);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable UUID id) {
        CategoryResponseDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok()
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<PageResultDto> getCategoryList(PageRequestDto pageRequestDto) {
        PageResultDto result = categoryService.getAllCategory(pageRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }
}
