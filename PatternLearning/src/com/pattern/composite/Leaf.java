package com.pattern.composite;

public class Leaf implements ILeaf {

    private String mName = "";

    private String mPosition = "";

    private int mSalary;

    public Leaf(String name, String position, int salary) {
        mName = name;
        mPosition = position;
        mSalary = salary;
    }

    @Override
    public String getInfo() {
        return "name: " + mName + "\t\t" +
                "position: " +  mPosition + "\t\t" +
                "salary: " + mSalary;
    }

}
