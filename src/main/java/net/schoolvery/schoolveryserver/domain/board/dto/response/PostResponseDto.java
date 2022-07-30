package net.schoolvery.schoolveryserver.domain.board.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
  private Long id;
  private String title;
  private String location;
  private UUID schoolId;
  private UUID categoryId;
  private Integer peopleNum;
  private Integer deliveryFee;
  private LocalDateTime deadline;
  private String content;
  private String store;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
