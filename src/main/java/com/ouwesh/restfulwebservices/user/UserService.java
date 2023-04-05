package com.ouwesh.restfulwebservices.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(int id) {
       return userRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("id-" + id));
    }

    public User deleteById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id));
        userRepository.delete(user);
        return user;
    }

    public List<Post> getUserPosts(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id-" + id))
                .getPosts();
    }
}
