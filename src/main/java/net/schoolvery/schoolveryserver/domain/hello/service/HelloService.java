package net.schoolvery.schoolveryserver.domain.hello.service;

import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.response.HelloResponseDto;
import net.schoolvery.schoolveryserver.domain.hello.entity.Hello;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

public interface HelloService {

    HelloResponseDto create(HelloCreateRequestDto dto);
    PageResultDto<HelloResponseDto, Hello> getAllUser(PageRequestDto requestDto);
    HelloResponseDto getUserById(Long id);
    void modify(Long id, HelloUpdateRequestDto dto);
    void remove(Long id);


    default Hello dtoToEntity(HelloCreateRequestDto dto){
        Hello entity = Hello.builder()
                .name(dto.getName())
                .greeting(dto.getGreeting())
                .build();
        return entity;
    }

    default HelloResponseDto entityToDto(Hello entity) {
        HelloResponseDto dto = HelloResponseDto.builder()
                .name(entity.getName())
                .greeting(entity.getGreeting())
                .build();
        return dto;
    }
}
