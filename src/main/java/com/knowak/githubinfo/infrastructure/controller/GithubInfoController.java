package com.knowak.githubinfo.infrastructure.controller;

import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoWithBranchesResponseDto;
import com.knowak.githubinfo.infrastructure.github.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/repos")
public class GithubInfoController {

    private final GithubService service;

    public GithubInfoController(GithubService service) {
        this.service = service;
    }

    @GetMapping(value = "/{user}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SingleRepoWithBranchesResponseDto>> getFullRequiredInfo(@PathVariable String user)  {
        List<SingleRepoWithBranchesResponseDto> response = service.fetchAllUserReposWithBranches(user);
        return ResponseEntity.ok(response);
    }
}
