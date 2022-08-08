package net.schoolvery.schoolveryserver.domain.chat.controller;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.*;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomFindResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomJoinResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.service.MemberService;
import net.schoolvery.schoolveryserver.domain.chat.service.MessageService;
import net.schoolvery.schoolveryserver.domain.chat.service.RoomService;
import net.schoolvery.schoolveryserver.global.common.dto.PageRequestDto;
import net.schoolvery.schoolveryserver.global.common.dto.PageResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/chats")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "Chat Controller", description = "Chat Controller REST API")
public class ChatController {
    private final RoomService roomService;
    private final MessageService messageService;
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<RoomResponseDto> createChatRoom(@RequestBody RoomCreateRequestDto roomCreateRequestDto
    , HttpServletRequest request) {
        final RoomResponseDto result = roomService.createChatRoom(roomCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable UUID id, HttpServletRequest request) {
        roomService.deleteChatRoom(id);
        return ResponseEntity.ok()
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateChatRoom(@PathVariable UUID id, @RequestBody RoomUpdateRequestDto roomUpdateRequestDto
            , HttpServletRequest request) {
        RoomResponseDto result = roomService.updateChatRoom(id, roomUpdateRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping
    public ResponseEntity<PageResultDto> list(PageRequestDto pageRequestDto, HttpServletRequest request) {
        PageResultDto result = roomService.getRooms(pageRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<RoomFindResponseDto>> getRoomsbyUserId(@PathVariable UUID id, HttpServletRequest request) {
        List<RoomFindResponseDto> roomFindResponseDtoList = memberService.findMember(id);
        return ResponseEntity.ok()
                .body(roomFindResponseDtoList);
    }

    // [실전용] 웹 소캣 버전
    @MessageMapping("/message")
    public void sendTextMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto, HttpServletRequest request) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    // [테스트용] HTTP 버전
    @PostMapping("/message")
    public void sendTextMessage2(@RequestBody MessageCreateRequestDto messageCreateRequestDto, HttpServletRequest request) {
        messageService.sendMessage(messageCreateRequestDto);
    }

//    @GetMapping("/room")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity <List<Message>> enterChatRoom (@RequestBody MessageGetRequestDto messageGetRequestDto, HttpServletRequest request) {
//        // 1. 채팅방 멤버 추가
//        memberService.addMembers(messageGetRequestDto.getRoom_id(),messageGetRequestDto.getMember_id());
//
//        // 2. 채팅방 모든 메세지 띄우기(가져오기)
//        List<Message> messages = messageService.getMessages(messageGetRequestDto.getRoom_id());
//        return ResponseEntity.ok().body(messages);
//    }

    @PostMapping("/member")
    public ResponseEntity<RoomJoinResponseDto> joinChatRoom(@RequestBody RoomJoinRequestDto requestDto, HttpServletRequest request) {
        RoomJoinResponseDto responseDto = memberService.joinMemebers(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PutMapping("/member")
    public ResponseEntity<Boolean> exitChatRoom(@RequestBody RoomJoinRequestDto requestDto) {
        Boolean result = memberService.exitMembers(requestDto);

        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/member/{roomId}")
    public ResponseEntity<PageResultDto> whoseInChatRoom(@PathVariable UUID roomId, PageRequestDto pageRequestDto,HttpServletRequest request) {
        PageResultDto result = memberService.checkMembers(roomId,pageRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<JSONObject> findMessageV1(@PathVariable UUID id, HttpServletRequest request) {
        JSONObject object = messageService.findMessageV1(id);

        return ResponseEntity.ok()
                .body(object);
    }

    @GetMapping("/room/v2/{id}")
    public ResponseEntity<JSONObject> findMessageV2(@PathVariable UUID id, @RequestBody MessageFindRequestDto dto, HttpServletRequest request) {
        JSONObject object = messageService.findMessageV2(id, dto);

        return ResponseEntity.ok()
                .body(object);
    }

    @GetMapping("/postId/{id}")
    public ResponseEntity<RoomResponseDto> getChatroomByPostId(@PathVariable UUID postId, HttpServletRequest request) {
        RoomResponseDto room = roomService.getByPostId(postId);

        return ResponseEntity.ok()
            .body(room);
    }

}
