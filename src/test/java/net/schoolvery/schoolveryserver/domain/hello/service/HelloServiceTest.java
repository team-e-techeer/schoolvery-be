package net.schoolvery.schoolveryserver.domain.hello.service;

import net.schoolvery.schoolveryserver.domain.hello.dto.response.HelloResponseDto;
import net.schoolvery.schoolveryserver.domain.hello.entity.Hello;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService service;

    @Test
    public void testSearch() {
        // 제목이나 내용에 '한글' 키워드가 있는 글을 검색
        PageRequestDto pageRequestDTO = PageRequestDto.builder()
                .page(1)
                .size(10)
                .keyword("hello")
                .build();

        PageResultDto<HelloResponseDto, Hello> resultDTO = service.getAllUser(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("--------------------");
        for (HelloResponseDto helloDto : resultDTO.getDtoList()) {
            System.out.println(helloDto);
        }

        System.out.println("====================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}