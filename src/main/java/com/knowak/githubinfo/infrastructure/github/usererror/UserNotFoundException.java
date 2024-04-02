package com.knowak.githubinfo.infrastructure.github.usererror;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
