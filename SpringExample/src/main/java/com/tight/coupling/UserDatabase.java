package com.tight.coupling;

// A - MySQL, PostgresSQL
// B - Web Service, MongoDB

public class UserDatabase {
    public String getUserDetails(){

        //Directly access database here
        return "User Details From Database";
    }
}
