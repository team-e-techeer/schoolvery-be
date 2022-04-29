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
  private UUID school_id;
  private Integer category_id;
  private Integer people_num;
  private Integer delivery_fee;
  private LocalDateTime deadline;
  private String content;
  private String store;

}
