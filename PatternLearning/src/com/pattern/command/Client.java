package com.pattern.command;

public class Client {

    public static void main(String[] args) {
        System.out.println("----- Customer need to add a new requirement -----");
        Group group = new RequirementGroup();
        group.find();
        group.add();
        group.plan();

        System.out.println("\n----- Customer need to delte a page -----");
        Group page = new RequirementGroup();
        page.find();
        page.delete();
        page.plan();

        System.out.println("\n***** Revise to use command pattern *****");
        System.out.println("----- add requirement -----");
        Invoker invoker = new Invoker();
        invoker.setCommand(new AddRequirementCommand());
        invoker.action();

        System.out.println("\n----- delete page -----");
        invoker.setCommand(new DeletePageCommand());
        invoker.action();
    }
}
