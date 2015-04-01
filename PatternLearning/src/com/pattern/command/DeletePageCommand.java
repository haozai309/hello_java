package com.pattern.command;

public class DeletePageCommand extends Command {

    @Override
    public void execute() {
        mPageGroup.find();

        mPageGroup.delete();

        mPageGroup.plan();
    }

}
