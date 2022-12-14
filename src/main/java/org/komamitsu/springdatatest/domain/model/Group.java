package org.komamitsu.springdatatest.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;


@Table(schema = "public", value = "group")
public class Group {
    @Id
    public final Long id;
    public final String name;

    @MappedCollection(idColumn = "group_id")
    public final Set<User> users;

    public Group(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        if (users == null) {
            users = new HashSet<>();
        }
        this.users = users;
    }

    public Group withName(String name) {
        return new Group(this.id, name, this.users);
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
