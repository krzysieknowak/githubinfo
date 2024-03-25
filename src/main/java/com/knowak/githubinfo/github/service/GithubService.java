package com.knowak.githubinfo.github.service;

import com.knowak.githubinfo.github.proxy.GithubProxy;
import com.knowak.githubinfo.github.proxy.dto.SingleBranchDto;
import com.knowak.githubinfo.github.proxy.dto.SingleRepoDto;
import com.knowak.githubinfo.github.proxy.dto.SingleRepoWithBranchesResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GithubService {
    private final GithubProxy githubProxy;

    public GithubService(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public List<SingleRepoWithBranchesResponseDto> fetchAllUserReposWithBranches(String user) {
        SingleRepoDto[] repos = fetchAllUserRepos(user);
        if(repos == null){
            return null;
        }
        List<SingleRepoWithBranchesResponseDto> reposWithBranches = new ArrayList<>();

        return Arrays.stream(repos)
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    SingleBranchDto[] branches = fetchAllBranchesFromUserRepos(user, repo.name());
                    return new SingleRepoWithBranchesResponseDto(repo.name(), repo.owner(), branches);
                })
                .collect(Collectors.toList());
    }
    private SingleRepoDto[] fetchAllUserRepos(String user){
        SingleRepoDto[] response = githubProxy.makeGetRequestForRepos(user);
        if(response == null){
            log.error("Fetched response is null when getting repos");
            return null;
        }
        log.info("Fetched repos: " + response);
        return response;
    }
    private SingleBranchDto[] fetchAllBranchesFromUserRepos(String user, String repo){
        SingleBranchDto[] response = githubProxy.makeGetRequestForBranches(user, repo);
        if(response == null){
            log.error("Fetched response is null when getting branches");
            return null;
        }
        log.info("Fetched branches: " + response);
        return response;
    }
}
