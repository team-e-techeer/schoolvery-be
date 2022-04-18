package net.schoolvery.schoolveryserver.domain.board.service;

import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;

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
    void deleteCategory(Integer id);
}
