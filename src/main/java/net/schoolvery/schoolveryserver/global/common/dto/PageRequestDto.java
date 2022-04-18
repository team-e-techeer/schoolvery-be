package net.schoolvery.schoolveryserver.global.common.dto;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestDto {
    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDto(){
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page - 1, size, sort);
    }
}
