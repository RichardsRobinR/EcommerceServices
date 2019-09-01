package com.example.krishnachaitanya.ecommerceservices;

/**
 * Created by Krishna Chaitanya on 25-08-2019.
 */

public class User {

    public String name,email,phone,id;

    public User()
    {

    }

    public User(String name, String email, String phone, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {return name;}

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }
}
