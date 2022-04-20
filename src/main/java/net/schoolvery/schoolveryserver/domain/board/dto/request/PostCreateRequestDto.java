package net.schoolvery.schoolveryserver.domain.board.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateRequestDto {

  private String title;
  private String location;
  private String people_num;
  private String delivery_fee;
  private String content;
  private String store;

}
