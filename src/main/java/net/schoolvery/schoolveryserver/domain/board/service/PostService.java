package net.schoolvery.schoolveryserver.domain.board.service;

import java.util.UUID;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.PostResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Category;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

public interface PostService {
  PostResponseDto create(PostCreateRequestDto dto);
  PageResultDto<PostResponseDto, Post> getPosts(PageRequestDto requestDto);
  PostResponseDto getPostById(Long id);
  void modify(Long id, PostUpdateRequestDto dto);
  void remove(Long id);

  PageResultDto<PostResponseDto, Post> getPostsByUserId(UUID userId, PageRequestDto requestDto);

  default Post createDtoToEntity(PostCreateRequestDto dto){

    Post entity = Post.builder()
        .userId(dto.getUserId())
        .title(dto.getTitle())
        .location(dto.getLocation())
        .schoolId(dto.getSchoolId())
        .category(Category.builder().id(dto.getCategoryId()).build())
        .deadline(dto.getDeadline())
        .peopleNum(dto.getPeopleNum())
        .deliveryFee(dto.getDeliveryFee())
        .content(dto.getContent())
        .store(dto.getStore())
        .build();
    return entity;
  }

  default Post updateDtoToEntity(PostUpdateRequestDto dto){
    Post entity = Post.builder()
        .title(dto.getTitle())
        .location(dto.getLocation())
        .deadline(dto.getDeadline())
        .peopleNum(dto.getPeopleNum())
        .content(dto.getContent())
        .deliveryFee(dto.getDeliveryFee())
        .build();
    return entity;
  }

  default PostResponseDto entityToDto(Post entity) {
    PostResponseDto dto = PostResponseDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .location(entity.getLocation())
        .schoolId(entity.getSchoolId())
        .categoryId(entity.getCategory().getId())
        .peopleNum(entity.getPeopleNum())
        .deliveryFee(entity.getDeliveryFee())
        .content(entity.getContent())
        .store(entity.getStore())
        .build();
    return dto;
  }
}
