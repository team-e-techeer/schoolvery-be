package net.schoolvery.schoolveryserver.domain.board.repository;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
public interface CategoryRepository extends JpaRepository<Category,Integer>, QuerydslPredicateExecutor<Category>{
}
