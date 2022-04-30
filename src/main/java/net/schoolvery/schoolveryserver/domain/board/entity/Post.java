package net.schoolvery.schoolveryserver.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import net.schoolvery.schoolveryserver.global.common.BaseEntity;

@Entity
@Table(name = "post")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 150, nullable = false)
  private UUID user_id;

  @Column(name = "school_id", length = 150, nullable = false)
  private UUID schoolId;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @Column(length = 100, nullable = false)
  private String location;

  @Column(length = 45, nullable = false)
  private Integer people_num;

  @Column(length = 50, nullable = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime deadline;

  @Column(length = 100, nullable = true)
  private Integer delivery_fee;

  @Column(length = 150, nullable = false)
  private String content;

  @Column(length = 150, nullable = false)
  private String store;

  @Column(length = 50, nullable = true)
  private String status;

  public void modify (String title, String location, LocalDateTime deadline, Integer people_num, Integer delivery_fee, String content) {
    this.title = title;
    this.location = location;
    this.deadline = deadline;
    this.people_num = people_num;
    this.delivery_fee = delivery_fee;
    this.content = content;
  }
}
