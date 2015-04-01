package com.pattern.command;

public class PageGroup extends Group {

    @Override
    public void find() {
        System.out.println("Find page group");
    }

    @Override
    public void add() {
        System.out.println("Add a new page");

    }

    @Override
    public void delete() {
        System.out.println("Delete a page");

    }

    @Override
    public void change() {
        System.out.println("Change a page");

    }

    @Override
    public void plan() {
        System.out.println("Page plan");

    }

}
