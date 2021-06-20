package com.example.kitchenstories.Model.Recipe;

public class Likes {

    String uID;
    String emailUser;

    public Likes() {
    }

    public Likes(String uID, String emailUser) {
        this.uID = uID;
        this.emailUser = emailUser;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
