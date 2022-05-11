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
@Table(name = "member")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "user_id")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID user_id;

    @Column(name = "nickname", nullable = false)
    private String nickname;
}
