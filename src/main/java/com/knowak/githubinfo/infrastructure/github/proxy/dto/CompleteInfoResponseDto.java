package com.knowak.githubinfo.infrastructure.github.proxy.dto;

public record CompleteInfoResponseDto(SingleRepoDto[] repo, SingleBranchDto[] branch) {
}
