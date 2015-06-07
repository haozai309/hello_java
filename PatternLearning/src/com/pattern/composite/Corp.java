package com.pattern.composite;

public abstract class Corp {

    private String mName = "";

    private String mPosition = "";

    private int mSalary = 0;

    public Corp(String name, String position, int salary) {
        mName = name;
        mPosition = position;
        mSalary = salary;
    }

    public String getInfo() {
        return "name: " + mName + "\t\t" +
                "position: " +  mPosition + "\t\t" +
                "salary: " + mSalary;
    }
}
