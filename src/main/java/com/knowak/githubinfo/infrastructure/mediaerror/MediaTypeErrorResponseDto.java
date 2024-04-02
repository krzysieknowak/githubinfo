package com.knowak.githubinfo.infrastructure.mediaerror;

import org.springframework.http.HttpStatus;

public record MediaTypeErrorResponseDto(int status, String Message) {

}
