package com.knowak.githubinfo.mediaerror;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class MediaTypeErrorHandler {
//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public MediaTypeErrorResponseDto handleMediaTypeError(HttpMediaTypeNotAcceptableException exception) {
//        String message = exception.getMessage();
//        return new MediaTypeErrorResponseDto(HttpStatus.NOT_ACCEPTABLE, message);
//
//    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}

