package org.komamitsu.springdatatest.domain.model;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private final Long id;
    private final String name;
    private final Integer point;

    public User(Long id, String name, Integer point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public static User create(String name, Integer point) {
        return new User(null, name, point);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPoint() {
        return point;
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
