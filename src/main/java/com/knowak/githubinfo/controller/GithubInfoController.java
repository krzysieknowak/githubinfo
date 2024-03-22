package com.knowak.githubinfo.controller;

import com.knowak.githubinfo.github.proxy.dto.response.SingleRepoWithBranchesResponseDto;
import com.knowak.githubinfo.github.service.GithubService;
import com.knowak.githubinfo.github.usererror.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
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

    @GetMapping(value = "/{user}", produces = "application/json")
    public ResponseEntity<List<SingleRepoWithBranchesResponseDto>> getFullRequiredInfo(@PathVariable String user)  {

        List<SingleRepoWithBranchesResponseDto> response = service.fetchAllUserReposWithBranches(user);
        if(response == null){
            throw new UserNotFoundException("User does not exist");
        }
        return ResponseEntity.ok(response);
    }
}
