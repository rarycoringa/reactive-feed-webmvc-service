package br.edu.ufrn.feed.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import br.edu.ufrn.feed.record.PostDTO;

@Component
public class PostGraphQLClient implements PostClient {

    private final HttpSyncGraphQlClient client;

    public PostGraphQLClient(
        @LoadBalanced RestTemplate restTemplate,
        @Value("${post.graphql.base-url}") String baseUrl
    ) {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        this.client = HttpSyncGraphQlClient.create(RestClient.create(restTemplate));
    }

    @Override
    public List<PostDTO> getAll() {
        String query = """
            query GetAll {
                getAll {
                    content
                    user {
                        name
                    }
                    createdAt
                    comments {
                        content
                        user {
                            name
                        }
                    }
                }
            }
            """;

        return client.document(query)
            .retrieve("getAll")
            .toEntityList(PostDTO.class)
            .block();
    }

}
