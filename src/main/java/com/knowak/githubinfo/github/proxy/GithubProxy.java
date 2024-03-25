package com.knowak.githubinfo.github.proxy;


import com.knowak.githubinfo.github.proxy.dto.SingleBranchDto;
import com.knowak.githubinfo.github.proxy.dto.SingleRepoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class GithubProxy {
    //https://api.github.com/users/kalqa/repos

    private final RestTemplate restTemplate;

    public GithubProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${github.proxy.scheme}")
    private String scheme;
    @Value("${github.proxy.host}")
    private String host;

    public SingleRepoDto[] makeGetRequestForRepos(String user){
        //https://api.github.com/users/kalqa/repos
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path("/users/" + user + "/repos");
        try{
            ResponseEntity<SingleRepoDto[]> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    SingleRepoDto[].class);
            return exchange.getBody();
        } catch (RestClientResponseException e) {
            log.error(e.getStatusText() + " " + e.getStatusCode().value());
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public SingleBranchDto[] makeGetRequestForBranches(String user, String repo){
        //https://api.github.com/repos/kalqa/03-open-feign/branches

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path("/repos/" + user + "/" + repo + "/branches");
        try{
            ResponseEntity<SingleBranchDto[]> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    SingleBranchDto[].class);
            return exchange.getBody();
        } catch (RestClientResponseException e) {
            log.error(e.getStatusText() + " " + e.getStatusCode().value());
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}