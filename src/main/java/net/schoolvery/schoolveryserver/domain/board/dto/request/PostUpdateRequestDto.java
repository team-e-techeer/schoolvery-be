package net.schoolvery.schoolveryserver.domain.board.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUpdateRequestDto {

  private String title;
  private String location;
  private Integer peopleNum;
  private Integer deliveryFee;
  private String content;
  private UUID categoryId;  //카테고리 ID
  private LocalDateTime deadline;

}
