package com.pattern.command;

/*
 * Command pattern: encapsulate a request as an object, thereby letting you parameterize clients
 * with different requests, queue or log requests, and support undoable operations.
 */
public abstract class Command {

    protected RequirementGroup mRequirementGroup = new RequirementGroup();

    protected PageGroup mPageGroup = new PageGroup();

    protected CodeGroup mCodeGroup = new CodeGroup();

    public abstract void execute();
}
