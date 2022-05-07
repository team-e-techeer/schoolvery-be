package net.schoolvery.schoolveryserver.domain.chat.repository;

import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
