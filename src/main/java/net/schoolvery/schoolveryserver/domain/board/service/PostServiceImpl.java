package net.schoolvery.schoolveryserver.domain.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.PostResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import net.schoolvery.schoolveryserver.domain.board.entity.QPost;
import net.schoolvery.schoolveryserver.domain.board.repository.PostRepository;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.service.RoomService;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final RoomService roomService;

    @Override
    public PostResponseDto create(PostCreateRequestDto dto) {
        Post entity = createDtoToEntity(dto);
        postRepository.save(entity);
        RoomCreateRequestDto room = RoomCreateRequestDto.builder()
            .post_id(entity.getId())
            .name(entity.getTitle())
            .build();

        RoomResponseDto roomResponse = roomService.createChatRoom(room);
        PostResponseDto postResponse = entityToDto(entity);

        postResponse.setRoomId(roomResponse.getId());

        return postResponse;
    }

    @Override
    public PageResultDto<PostResponseDto, Post> getPosts(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("deadline"));

        BooleanBuilder booleanBuilder = getSearch(requestDto);
        Page<Post> result = postRepository.findAll(booleanBuilder, pageable);

        Function<Post, PostResponseDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public PostResponseDto getPostById(Long id) {
        Optional<Post> result = postRepository.findById(id);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void modify(Long id, PostUpdateRequestDto dto) {
        Optional<Post> result = postRepository.findById(id);
        if (result.isPresent()) {
            Post entity = result.get();
            entity.modify(
                dto.getTitle(),
                dto.getLocation(),
                dto.getDeadline(),
                dto.getPeopleNum(),
                dto.getDeliveryFee(),
                dto.getContent()
            );
            postRepository.save(entity);
        }
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PageResultDto<PostResponseDto, Post> getPostsByUserId(UUID userId, PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("deadline"));
        Page<Post> result = postRepository.findByUserId(userId, pageable);

        Function<Post, PostResponseDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }


    // Todo : getSearch : at title -> o, store, location
    // Todo : booleanexpresstion

    private BooleanBuilder getSearch(PageRequestDto requestDto) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QPost qPost = QPost.post;

        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();
        UUID schoolId = requestDto.getSchoolId();
        UUID categoryId = requestDto.getCategoryId();

        if (schoolId != null) {
            booleanBuilder.and(qPost.schoolId.eq(schoolId));
        }

        if (categoryId != null) {
            booleanBuilder.and(qPost.category.id.eq(categoryId));
        }

        // keyword + type : keyword 존재, type must have
        if (keyword != null) {
            if (type.contains("store")) {
                booleanBuilder.and(qPost.store.contains(keyword));
            }
            if (type.contains("title")) {
                booleanBuilder.and(qPost.title.contains(keyword));
            }
            if (type.contains("location")) {
                booleanBuilder.and(qPost.location.contains(keyword));
            }
        }

        return booleanBuilder;
    }
}
