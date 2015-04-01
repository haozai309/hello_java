package com.pattern.command;

public class RequirementGroup extends Group {

    @Override
    public void find() {
        System.out.println("find new requiremnt");
    }

    @Override
    public void add() {
        System.out.println("add new requirement");
    }

    @Override
    public void delete() {
        System.out.println("delete a requirment");
    }

    @Override
    public void change() {
        System.out.println("change a requirement");
    }

    @Override
    public void plan() {
        System.out.println("Requirement plan");
    }

}
