package net.schoolvery.schoolveryserver.domain.chat.repository;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ChatRepository extends JpaRepository <Room,Long> {
}
