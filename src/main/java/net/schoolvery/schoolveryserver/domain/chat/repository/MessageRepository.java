package net.schoolvery.schoolveryserver.domain.chat.repository;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("SELECT m FROM Message m WHERE m.id =:id")
    List<Message> findAllByRoomId(@Param("id") UUID id);
}
