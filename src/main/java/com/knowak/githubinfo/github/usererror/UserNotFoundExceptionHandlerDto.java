package com.knowak.githubinfo.github.usererror;

import org.springframework.http.HttpStatusCode;

record UserNotFoundExceptionHandlerDto(int status, String Message) {
}
