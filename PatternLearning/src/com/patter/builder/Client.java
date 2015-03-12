package com.patter.builder;

import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        sequence.add("alarm");

        System.out.println("---- basic creator pattern ----");
        BenzModel benz = new BenzModel();
        benz.setSequence(sequence);
        benz.run();

        System.out.println("\n---- update to use builder ----");
        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);
        BenzModel newBenz = (BenzModel) benzBuilder.getCarModel();
        newBenz.run();

        BmwBuilder bmwBuilder = new BmwBuilder();
        bmwBuilder.setSequence(sequence);
        BmwModel bmwModel = (BmwModel) bmwBuilder.getCarModel();
        bmwModel.run();

        System.out.println("\n---- update to use director ----");
        Director director = new Director();
        director.getABenzModel().run();
        director.getCBmwModel().run();
        director.getBBenzModel().run();
    }
}
