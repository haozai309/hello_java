package com.patter.builder;

/*
 * Builder pattern: Separate the construction of a complex object from its representation so that
 * the same construction process can create different representations.
 */
import java.util.ArrayList;

public abstract class CarBuilder {

    public abstract void setSequence(ArrayList<String> sequence);

    public abstract CarModel getCarModel();
}
