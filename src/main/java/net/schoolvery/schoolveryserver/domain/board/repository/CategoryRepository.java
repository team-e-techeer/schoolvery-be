package net.schoolvery.schoolveryserver.domain.board.repository;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryRepository extends JpaRepository<Category,Integer>, QuerydslPredicateExecutor<Category>{

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Category s WHERE s.id = ?1")
    Boolean isCategoryExitsById(Integer id);
}
