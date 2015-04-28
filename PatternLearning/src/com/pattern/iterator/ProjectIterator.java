package com.pattern.iterator;

import java.util.ArrayList;

public class ProjectIterator implements IProjectIterator {

    private ArrayList<IProject> mProjectList = new ArrayList<IProject>();
    private int mCurrentItem = 0;

    public ProjectIterator(ArrayList<IProject> projectList) {
        mProjectList = projectList;
    }

    @Override
    public boolean hasNext() {
        if (mCurrentItem >= mProjectList.size() || mProjectList.get(mCurrentItem) == null) {
            return false;
        }
        return true;
    }

    @Override
    public IProject next() {
        int currentItem = mCurrentItem;
        mCurrentItem++;

        return (IProject) mProjectList.get(currentItem);
    }

}
