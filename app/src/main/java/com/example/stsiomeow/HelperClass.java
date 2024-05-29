package com.example.stsiomeow;

public class HelperClass {
    public String user, email, pass, address, firstName, lastName;
    public Boolean isAlumni, rememberMe;

    public HelperClass(String user, String email, String pass, String address, String firstName, String lastName, Boolean isAlumni) {
        this.user = user;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAlumni = isAlumni;
    }

    public HelperClass(String user, String email, String pass, Boolean rememberMe){
        this.user = user;
        this.email = email;
        this.pass = pass;
        this.rememberMe = rememberMe;
    }

    public HelperClass() {
    }
    public Boolean getAlumni() {
        return isAlumni;
    }

    public void setAlumni(Boolean alumni) {
        isAlumni = alumni;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
