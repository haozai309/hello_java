package com.pattern.decorator;

public class Father {

    public static void main(String[] args) {
        SchoolReport schoolReport = new FouthGradeSchoolReport();
        schoolReport.report();
        System.out.println();

        System.out.println("----- reviesed to use decorator pattern -----");
        SchoolReport sugarReport = new SugarFouthGradeSchoolReport();
        sugarReport.report();
        sugarReport.sign("Chang san");
    }
}
