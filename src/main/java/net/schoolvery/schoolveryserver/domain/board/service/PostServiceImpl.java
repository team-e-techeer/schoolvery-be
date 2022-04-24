package net.schoolvery.schoolveryserver.domain.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.PostResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import net.schoolvery.schoolveryserver.domain.board.entity.QPost;
import net.schoolvery.schoolveryserver.domain.board.repository.PostRepository;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

  private final PostRepository postRepository;

  @Override
  public PostResponseDto create(PostCreateRequestDto dto) {
    Post entity = createDtoToEntity(dto);
    postRepository.save(entity);
    return entityToDto(entity);
  }

  @Override
  public PageResultDto<PostResponseDto, Post> getPosts(PageRequestDto requestDto) {

    Pageable pageable = requestDto.getPageable(Sort.by("title").descending());

    BooleanBuilder booleanBuilder = getSearch(requestDto);
    Page<Post> result = postRepository.findAll(booleanBuilder, pageable);

    Function<Post, PostResponseDto> fn = (entity -> entityToDto(entity));
    return new PageResultDto<>(result, fn);
  }

  @Override
  public PostResponseDto getPostById(Long id) {
    Optional<Post> result = postRepository.findById(id);
    return result.isPresent()? entityToDto(result.get()): null;
  }

  @Override
  public void modify(Long id, PostUpdateRequestDto dto) {
    Optional<Post> result = postRepository.findById(id);
    if (result.isPresent()) {
      Post entity = result.get();
      entity.modify(
          dto.getTitle(),
          dto.getLocation()
      );
      postRepository.save(entity);
    }
  }

  @Override
  public void remove(Long id) {
    postRepository.deleteById(id);
  }

  // Todo : getSearch : at title -> o
  // Todo : getSchool
  // Todo : getCategory
  // Todo : booleanexpresstion

  private BooleanBuilder getSearch(PageRequestDto requestDto) {

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    QPost qPost = QPost.post;

    String keyword = requestDto.getKeyword();
    Long schoolId = requestDto.getSchoolId();
    Long categoryId = requestDto.getCategoryId();

    BooleanExpression expression = qPost.id.gt(0L);
    booleanBuilder.and(expression);

//    if (keyword == null || keyword.trim().length() == 0) {
//      return booleanBuilder;
//    }

    if (schoolId != null) {
      booleanBuilder.and(qPost.schoolId.eq(schoolId));
    }

    // keyword or category 임
    if (keyword != null) {
//      BooleanBuilder conditionBuilder = new BooleanBuilder();
//      conditionBuilder.or(qPost.title.contains(keyword));
//
//      booleanBuilder.and(conditionBuilder);
      booleanBuilder.and(qPost.title.contains(keyword));
    }

    if (categoryId != null) {
      booleanBuilder.and(qPost.categoryId.eq(categoryId));
    }

    return booleanBuilder;
  }
}
