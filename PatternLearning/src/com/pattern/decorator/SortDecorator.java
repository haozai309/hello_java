package com.pattern.decorator;

public class SortDecorator extends Decorator {

    public SortDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }

    private void reportSort() {
        System.out.println("I'm the 38th");
    }

    @Override
    public void report() {
        super.report();
        reportSort();
    }
}
