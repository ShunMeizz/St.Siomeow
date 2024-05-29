//package com.example.stsiomeow;
//
//public class HelperClassBuilder {
//    private String user;
//    String pass;
//    String email;
//    String firstName;
//    String lastName;
//    String address;
//
//    public HelperClassBuilder(String user){
//        this.user = user;
//    }
//    public HelperClassBuilder firstName (String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public HelperClassBuilder lastName (String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public HelperClassBuilder email (String email) {
//        this.email = email;
//        return this;
//    }
//
//    public HelperClassBuilder password (String pass) {
//        this.pass = pass;
//        return this;
//    }
//
//    public HelperClassBuilder address(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public HelperClass createHelperClass() {
//        return new HelperClass(this);
//    }
//}