package com.ouwesh.restfulwebservices.user;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post save(int userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id-" + userId));
        post.setUser(user);
        return postRepository.save(post);
    }
}
