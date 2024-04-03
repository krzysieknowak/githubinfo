package com.knowak.githubinfo.domain.repository;

import com.knowak.githubinfo.domain.model.SingleRepoEntity;
import com.knowak.githubinfo.infrastructure.github.proxy.dto.SingleRepoDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GithubInfoRepository extends Repository<SingleRepoEntity, Long> {

    SingleRepoEntity save(SingleRepoEntity singleRepo);
    boolean existsByOwner(String owner);
    @Query("SELECT r FROM SingleRepoEntity r")
    List<SingleRepoEntity> findAll();
}
