package net.schoolvery.schoolveryserver.domain.chat.repository;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
