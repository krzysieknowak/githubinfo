package com.knowak.githubinfo.infrastructure.github.proxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OwnerDto(String login) {
}