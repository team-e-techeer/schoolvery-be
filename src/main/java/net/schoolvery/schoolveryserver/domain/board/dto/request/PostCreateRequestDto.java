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
  private UUID schoolId;
  private UUID userId;
//  private String categoryName;
  private UUID categoryId;
  private String location;
  private Integer peopleNum;
  private Integer deliveryFee;
  private LocalDateTime deadline;
  private String content;
  private String store;

}
