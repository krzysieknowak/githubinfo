package com.knowak.githubinfo.github.proxy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OwnerDto(String login) {
}