package com.pattern.decorator;

public class FouthGradeSchoolReport extends SchoolReport {

    @Override
    public void report() {
        System.out.println("Dear XXX");
        System.out.println("....");
        System.out.println("Chinese\t62\tMath\t65");
        System.out.println("PE\t98\tNatural\t63");
        System.out.println("....");
        System.out.println("Sign here:");
    }

    @Override
    public void sign(String name) {
        System.out.println("Sign as " + name);
    }

}
