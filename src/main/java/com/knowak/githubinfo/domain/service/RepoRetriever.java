package com.knowak.githubinfo.domain.service;

import com.knowak.githubinfo.domain.model.SingleRepoEntity;
import com.knowak.githubinfo.domain.repository.GithubInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoRetriever {

    private final GithubInfoRepository repository;

    public RepoRetriever(GithubInfoRepository repository) {
        this.repository = repository;
    }
    public boolean existsByOwnerName(String ownerName){
        return repository.existsByOwner(ownerName);
    }
    public List<SingleRepoEntity> findAllRepos(){
        return repository.findAll();
    }
}
