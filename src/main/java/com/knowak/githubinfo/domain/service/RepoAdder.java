package com.knowak.githubinfo.domain.service;

import com.knowak.githubinfo.domain.model.SingleRepoEntity;
import com.knowak.githubinfo.domain.repository.GithubInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RepoAdder {

    private final GithubInfoRepository repository;

    public RepoAdder(GithubInfoRepository repository) {
        this.repository = repository;
    }
    public SingleRepoEntity addRepo(SingleRepoEntity repo){
        return repository.save(repo);
    }
}
