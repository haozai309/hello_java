package com.pattern.decorator;

public class HighScoreDecorator extends Decorator {

    public HighScoreDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }

    private void reportHighScore() {
        System.out.println("The highest score:");
        System.out.println("Chinese\t75\tMath\t78\tNature\t80");
    }

    @Override
    public void report() {
        reportHighScore();
        super.report();
    }

}
