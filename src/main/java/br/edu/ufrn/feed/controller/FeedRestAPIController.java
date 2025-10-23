package br.edu.ufrn.feed.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.feed.client.PostRestAPIClient;
import br.edu.ufrn.feed.record.PostDTO;
import br.edu.ufrn.feed.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedRestAPIController {

    private final FeedService feedService;

    public FeedRestAPIController(PostRestAPIClient client) {
        this.feedService = new FeedService(client);
    }

    @GetMapping(params = "limit", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<PostDTO> getLatestPosts(@RequestParam("limit") Integer limit) {
        return feedService.getLatestPosts(limit);
    }

}
