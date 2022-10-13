package org.komamitsu.springdatatest.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema = "public", value = "user")
public class User {
    @Id
    public final Long id;
    public final String name;
    public final Integer point;

    public User(Long id, String name, Integer point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public static User create(String name, Integer point) {
        return new User(null, name, point);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}
