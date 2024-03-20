package com.knowak.githubinfo.github.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowak.githubinfo.github.proxy.dto.response.CommitDto;
import com.knowak.githubinfo.github.proxy.dto.response.OwnerDto;
import com.knowak.githubinfo.github.proxy.dto.response.SingleBranchDto;
import com.knowak.githubinfo.github.proxy.dto.response.SingleRepoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Mapper {

    private final ObjectMapper objectMapper;
    public Mapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    SingleRepoDto[] mapJsonToSingleRepoDto(String json){
        try {
            return objectMapper.readValue(json, SingleRepoDto[].class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return new SingleRepoDto[]{new SingleRepoDto("emptyRepoNameMapper", new OwnerDto("emptyOwnerMapper"), false)};
        }
    }
    SingleBranchDto[] mapJsonToSingleBranchDto(String json){
        try {
            return objectMapper.readValue(json, SingleBranchDto[].class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return new SingleBranchDto[]{new SingleBranchDto("emptyBranchNameMapper",new CommitDto("emptyShaMapper"))};
        }
    }
}
