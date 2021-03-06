package com.pattern.factory;

public class NvWa {

    public static void main(String args[]) {
        AbstractHumanFactory yinYangLu = new HumanFactory();

        System.out.println("The white people.");
        Human whiteHuman = yinYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        System.out.println("\nThe yellow people.");
        Human yellowhHuman = yinYangLu.createHuman(YellowHuman.class);
        yellowhHuman.getColor();
        yellowhHuman.talk();

        System.out.println("\nThe black people.");
        Human blackHuman = yinYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
    }
}
