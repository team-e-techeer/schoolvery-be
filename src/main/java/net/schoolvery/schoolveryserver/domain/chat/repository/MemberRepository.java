package net.schoolvery.schoolveryserver.domain.chat.repository;

import net.schoolvery.schoolveryserver.domain.chat.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByUserId(UUID user_id);

    List<Member> findMByUserId(UUID memberId);

    Page <Member> findByRoomId(UUID roomId,Pageable pageable);
}
