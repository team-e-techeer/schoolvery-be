package net.schoolvery.schoolveryserver.domain.user.repository;

import net.schoolvery.schoolveryserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, QuerydslPredicateExecutor<User>{

    void deleteAllById(UUID id);

    // 쿼리가 수행될때 Lazy 조회가 아닌 Eager 조회로 authorities정보도 같이 가져옴
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByNickname(String nickname);
}
