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
  private String people_num;
  private String delivery_fee;
  private String content;
  private String store;
}
