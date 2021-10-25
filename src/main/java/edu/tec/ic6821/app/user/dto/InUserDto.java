package edu.tec.ic6821.app.user.dto;


public class InUserDto {
    private String name;

    private String text;


    public InUserDto(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() { return name; }

    public String getText() { return  text; }

}
