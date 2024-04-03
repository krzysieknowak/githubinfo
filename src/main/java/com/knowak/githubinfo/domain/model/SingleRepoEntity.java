package com.knowak.githubinfo.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "repos")
public class SingleRepoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String owner;
    @Column(nullable = false)
    private String name;


    public SingleRepoEntity(String owner, String name){
        this.owner = owner;
        this.name = name;
    }
}
