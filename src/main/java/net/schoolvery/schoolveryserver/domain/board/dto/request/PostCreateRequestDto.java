package net.schoolvery.schoolveryserver.domain.board.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateRequestDto {

  private String title;
  private UUID school_id;
  private UUID user_id;
//  private String categoryName;
  private Integer category_id;
  private String location;
  private Integer people_num;
  private Integer delivery_fee;
  private LocalDateTime deadline;
  private String content;
  private String store;

}
