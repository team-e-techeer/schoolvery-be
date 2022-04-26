package net.schoolvery.schoolveryserver.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

}
