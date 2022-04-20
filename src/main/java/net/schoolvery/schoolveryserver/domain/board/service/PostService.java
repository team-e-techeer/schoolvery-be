package net.schoolvery.schoolveryserver.domain.board.service;

import net.schoolvery.schoolveryserver.domain.board.dto.request.PostCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.request.PostUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.board.dto.response.PostResponseDto;
import net.schoolvery.schoolveryserver.domain.board.entity.Post;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.request.HelloUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.hello.dto.response.HelloResponseDto;
import net.schoolvery.schoolveryserver.domain.hello.entity.Hello;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;

public interface PostService {
  PostResponseDto create(PostCreateRequestDto dto);
  PageResultDto<PostResponseDto, Post> getAllPost(PageRequestDto requestDto);
  PostResponseDto getPostById(Long id);
  void modify(Long id, PostUpdateRequestDto dto);
  void remove(Long id);


  default Post createDtoToEntity(PostCreateRequestDto dto){
    Post entity = Post.builder()
        .title(dto.getTitle())
        .location(dto.getLocation())
        .people_num(dto.getPeople_num())
        .delivery_fee(dto.getDelivery_fee())
        .content(dto.getContent())
        .store(dto.getStore())
        .build();
    return entity;
  }

  default Post updateDtoToEntity(PostUpdateRequestDto dto){
    Post entity = Post.builder()
        .title(dto.getTitle())
        .location(dto.getLocation())
        .people_num(dto.getPeople_num())
        .content(dto.getContent())
        .delivery_fee(dto.getDelivery_fee())
        .build();
    return entity;
  }

  default PostResponseDto entityToDto(Post entity) {
    PostResponseDto dto = PostResponseDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .location(entity.getLocation())
        .people_num(entity.getPeople_num())
        .delivery_fee(entity.getDelivery_fee())
        .content(entity.getContent())
        .store(entity.getStore())
        .build();
    return dto;
  }
}
