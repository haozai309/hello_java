package com.pattern.iterator;

import java.util.ArrayList;

public class Boss {

    public static void main(String[] args) {
        ArrayList<IProject> projectList = new ArrayList<IProject>();
        projectList.add(new Project("Star War", 10, 3600));
        projectList.add(new Project("Time machine", 100, 36000));
        projectList.add(new Project("Super man", 1000, 365000));

        for (int i = 4; i < 104; ++i) {
            projectList.add(new Project("Project " + i, i * 5, i * 360));
        }

        for (IProject project : projectList) {
            System.out.println(project.getProjectInfo());
        }
    }

}
