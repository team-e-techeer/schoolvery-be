package net.schoolvery.schoolveryserver.domain.chat.controller;

import net.schoolvery.schoolveryserver.domain.chat.dto.request.RoomCreateRequestDto;
import net.schoolvery.schoolveryserver.domain.chat.dto.response.ChatResponseDto;
import net.schoolvery.schoolveryserver.domain.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated  //?
@RestController
@RequestMapping(value = "/api/v1/chats")
@RequiredArgsConstructor
@Tag(name = "Chat Controller", description = "Chat Controller REST API")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<ChatResponseDto> createChatRoom(@RequestParam RoomCreateRequestDto roomCreateRequestDto) {
        final ChatResponseDto result = chatService.createChatRoom(roomCreateRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @ApiOperation(value = "채팅방 조회")
//    @ApiImplicitParam(name = "roomId", value = "채팅방 PK", example = "1", required = true)
//    @DeleteMapping("/chat/rooms/{roomId}")
//    public ResponseEntity<ResultResponse> inquireChatRoom(@NotNull(message = "채팅방 PK는 필수입니다.") @PathVariable Long roomId) {
//        final ChatRoomInquireResponse response = chatService.inquireRoom(roomId);
//
//        return ResponseEntity.ok(ResultResponse.of(INQUIRE_CHAT_ROOM_SUCCESS, response));
//    }

//    @ApiOperation(value = "채팅방 삭제")
//    @ApiImplicitParam(name = "roomId", value = "채팅방 PK", example = "1", required = true)
//    @DeleteMapping("/chat/rooms/hide")
//    public ResponseEntity<ResultResponse> hideChatRoom(@NotNull(message = "채팅방 PK는 필수입니다.") @RequestParam Long roomId) {
//        final JoinRoomDeleteResponse response = chatService.deleteJoinRoom(roomId);
//
//        return ResponseEntity.ok(ResultResponse.of(DELETE_JOIN_ROOM_SUCCESS, response));
//    }

//    @ApiOperation(value = "채팅방 목록 페이징 조회", notes = "페이지당 10개씩 조회할 수 있습니다.")
//    @ApiImplicitParam(name = "page", value = "페이지", example = "1", required = true)
//    @GetMapping("/chat/rooms")
//    public ResponseEntity<ResultResponse> getJoinRooms(@NotNull(message = "페이지는 필수입니다.") @RequestParam Integer page) {
//        final Page<JoinRoomDTO> response = chatService.getJoinRooms(page);
//
//        return ResponseEntity.ok(ResultResponse.of(GET_JOIN_ROOMS_SUCCESS, response));
//    }
//
//    @ApiOperation(value = "채팅방 메시지 목록 페이징 조회", notes = "페이지당 10개씩 조회할 수 있습니다.")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roomId", value = "채팅방 PK", example = "1", required = true),
//            @ApiImplicitParam(name = "page", value = "페이지", example = "1", required = true)
//    })
//    @GetMapping("/chat/rooms/{roomId}/messages")
//    public ResponseEntity<ResultResponse> getChatMessages(
//            @NotNull(message = "채팅방 PK는 필수입니다.") @PathVariable Long roomId,
//            @NotNull(message = "페이지는 필수입니다.") @RequestParam Integer page) {
//        final Page<MessageDTO> response = chatService.getChatMessages(roomId, page);
//
//        return ResponseEntity.ok(ResultResponse.of(GET_CHAT_MESSAGES_SUCCESS, response));
//    }


//    @MessageMapping("/messages")
//    public void sendTextMessage(@RequestBody MessageRequest request) {
//        chatService.sendMessage(request);
//    }
//
//    @MessageMapping("/messages/indicate")
//    public void indicate(@RequestBody IndicateRequest request) {
//        chatService.indicate(request);
//    }
//}


}
