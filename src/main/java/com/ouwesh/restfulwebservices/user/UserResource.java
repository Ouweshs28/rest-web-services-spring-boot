package com.ouwesh.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    private final PostService postService;

    public UserResource(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUser(@PathVariable int id) {
        EntityModel<User> entityModel = EntityModel.of(userService.findOne(id));
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(link.withRel("all-users"));
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user) {
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserPosts(id));
    }

    @PostMapping("{id}/posts")
    public ResponseEntity<Void> createUserPost(@PathVariable int id, @RequestBody @Valid Post post) {
        Post savedPost = postService.save(id, post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
