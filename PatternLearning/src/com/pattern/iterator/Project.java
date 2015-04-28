package com.pattern.iterator;

public class Project implements IProject {

    private String mName = "";

    private int mNum = 0;

    private int mCost = 0;

    public Project(String name, int num, int cost) {
        mName = name;
        mNum = num;
        mCost = cost;
    }

    @Override
    public String getProjectInfo() {
        String info = "name is " + mName + "\t"
                + "number is " + mNum + "\t"
                + "cost is " + mCost;
        return info;
    }

}
