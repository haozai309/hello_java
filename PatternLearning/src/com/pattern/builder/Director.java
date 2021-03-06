package com.pattern.builder;

import java.util.ArrayList;

public class Director {

    private ArrayList<String> mSequence = new ArrayList<String>();

    private BenzBuilder benzBuilder = new BenzBuilder();

    private BmwBuilder bmwBuilder = new BmwBuilder();

    public BenzModel getABenzModel() {
        this.mSequence.clear();
        this.mSequence.add("start");
        this.mSequence.add("stop");
        this.benzBuilder.setSequence(this.mSequence);
        return (BenzModel) benzBuilder.getCarModel();
    }

    public BenzModel getBBenzModel() {
        this.mSequence.clear();
        this.mSequence.add("engine boom");
        this.mSequence.add("start");
        this.mSequence.add("stop");
        this.benzBuilder.setSequence(this.mSequence);
        return (BenzModel) benzBuilder.getCarModel();
    }

    public BmwModel getCBmwModel() {
        this.mSequence.clear();
        this.mSequence.add("alarm");
        this.mSequence.add("start");
        this.mSequence.add("stop");
        this.bmwBuilder.setSequence(this.mSequence);
        return (BmwModel) bmwBuilder.getCarModel();
    }

    public BmwModel getDBmwModel() {
        this.mSequence.clear();
        this.mSequence.add("start");
        this.bmwBuilder.setSequence(this.mSequence);
        return (BmwModel) bmwBuilder.getCarModel();
    }
}
