package com.pattern.template;

public abstract class AbstractClass {

    protected abstract void doSomething();

    protected abstract void doAnything();

    public void templatemethod() {
        this.doAnything();
        this.doSomething();
    }
}
