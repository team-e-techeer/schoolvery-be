package net.schoolvery.schoolveryserver.domain.chat.repository;
import net.schoolvery.schoolveryserver.domain.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
public interface RoomRepository extends JpaRepository <Room,Long> {
    @Modifying
    @Query("delete from Room r where r.id=:id")
    void deleteByRoomId(@Param("id") UUID id);

    @Modifying
    @Query("update Room r set r.name=:name, r.post_id=:post_id where r.id=:id")
    void findByRoomId(@Param("id") UUID id, @Param("name")String name, @Param("post_id") Long post_id);

    @Query("SELECT r FROM Room r WHERE r.id =:id")
    Room getByRoomId(@Param("id") UUID id);
}
