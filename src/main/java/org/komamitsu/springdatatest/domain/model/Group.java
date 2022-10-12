package org.komamitsu.springdatatest.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;


public class Group {
    @Id
    public final Long id;
    public final String name;
    @MappedCollection(idColumn = "group_id")
    public final Set<User> users;

    private Group(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        if (users == null) {
            users = new HashSet<>();
        }
        this.users = users;
    }

    public static Group create(String name) {
        return new Group(null, name, null);
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
