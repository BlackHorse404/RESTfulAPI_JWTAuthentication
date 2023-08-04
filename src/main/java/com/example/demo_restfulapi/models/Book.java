package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Book {
    private int id;
    private String name;

    public Book() {
    }

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNull(){
        return name==null ? true:false;
    }

    @Override
    public String toString() {
        return String.format("%d, %s",id,name);
    }
}
