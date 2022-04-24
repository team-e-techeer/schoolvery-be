package net.schoolvery.schoolveryserver.global.common.dto;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int size;
    private String type;
    private String keyword;
    private UUID schoolId;
    private UUID categoryId;

    public PageRequestDto(){
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1, size, sort);
    }
}
