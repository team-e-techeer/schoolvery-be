package net.schoolvery.schoolveryserver.domain.board.entity;

import lombok.Builder;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "category")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{
    /*
    @Id
    @GeneratedValue(generator = "uuid2") //@GeneratedValue -> PK 설정
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
     */

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NAME" ,nullable = false)
    private String name;

    @Column(name = "DESCRIPTION" ,nullable = false)
    private String description;

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
