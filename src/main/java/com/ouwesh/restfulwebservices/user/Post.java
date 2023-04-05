package com.ouwesh.restfulwebservices.user;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import static jakarta.persistence.GenerationType.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
