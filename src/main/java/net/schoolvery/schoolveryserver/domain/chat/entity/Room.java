package net.schoolvery.schoolveryserver.domain.chat.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "room")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "POSTID", nullable = false)
    private Long post_id;
}
