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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}