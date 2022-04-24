package net.schoolvery.schoolveryserver.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUpdateRequestDto {
//  private String userId;
  private String title;
  private String location;
  private String peopleNum;
  private String deliveryFee;
  private String content;
}
