package com.knowak.githubinfo.infrastructure.github.service;

import com.knowak.githubinfo.domain.model.SingleRepoEntity;
import com.knowak.githubinfo.domain.repository.GithubInfoRepository;
import com.knowak.githubinfo.domain.service.RepoAdder;
import com.knowak.githubinfo.domain.service.RepoRetriever;
import com.knowak.githubinfo.infrastructure.github.proxy.GithubProxy;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleBranchDto;
import com.knowak.githubinfo.infrastructure.github.usererror.UserNotFoundException;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoDto;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoWithBranchesResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.knowak.githubinfo.domain.service.Mapper.mapFromDtosListToEntityList;

@Service
@Log4j2
public class GithubService {
    private final GithubProxy githubProxy;
    private final RepoAdder repoAdder;
    private final RepoRetriever repoRetriever;

    public GithubService(GithubProxy githubProxy, RepoAdder repoAdder, RepoRetriever repoRetriever) {
        this.githubProxy = githubProxy;
        this.repoAdder = repoAdder;
        this.repoRetriever = repoRetriever;
    }

    public List<SingleRepoWithBranchesResponseDto> fetchAllUserReposWithBranches(String user) {
        SingleRepoDto[] repos = fetchAllUserReposAndSaveToDatabase(user);
        if(repos == null){
            throw new UserNotFoundException("User does not exist");
        }
        return Arrays.stream(repos)
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    SingleBranchDto[] branches = fetchAllBranchesFromUserRepos(user, repo.name());
                    return new SingleRepoWithBranchesResponseDto(repo.name(), repo.owner(), branches);
                })
                .collect(Collectors.toList());
    }
    private SingleRepoDto[] fetchAllUserReposAndSaveToDatabase(String user){
        SingleRepoDto[] response = githubProxy.makeGetRequestForRepos(user);
        if(response == null){
            log.error("Fetched response is null when getting repos");
            return null;
        }
        if(!repoRetriever.existsByOwnerName(user)){
            List<SingleRepoDto> singleRepoDtos = Arrays.asList(response);
            List<SingleRepoEntity> singleRepoEntities = mapFromDtosListToEntityList(singleRepoDtos);
            singleRepoEntities.forEach(repoAdder::addRepo);
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
