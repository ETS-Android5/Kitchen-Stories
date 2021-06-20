package com.example.kitchenstories.Model;

public class User {

    String url_imageUser;
    String displayNameUser;
    String emailUser;
    String idUser;

    public User() {
    }

    public User(String url_imageUser,
                String displayNameUser,
                String emailUser, String
                        idUser) {
        this.url_imageUser = url_imageUser;
        this.displayNameUser = displayNameUser;
        this.emailUser = emailUser;
        this.idUser = idUser;
    }

    public String getUrl_imageUser() {
        return url_imageUser;
    }

    public void setUrl_imageUser(String url_imageUser) {
        this.url_imageUser = url_imageUser;
    }

    public String getDisplayNameUser() {
        return displayNameUser;
    }

    public void setDisplayNameUser(String displayNameUser) {
        this.displayNameUser = displayNameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
