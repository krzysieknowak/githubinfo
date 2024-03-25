package com.knowak.githubinfo.mediaerror;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class MediaTypeErrorHandler {
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<MediaTypeErrorResponseDto> handleMediaTypeError(HttpMediaTypeNotAcceptableException exception) {
        String message = exception.getMessage();
        MediaTypeErrorResponseDto responseDto = new MediaTypeErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
    }
}

