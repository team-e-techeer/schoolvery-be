package net.schoolvery.schoolveryserver.domain.school.repository;

import net.schoolvery.schoolveryserver.domain.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<School, UUID>, QuerydslPredicateExecutor<School> {


}
