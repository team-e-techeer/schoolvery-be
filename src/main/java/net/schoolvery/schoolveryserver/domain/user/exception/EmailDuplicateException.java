package net.schoolvery.schoolveryserver.domain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailDuplicateException extends Exception{

    public EmailDuplicateException(String message) {
        super(message);
    }
}
