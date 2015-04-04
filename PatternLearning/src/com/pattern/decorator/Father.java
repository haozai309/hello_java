package com.pattern.decorator;

public class Father {

    public static void main(String[] args) {
        SchoolReport schoolReport = new FouthGradeSchoolReport();
        schoolReport.report();
    }
}
