package com.knowak.githubinfo.mediaerror;

import org.springframework.http.HttpStatus;

public record MediaTypeErrorResponseDto(int status, String Message) {

}
