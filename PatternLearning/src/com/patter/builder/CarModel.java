package com.patter.builder;

import java.util.ArrayList;

public abstract class CarModel {

    private ArrayList<String> mSequence = new ArrayList<String>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run() {
        for (int i = 0; i < this.mSequence.size(); i++) {
            String actionName = this.mSequence.get(i);
            if ("start".equalsIgnoreCase(actionName)) {
                this.start();
            } else if ("stop".equalsIgnoreCase(actionName)) {
                this.stop();
            } else if ("alarm".equalsIgnoreCase(actionName)) {
                this.alarm();
            } else if ("engine boom".equalsIgnoreCase(actionName)) {
                this.engineBoom();
            }
        }
    }

    final public void setSequence(ArrayList<String> sequence) {
        this.mSequence = sequence;
    }
}
