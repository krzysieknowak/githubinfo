package com.knowak.githubinfo.github.proxy.dto;

public record SingleRepoWithBranchesResponseDto(String name, OwnerDto owner, SingleBranchDto[] branches) {
}
