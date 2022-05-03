package net.schoolvery.schoolveryserver.domain.chat.repository;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository <Room,Integer> {
}
