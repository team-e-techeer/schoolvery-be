package net.schoolvery.schoolveryserver.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "post")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "school_id", length = 150, nullable = false)
    private Integer schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(length = 100, nullable = false)
    private String location;

    @Column(name = "people_num", length = 45, nullable = false)
    private Integer peopleNum;

    @Column(length = 50, nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime deadline;

    @Column(name = "delivery_fee", length = 100, nullable = true)
    private Integer deliveryFee;

    @Column(length = 150, nullable = false)
    private String content;

    @Column(length = 150, nullable = false)
    private String store;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'OPEN'")
    private Status status;

    public void modify(String title, String location, LocalDateTime deadline, Integer peopleNum,
        Integer deliveryFee, String content) {
        this.title = title;
        this.location = location;
        this.deadline = deadline;
        this.peopleNum = peopleNum;
        this.deliveryFee = deliveryFee;
        this.content = content;
    }
}
