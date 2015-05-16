package com.example.hellolibrary.model;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 6676944858002023050L;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name is " + name + ", age is " + age;
    }

}
