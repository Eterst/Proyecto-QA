package edu.tec.ic6821.app.user.dto;

import edu.tec.ic6821.app.user.model.User;


public class OutUserDto {
    private final Long userId;
    private final String name;

    public static OutUserDto from(User User) {
        return new OutUserDto(User.getId(), User.getName());
    }

    public OutUserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
}
