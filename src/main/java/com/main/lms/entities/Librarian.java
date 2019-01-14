package com.main.lms.entities;

public class Librarian {
    private int lid;
    private String username;
    private String password;

    public Librarian(int lid,String username,String password){
        this.lid=lid;
        this.username=username;
        this.password=password;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
