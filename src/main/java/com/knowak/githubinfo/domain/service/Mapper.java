package com.knowak.githubinfo.domain.service;

import com.knowak.githubinfo.domain.model.SingleRepoEntity;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoDto;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoFromDbResponseDto;

import java.util.List;

public class Mapper {

    public static SingleRepoEntity mapFromSingleRepoDtoToSingleRepoEntity(SingleRepoDto singleRepoDto){
        return new SingleRepoEntity(singleRepoDto.owner().login(), singleRepoDto.name());
    }
    public static List<SingleRepoEntity>mapFromDtosListToEntityList(List<SingleRepoDto> singleRepoDtos){
        return singleRepoDtos.stream()
                .map(Mapper::mapFromSingleRepoDtoToSingleRepoEntity)
                .toList();
    }
    public static SingleRepoFromDbResponseDto mapFromSingleRepoEntityToSingleRepoFromDbResponseDto
            (SingleRepoEntity singleRepo){
        return new SingleRepoFromDbResponseDto(singleRepo.getOwner(), singleRepo.getName());
    }
    public static List<SingleRepoFromDbResponseDto>mapFromEntityListToDtosList(List<SingleRepoEntity> singleRepoEntities){
        return singleRepoEntities.stream()
                .map(Mapper::mapFromSingleRepoEntityToSingleRepoFromDbResponseDto)
                .toList();
    }
}
