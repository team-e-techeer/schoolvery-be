package net.schoolvery.schoolveryserver.domain.board.entity;

import lombok.Builder;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue -> PK 설정, GenerationType.IDENTITY -> AutoIncrement
    private Integer id;

    @Column(name = "NAME" ,nullable = false)
    private String name;

    @Column(name = "DESCRIPTION" ,nullable = false)
    private String description;

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
