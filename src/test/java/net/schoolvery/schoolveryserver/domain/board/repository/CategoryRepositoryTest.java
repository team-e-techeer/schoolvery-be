package net.schoolvery.schoolveryserver.domain.board.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 존재하는_카테고리인지_확인() {
        UUID id = UUID.randomUUID();
        Category category = new Category(id, "일식", "일식 입니다.");
        categoryRepository.save(category);
        Boolean actualResult = categoryRepository.isCategoryExitsById(id);
        assertThat(actualResult).isFalse();
    }

}