package net.schoolvery.schoolveryserver.domain.hello.repository;

import net.schoolvery.schoolveryserver.domain.hello.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {
}
