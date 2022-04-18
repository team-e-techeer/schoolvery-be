package net.schoolvery.schoolveryserver.domain.post.entity;

import lombok.*;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String location;

    @Column(length = 50, nullable = false)
    private Integer people_num;

    @Column
    private LocalDateTime deadline;

    @Column
    private String delivery_fee;

    @Column
    private String content;

    // status 템플릿 따로 만들어야 함
    @Column
    private String status;
    // 원래 엔티티 클래스는 가능하면 setter 관련 기능을 만들지 않지만,
    // 필요에 따라서 수정 기능을 만들기도 한다.
    public void modify(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
