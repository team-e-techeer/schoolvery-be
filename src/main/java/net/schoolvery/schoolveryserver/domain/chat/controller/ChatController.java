package net.schoolvery.schoolveryserver.domain.chat.controller;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.MessageCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomUpdateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.MessageResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.RoomResponseDto;
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

    @MessageMapping("/message")
    public void sendTextMessage(@RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageService.sendMessage(messageCreateRequestDto);
    }

    // 특정 채팅방 입장
    @GetMapping("/room/{id}")
    public ResponseEntity <List<MessageResponseDto>> getChatMessages (@PathVariable UUID room_id, @RequestBody UUID member_id) {
        // 1. 채팅방 멤버 추가
        //memberService.addMembers(room_id,member_id);
        // 2. 채팅방 모든 메세지 띄우기
        log.info("WS 도잔?");
        List<MessageResponseDto> dto = messageService.getMessages(room_id);
        return ResponseEntity.ok()
                .body(dto);
    }
}