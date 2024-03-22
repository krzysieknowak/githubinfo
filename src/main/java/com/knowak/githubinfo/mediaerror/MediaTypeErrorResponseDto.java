package com.knowak.githubinfo.mediaerror;

import org.springframework.http.HttpStatus;

public record MediaTypeErrorResponseDto(HttpStatus status, String Message) {

}
