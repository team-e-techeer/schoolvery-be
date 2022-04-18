package net.schoolvery.schoolveryserver.domain.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.respository.CategoryRepository;
import org.springframework.stereotype.Service;
@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto create(CategoryCreateRequestDto dto) {
        Category entity = dtoToEntity(dto);
        return entityToDto(categoryRepository.save(entity));
    }
}