package com.pattern.command;

public class Client {

    public static void main(String[] args) {
        System.out.println("----- Customer need to add a new requirement ----");
        Group group = new RequirementGroup();
        group.find();
        group.add();
        group.plan();

        System.out.println("\n----- Customer need to delte a page ----");
        Group page = new RequirementGroup();
        page.find();
        page.delete();
        page.plan();
    }
}
