package com.knowak.githubinfo.github.proxy.dto;

public record CompleteInfoResponseDto(SingleRepoDto[] repo, SingleBranchDto[] branch) {
}
