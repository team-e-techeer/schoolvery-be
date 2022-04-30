package net.schoolvery.schoolveryserver.domain.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "message")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    @Column(name = "roomID", nullable = false)
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID room_id;

    @Column(name = "userID", nullable = false)
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID user_id;

    @Column(name = "MESSAGE", columnDefinition = "TEXT", nullable = false)
    private String message;
}
