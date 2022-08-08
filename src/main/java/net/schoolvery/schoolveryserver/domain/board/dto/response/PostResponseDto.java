package net.schoolvery.schoolveryserver.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
  private Long id;
  private String title;
  private String location;
  private UUID schoolId;
  private String schoolName;
  private UUID categoryId;
  private String categoryName;
  private Integer peopleNum;
  private Integer deliveryFee;
  private LocalDateTime deadline;
  private String content;
  private String store;
  private UUID roomId;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
