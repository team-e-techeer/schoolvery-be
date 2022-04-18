package net.schoolvery.schoolveryserver.domain.hello.service;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.response.HelloResponseDto;
import net.schoolvery.schoolveryserver.domain.hello.entity.Hello;
import net.schoolvery.schoolveryserver.domain.hello.entity.QHello;
import net.schoolvery.schoolveryserver.domain.hello.repository.HelloRepository;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService{

    private final HelloRepository helloRepository;

    @Override
    public HelloResponseDto create(HelloCreateRequestDto dto) {
        Hello entity = dtoToEntity(dto);
        helloRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    public PageResultDto<HelloResponseDto, Hello> getAllUser(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("name").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDto);
        Page<Hello> result = helloRepository.findAll(booleanBuilder, pageable);

        Function<Hello, HelloResponseDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public HelloResponseDto getUserById(Long id) {
        Optional<Hello> result = helloRepository.findById(id);
        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void modify(Long id, HelloUpdateRequestDto dto) {
        Optional<Hello> result = helloRepository.findById(id);
        if (result.isPresent()) {
            Hello entity = result.get();
            entity.modify(dto.getGreeting());
            helloRepository.save(entity);
        }
    }

    @Override
    public void remove(Long id) {
        helloRepository.deleteById(id);
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QHello qHello = QHello.hello;
        String keyword = requestDto.getKeyword();
        BooleanExpression expression = qHello.id.gt(0L);

        booleanBuilder.and(expression);

        if (keyword == null || keyword.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.or(qHello.greeting.contains(keyword));

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;

    }
}
