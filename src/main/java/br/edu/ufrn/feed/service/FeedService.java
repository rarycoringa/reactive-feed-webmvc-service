package br.edu.ufrn.feed.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ufrn.feed.client.PostClient;
import br.edu.ufrn.feed.record.PostDTO;

public class FeedService {

    private final PostClient postClient;

    public FeedService(
        PostClient postClient
    ) {
        this.postClient = postClient;
    }

    public List<PostDTO> getLatestPosts(Integer limit) {
        List<PostDTO> allPosts = postClient.getAll();
        
        if (allPosts == null || allPosts.isEmpty()) {
            return Collections.emptyList();
        }
        int end = Math.min(limit, allPosts.size());
        return new ArrayList<>(allPosts.subList(0, end));
    }

}
