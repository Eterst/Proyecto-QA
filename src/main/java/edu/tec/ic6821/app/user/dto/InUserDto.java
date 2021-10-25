package edu.tec.ic6821.app.user.dto;


public class InUserDto {
    private  Long userId;
    private  String name;


    public InUserDto(Long userId, String name) {
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
