package net.schoolvery.schoolveryserver.domain.board.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 존재하는_카테고리인지_확인() {
        Category category = new Category(5, "일식", "알삭압나더 .");
        categoryRepository.save(category);
        Boolean actualResult = categoryRepository.isCategoryExitsById(5);
        assertThat(actualResult).isFalse();
    }

}