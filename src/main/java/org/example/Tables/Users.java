package org.example.Tables;

public class Users {
    private int idUser;
    private String username;
    private String passwordUser;
    private String fullNameUser;
    private String roleUser;

    public Users(int idUser, String username, String passwordUser, String fullNameUser, String roleUser) {
        this.idUser = idUser;
        this.username = username;
        this.passwordUser = passwordUser;
        this.fullNameUser = fullNameUser;
        this.roleUser = roleUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }
}