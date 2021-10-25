package edu.tec.ic6821.app.user.dto;

import edu.tec.ic6821.app.user.model.User;


public class OutUserDto {
    private  Long userId;
    private  String name;

    public static OutUserDto from(User User) {
        return new OutUserDto(User.getUserId(), User.getName());
    }

    public OutUserDto(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
