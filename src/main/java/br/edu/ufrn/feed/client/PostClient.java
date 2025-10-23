package br.edu.ufrn.feed.client;

import java.util.List;

import br.edu.ufrn.feed.record.PostDTO;

public interface PostClient {
    public List<PostDTO> getAll();
}
