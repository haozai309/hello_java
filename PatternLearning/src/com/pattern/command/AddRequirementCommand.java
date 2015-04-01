package com.pattern.command;

public class AddRequirementCommand extends Command {

    @Override
    public void execute() {
        mRequirementGroup.find();

        mRequirementGroup.add();

        mRequirementGroup.plan();
    }

}
