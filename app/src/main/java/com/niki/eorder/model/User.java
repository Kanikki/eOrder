package com.niki.eorder.model;

public class User {
    private int eBalance;
    private String email;
    private String name;

    public int geteBalance() {
        return eBalance;
    }

    public void seteBalance(int eBalance) {
        this.eBalance = eBalance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
