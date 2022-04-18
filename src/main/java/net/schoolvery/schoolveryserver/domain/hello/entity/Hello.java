package net.schoolvery.schoolveryserver.domain.hello.entity;

import lombok.*;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hello extends BaseEntity {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String greeting;

    public void modify(String greeting) {

        this.greeting = greeting;
    }

}
