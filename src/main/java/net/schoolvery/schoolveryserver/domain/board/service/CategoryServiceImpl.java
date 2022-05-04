package net.schoolvery.schoolveryserver.domain.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.querydsl.core.BooleanBuilder; //쿼리 WHERE 뒤의 조건을 생성해주는 것
import com.querydsl.core.types.dsl.BooleanExpression;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.CategoryUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.CategoryResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.entity.QCategory;
import net.schoolvery.schoolveryserver.domain.board.repository.CategoryRepository;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.function.Function;
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

    @Override
    public PageResultDto<CategoryResponseDto, Category> getAllCategory(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("name").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDto);

        Page<Category> result = categoryRepository.findAll(booleanBuilder, pageable);

        Function<Category, CategoryResponseDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
    //QueryDSL
    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QCategory qCategory = QCategory.category;
        String keyword = requestDto.getKeyword();
        BooleanExpression expression = qCategory.id.gt(0L);
        booleanBuilder.and(expression);

        if (keyword == null || keyword.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.and(qCategory.description.contains(keyword));

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }
}
