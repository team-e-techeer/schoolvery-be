package net.schoolvery.schoolveryserver.domain.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;

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

//  @Column(length = 1500, nullable = false)
//  private String userId;

  @Column(length = 1500, nullable = false)
  private Long schoolId;

  @Column(length = 50, nullable = true)
  private Long categoryId;

  @Column(length = 100, nullable = false)
  private String location;

  @Column(length = 1500, nullable = false)
  private String peopleNum;

  @Column(length = 50, nullable = true)
  private LocalDateTime deadline;

  @Column(length = 100, nullable = true)
  private String deliveryFee;

  @Column(length = 1500, nullable = false)
  private String content;

  @Column(length = 1500, nullable = false)
  private String store;

  // default 지정해주기
  @Column(length = 50, nullable = true)
  private String status;

  public void modify (String title, String location) {
    this.title = title;
    this.location = location;
  }

}
