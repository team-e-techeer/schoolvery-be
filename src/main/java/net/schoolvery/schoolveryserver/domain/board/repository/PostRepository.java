package net.schoolvery.schoolveryserver.domain.board.repository;

import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

}
