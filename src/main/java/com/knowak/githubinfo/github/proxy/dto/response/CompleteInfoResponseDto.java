package com.knowak.githubinfo.github.proxy.dto.response;

public record CompleteInfoResponseDto(SingleRepoDto[] repo, SingleBranchDto[] branch) {
}
