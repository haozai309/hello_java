package com.pattern.factory;

/*
 * Factory: define an interface for creating an object, but let subclass decide
 * which class to instantiate.
 */
public abstract class AbstractHumanFactory {

    public abstract <T extends Human> T createHuman(Class<T> c);
}
