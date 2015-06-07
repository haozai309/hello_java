package com.pattern.composite;

import java.util.ArrayList;

public class Branch extends Corp {

    private ArrayList<Corp> mSubordinateList = new ArrayList<Corp>();

    public Branch(String name, String position, int salary) {
        super(name, position, salary);
    }

    public void addSubordinate(Corp corp) {
        mSubordinateList.add(corp);
    }

    public ArrayList<Corp> getSubordinate() {
        return mSubordinateList;
    }

}
