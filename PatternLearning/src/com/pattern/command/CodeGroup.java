package com.pattern.command;

public class CodeGroup extends Group {

    @Override
    public void find() {
        System.out.println("Find code group");
    }

    @Override
    public void add() {
        System.out.println("Add new code");

    }

    @Override
    public void delete() {
        System.out.println("Delete code");

    }

    @Override
    public void change() {
        System.out.println("Change code");

    }

    @Override
    public void plan() {
        System.out.println("Code plan");

    }

}
