package br.edu.ufrn.feed.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import br.edu.ufrn.feed.record.PostDTO;

@Component
public class PostRestAPIClient implements PostClient {

    private final RestTemplate restTemplate;
    
    public PostRestAPIClient(
        @LoadBalanced RestTemplate restTemplate,
        @Value("${post.restapi.base-url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
    }

    @Override
    public List<PostDTO> getAll() {
        return Arrays.asList(restTemplate.getForObject("/posts", PostDTO[].class));
    }

}
