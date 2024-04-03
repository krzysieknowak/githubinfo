package com.knowak.githubinfo.infrastructure.github.proxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleRepoDto(String name, OwnerDto owner, boolean fork){
}
