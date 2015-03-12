package com.pattern.abstractFactory;

/*
 * Abstract Factory Pattern: provide an interface 
 */
public interface HumanFactory {

    public Human createYellowHuman();

    public Human createWhiteHuman();

    public Human createBlackHuman();
}
