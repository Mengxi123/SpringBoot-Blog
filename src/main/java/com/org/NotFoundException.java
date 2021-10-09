package com.org;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author MengXi
 * @date 2021/10/9 17:19
 *
 * ResponseStatus指定异常状态码 NOT_FOUND(404, HttpStatus.Series.CLIENT_ERROR, "Not Found"),
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
