package com.jony.intlcc.domain;

/**
 * Created by jony on 2018/4/2.
 */
public class RegisterMessage {

    private String user;
    private String password;

    public RegisterMessage() {
    }

    public RegisterMessage(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
