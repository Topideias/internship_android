package com.example.topideias.internshipandroid;

import java.io.Serializable;

/**
 * Created by maau_ on 10/02/2017.
 */

public class Person implements Serializable {

    private static final long serialVersionUID = 54984991;

    private long id;
    private String name, email, country;

    public Person(){}

    public Person(long id, String name, String email, String country){
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
