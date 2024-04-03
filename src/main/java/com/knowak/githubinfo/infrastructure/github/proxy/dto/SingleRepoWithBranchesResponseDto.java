package com.knowak.githubinfo.infrastructure.github.proxy.dto;

public record SingleRepoWithBranchesResponseDto(String name, OwnerDto owner, SingleBranchDto[] branches) {
}
