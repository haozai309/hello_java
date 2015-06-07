package com.pattern.composite;

import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        Branch ceo = compositeCorpTree();
        System.out.println(ceo.getInfo());
        System.out.println(getTreeInfo(ceo));
    }

    private static Branch compositeCorpTree() {
        Branch root = new Branch("Wong", "General Manager", 100000);

        Branch developDep = new Branch("Liu", "Develop manager", 10000);
        Branch salesDep = new Branch("Ma", "Sales manager", 20000);
        Branch financeDep = new Branch("Zhao", "Finance manager", 30000);

        Branch firstDevGroup = new Branch("Yang", "1st group leader", 5000);
        Branch secondDevGroup = new Branch("Wu", "2nd group leader", 6000);

        root.addSubordinate(new Leaf("k", "CEO Secretary", 8000));
        root.addSubordinate(developDep);
        root.addSubordinate(salesDep);
        root.addSubordinate(financeDep);

        addSalesLeaf(salesDep, "h", 5000);
        addSalesLeaf(salesDep, "i", 4000);
        addFinanceLeaf(financeDep, "j", 5000);

        developDep.addSubordinate(new Leaf("Zheng", "Vice Manager", 20000));
        developDep.addSubordinate(firstDevGroup);
        developDep.addSubordinate(secondDevGroup);

        addDeveloperLeaf(firstDevGroup, "a");
        addDeveloperLeaf(firstDevGroup, "b");
        addDeveloperLeaf(firstDevGroup, "c");
        addDeveloperLeaf(secondDevGroup, "d");
        addDeveloperLeaf(secondDevGroup, "e");
        addDeveloperLeaf(secondDevGroup, "f");
        addDeveloperLeaf(secondDevGroup, "g");

        return root;
    }

    private static String getTreeInfo(Branch root) {
        ArrayList<ICorp> subordinateList = root.getSubordinate();
        String info = "";
        for (ICorp s : subordinateList) {
            if (s instanceof Leaf) {
                info += s.getInfo() + "\n";
            } else {
                info += s.getInfo() + "\n" + getTreeInfo((Branch) s);
            }
        }
        return info;
    }

    private static void addDeveloperLeaf(Branch branch, String name) {
        branch.addSubordinate(new Leaf(name, "Developer", 2000));
    }

    private static void addSalesLeaf(Branch branch, String name, int salary) {
        branch.addSubordinate(new Leaf(name, "Sales Staff", salary));
    }

    private static void addFinanceLeaf(Branch branch, String name, int salary) {
        branch.addSubordinate(new Leaf(name, "Finance Staff", salary));
    }
}
