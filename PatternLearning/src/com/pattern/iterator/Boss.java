package com.pattern.iterator;

public class Boss {

    public static void main(String[] args) {
        IProject project = new Project();
        project.add("Star War", 10, 3600);
        project.add("Time machine", 100, 36000);
        project.add("Super man", 1000, 365000);

        for (int i = 4; i < 104; ++i) {
            project.add("Project " + i, i * 5, i * 360);
        }

        IProjectIterator projectIterator = project.iterator();
        while(projectIterator.hasNext()) {
            IProject p = projectIterator.next();
            System.out.println(p.getProjectInfo());
        }
    }

}
