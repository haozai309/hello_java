package com.pattern.iterator;

import java.util.ArrayList;

public class Project implements IProject {

    private ArrayList<IProject> mProjectList = new ArrayList<IProject>();

    private String mName = "";

    private int mNum = 0;

    private int mCost = 0;

    public Project() {

    }

    public Project(String name, int num, int cost) {
        mName = name;
        mNum = num;
        mCost = cost;
    }

    @Override
    public String getProjectInfo() {
        String info = "name is " + mName + "\t" + "number is " + mNum + "\t" + "cost is " + mCost;
        return info;
    }

    @Override
    public void add(String name, int num, int cost) {
        mProjectList.add(new Project(name, num, cost));
    }

    @Override
    public IProjectIterator iterator() {
        return new ProjectIterator(mProjectList);
    }

}
