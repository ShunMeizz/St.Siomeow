package com.example.stsiomeow;

public class HelperClass {
    String user;
    String pass;
    String email;
    String address;
    String firstName;
    String lastName;

public HelperClass(HelperClassBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.pass = builder.pass;
    this.address = builder.address;
}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
