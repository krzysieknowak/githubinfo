package com.knowak.githubinfo.github.proxy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleRepoDto(String name, OwnerDto owner, boolean fork){
}
