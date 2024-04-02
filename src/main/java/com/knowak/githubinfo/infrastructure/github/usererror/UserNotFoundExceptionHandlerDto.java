package com.knowak.githubinfo.infrastructure.github.usererror;

import org.springframework.http.HttpStatusCode;

record UserNotFoundExceptionHandlerDto(int status, String Message) {
}
