package net.schoolvery.schoolveryserver.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import java.util.UUID;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

//    Page<Post> findBySchoolId(UUID school_id, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Post s WHERE s.id = ?1")
    Boolean isPostExitsById(Integer id);

    Page<Post> findByUserId(UUID userId, Pageable pageable);
}
