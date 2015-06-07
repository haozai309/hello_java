package com.pattern.composite;

import java.util.ArrayList;

public class Branch implements IBranch {

    private String mName = "";

    private String mPosition = "";

    private int mSalary;

    private ArrayList<ICorp> mSubordinateList = new ArrayList<ICorp>();

    public Branch(String name, String position, int salary) {
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

    @Override
    public void addSubordinate(ICorp corp) {
        mSubordinateList.add(corp);
    }

    @Override
    public ArrayList<ICorp> getSubordinate() {
        return mSubordinateList;
    }

}
