package net.schoolvery.schoolveryserver.domain.chat.controller;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.*;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomFindResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomJoinResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import net.schoolvery.schoolveryserver.domain.chat.service.MemberService;
import net.schoolvery.schoolveryserver.domain.chat.service.MessageService;
import net.schoolvery.schoolveryserver.domain.chat.service.RoomService;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoomResponseDto> createChatRoom(@RequestBody RoomCreateRequestDto roomCreateRequestDto
    , HttpServletRequest request) {
        final RoomResponseDto result = roomService.createChatRoom(roomCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable UUID id, HttpServletRequest request) {
        roomService.deleteChatRoom(id);
        return ResponseEntity.ok()
                .body(null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoomResponseDto> updateChatRoom(@PathVariable UUID id, @RequestBody RoomUpdateRequestDto roomUpdateRequestDto
            , HttpServletRequest request) {
        RoomResponseDto result = roomService.updateChatRoom(id, roomUpdateRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoomResponseDto> getChatRoom(@PathVariable UUID id, HttpServletRequest request) {
        RoomResponseDto dto = roomService.getRoomById(id);
        return ResponseEntity.ok()
                .body(dto);
    }

    // [실전용] 웹 소캣 버전
    @MessageMapping("/message")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void sendTextMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto, HttpServletRequest request) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    // [테스트용] HTTP 버전
    @PostMapping("/message")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void sendTextMessage2(@RequestBody MessageCreateRequestDto messageCreateRequestDto, HttpServletRequest request) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    @GetMapping("/room")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity <List<Message>> enterChatRoom (@RequestBody MessageGetRequestDto messageGetRequestDto, HttpServletRequest request) {
        // 1. 채팅방 멤버 추가
        memberService.addMembers(messageGetRequestDto.getRoom_id(),messageGetRequestDto.getMember_id());

        // 2. 채팅방 모든 메세지 띄우기(가져오기)
        List<Message> messages = messageService.getMessages(messageGetRequestDto.getRoom_id());
        return ResponseEntity.ok().body(messages);
    }

    @PostMapping("/member")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RoomJoinResponseDto> joinChatRoom(@RequestBody RoomJoinRequestDto requestDto, HttpServletRequest request) {
        RoomJoinResponseDto responseDto = memberService.joinMemebers(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PutMapping("/member")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Boolean> exitChatRoom(@RequestBody RoomJoinRequestDto requestDto) {
        Boolean result = memberService.exitMembers(requestDto);

        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<RoomFindResponseDto>> findChatRoom(@PathVariable UUID id, HttpServletRequest request) {
        List<RoomFindResponseDto> roomFindResponseDtoList = memberService.findMember(id);

        return ResponseEntity.ok()
                .body(roomFindResponseDtoList);
    }

    @GetMapping("/room/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<JSONObject> findMessageV1(@PathVariable UUID id, HttpServletRequest request) {
        JSONObject object = messageService.findMessageV1(id);

        return ResponseEntity.ok()
                .body(object);
    }

    @GetMapping("/room/v2/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<JSONObject> findMessageV2(@PathVariable UUID id, @RequestBody MessageFindRequestDto dto, HttpServletRequest request) {
        JSONObject object = messageService.findMessageV2(id, dto);

        return ResponseEntity.ok()
                .body(object);
    }

}
