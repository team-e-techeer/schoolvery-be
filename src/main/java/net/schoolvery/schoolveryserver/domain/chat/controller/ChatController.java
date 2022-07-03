package net.schoolvery.schoolveryserver.domain.chat.controller;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.*;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomJoinResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.entity.Message;
import net.schoolvery.schoolveryserver.domain.chat.service.MemberService;
import net.schoolvery.schoolveryserver.domain.chat.service.MessageService;
import net.schoolvery.schoolveryserver.domain.chat.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<RoomResponseDto> createChatRoom(@RequestBody RoomCreateRequestDto roomCreateRequestDto) {
        final RoomResponseDto result = roomService.createChatRoom(roomCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable UUID id) {
        roomService.deleteChatRoom(id);
        return ResponseEntity.ok()
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateChatRoom(@PathVariable UUID id, @RequestBody RoomUpdateRequestDto roomUpdateRequestDto) {
        RoomResponseDto result = roomService.updateChatRoom(id, roomUpdateRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getChatRoom(@PathVariable UUID id) {
        RoomResponseDto dto = roomService.getRoomById(id);
        return ResponseEntity.ok()
                .body(dto);
    }

    // [실전용] 웹 소캣 버전
    @MessageMapping("/message")
    public void sendTextMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    // [테스트용] HTTP 버전
    @PostMapping("/message")
    public void sendTextMessage2(@RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    @GetMapping("/room")
    public ResponseEntity <List<Message>> enterChatRoom (@RequestBody MessageGetRequestDto messageGetRequestDto) {
        // 1. 채팅방 멤버 추가
        memberService.addMembers(messageGetRequestDto.getRoom_id(),messageGetRequestDto.getMember_id());

        // 2. 채팅방 모든 메세지 띄우기(가져오기)
        List<Message> messages = messageService.getMessages(messageGetRequestDto.getRoom_id());
        return ResponseEntity.ok().body(messages);
    }

    @PostMapping("/member")
    public ResponseEntity<RoomJoinResponseDto> joinChatRoom(@RequestBody RoomJoinRequestDto requestDto) {
        RoomJoinResponseDto responseDto = memberService.joinMemebers(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }
}
