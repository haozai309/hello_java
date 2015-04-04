package com.pattern.decorator;

/*
 * Decorator pattern: attach additional responsibilities to an object dynamically keeping the same
 * interface. Decorators provide a flexible alternative to subclassing for extending functionality.
 */
public class Decorator extends SchoolReport {

    private SchoolReport mSchoolReport;

    public Decorator(SchoolReport schoolReport) {
        mSchoolReport = schoolReport;
    }

    @Override
    public void report() {
        mSchoolReport.report();
    }

    @Override
    public void sign(String name) {
        mSchoolReport.sign(name);
    }

}
