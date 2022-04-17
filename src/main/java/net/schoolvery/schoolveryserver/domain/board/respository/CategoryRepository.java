package net.schoolvery.schoolveryserver.domain.board.respository;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
}
