package com.knowak.githubinfo.github.proxy.dto.response;

import java.util.List;

public record SingleRepoWithBranchesResponseDto(String name, OwnerDto owner, SingleBranchDto[] branches) {
}
