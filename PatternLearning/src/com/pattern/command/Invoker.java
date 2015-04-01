package com.pattern.command;

public class Invoker {

    private Command mCommand;

    public void setCommand(Command command) {
        mCommand = command;
    }

    public Command getCommand() {
        return mCommand;
    }

    public void action() {
        mCommand.execute();
    }
}
