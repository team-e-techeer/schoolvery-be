package net.schoolvery.schoolveryserver.domain.board.service;

import java.util.List;
import java.util.UUID;

import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

public interface CategoryService {
    default Category dtoToEntity (CategoryCreateRequestDto dto) {
        Category entity = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return entity;
    }
    default CategoryResponseDto entityToDto (Category entity) {
        CategoryResponseDto dto = CategoryResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getName())
                .build();
        return dto;
    }

    CategoryResponseDto createCategory(CategoryCreateRequestDto dto);
    void deleteCategory(UUID id);
    CategoryResponseDto updateCategory(UUID id, CategoryUpdateRequestDto dto);
    CategoryResponseDto getCategoryById(UUID id);
    PageResultDto<CategoryResponseDto, Category> getAllCategory(PageRequestDto requestDto);

    // for test
    List<Category> getAllCategory();
}
