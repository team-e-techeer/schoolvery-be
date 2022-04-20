package net.schoolvery.schoolveryserver.domain.user.repository;

import net.schoolvery.schoolveryserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    String delete(String idx);

}
