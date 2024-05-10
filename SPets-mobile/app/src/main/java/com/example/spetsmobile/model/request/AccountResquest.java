package com.example.spetsmobile.model.request;

import com.google.gson.annotations.SerializedName;

public class AccountResquest {

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("role")
    private String role;

    @SerializedName("token")
    private String token;

    public AccountResquest() {
    }

    public AccountResquest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountResquest(String fullname, String username, String password, String role) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
