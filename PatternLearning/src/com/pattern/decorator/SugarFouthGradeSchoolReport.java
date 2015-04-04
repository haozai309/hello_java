package com.pattern.decorator;

public class SugarFouthGradeSchoolReport extends FouthGradeSchoolReport {

    private void reportHighScore() {
        System.out.println("The highest score:");
        System.out.println("Chinese\t75\tMath\t78\tNature\t80");
    }

    private void reportSort() {
        System.out.println("I'm the 38th");
    }

    @Override
    public void report() {
        reportHighScore();
        super.report();
        reportSort();
    }
}
