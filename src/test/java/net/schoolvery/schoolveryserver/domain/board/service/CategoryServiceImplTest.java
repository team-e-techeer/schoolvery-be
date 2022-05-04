package net.schoolvery.schoolveryserver.domain.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import net.schoolvery.schoolveryserver.domain.board.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock private CategoryRepository categoryRepository;

    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach void setUp() {
        this.categoryServiceImpl = new CategoryServiceImpl(this.categoryRepository);
    }

    @Test
    void getAllCategory() {
        categoryServiceImpl.getAllCategory();
        verify(categoryRepository).findAll();
    }

}