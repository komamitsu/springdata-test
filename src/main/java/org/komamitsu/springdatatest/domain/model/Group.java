package org.komamitsu.springdatatest.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Group {
    @Id
    private final Long id;
    private final String name;
    @MappedCollection(idColumn = "group_id", keyColumn = "id")
    private final List<User> users;

    private Group(Long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        if (users == null) {
            users = new ArrayList<>();
        }
        this.users = users;
    }

    public static Group create(String name) {
        return new Group(null, name, new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
