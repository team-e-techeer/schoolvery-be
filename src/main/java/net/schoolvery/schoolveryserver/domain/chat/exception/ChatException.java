package net.schoolvery.schoolveryserver.domain.chat.exception;

public class ChatException extends  NullPointerException{
    public String RoomNotFoundException(){
        String msg = "Room does not exists.";
        return msg;
    }
}
