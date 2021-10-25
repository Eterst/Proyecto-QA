package edu.tec.ic6821.app.user.model;

import java.util.Objects;

public class User {
    private  Long userId;
    private  String name;


    public User(Long userId, String name) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() { return name; }

    public Long getUserId() { return userId; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User User = (User) o;
        return Objects.equals(name, User.name)
                && Objects.equals(userId, User.userId);
    }

    @Override
    public int hashCode() { return Objects.hash(name, userId); }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
