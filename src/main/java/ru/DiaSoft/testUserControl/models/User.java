package ru.DiaSoft.testUserControl.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class User {
    private int userId;

    @NotEmpty(message="Name not null")
    @Size(min=2, max=30, message="Имя должно быть от 2 до 30 символов")
    private String name;

    @NotEmpty(message="Name not null")
    @Size(min=3, max=30, message="Пароль должен быть от 3 до 30 символов")
    private String pass;

    @NotEmpty(message="Email не должен быть пустым!")
    @Email(message = "Email должен быть валидным!")
    private String mail;

    public User() {
    }

    public User(int userId, String name, String pass, String mail) {
        this.userId = userId;
        this.name = name;
        this.pass = pass;
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
