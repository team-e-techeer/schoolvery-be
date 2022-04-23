package net.schoolvery.schoolveryserver.domain.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.respository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto createCategory(CategoryCreateRequestDto dto) {
        Category entity = dtoToEntity(dto);
        return entityToDto(categoryRepository.save(entity));
    }
    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateRequestDto dto){
        Optional<Category> result = categoryRepository.findById(id);
        Category entity = result.get();
        entity.update(
                dto.getName(),
                dto.getDescription()
        );
        Category updatedEntity = categoryRepository.save(entity);
        return entityToDto(updatedEntity);
    }
    @Override
    public CategoryResponseDto getCategoryById(Integer id) {
        Optional<Category> result = categoryRepository.findById(id);
        return result.isPresent()? entityToDto(result.get()): null;
    }
}