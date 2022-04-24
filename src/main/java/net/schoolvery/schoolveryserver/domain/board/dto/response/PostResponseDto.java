package net.schoolvery.schoolveryserver.domain.board.dto.response;

import java.time.LocalDateTime;
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
  private Long schoolId;
  private Long categoryId;
  private String peopleNum;
  private String deliveryFee;
  private String content;
  private String store;
}
