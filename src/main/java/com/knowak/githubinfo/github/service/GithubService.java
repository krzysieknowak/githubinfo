package com.knowak.githubinfo.github.service;

import com.knowak.githubinfo.github.proxy.GithubProxy;
import com.knowak.githubinfo.github.proxy.dto.response.*;
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
    private final Mapper mapper;

    public GithubService(GithubProxy githubProxy, Mapper mapper) {
        this.githubProxy = githubProxy;
        this.mapper = mapper;
    }
    public List<SingleRepoWithBranchesResponseDto> fetchAllUserReposWithBranches(String user) {
        SingleRepoDto[] repos = fetchAllUserRepos(user);

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
        String json = githubProxy.makeGetRequestForRepos(user);
        if(json == null){
            log.error("Fetched Json is null when getting repos");
            return new SingleRepoDto[]{new SingleRepoDto("emptyRepoService", new OwnerDto("emptyRepoService"), false)};
        }
        SingleRepoDto[] response = mapper.mapJsonToSingleRepoDto(json);
        log.info("Fetched repos: " + response);
        return response;
    }
    private SingleBranchDto[] fetchAllBranchesFromUserRepos(String user, String repo){
        String json = githubProxy.makeGetRequestForBranches(user, repo);
        if(json == null){
            log.error("Fetched Json is null when getting branches");
            return new SingleBranchDto[]{new SingleBranchDto("emptyBranchService",new CommitDto("emptyBranchService") )};
        }
        SingleBranchDto[] response = mapper.mapJsonToSingleBranchDto(json);
        log.info("Fetched branches: " + response);
        return response;
    }
}
