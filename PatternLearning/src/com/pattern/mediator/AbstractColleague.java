package com.pattern.mediator;

public abstract class AbstractColleague {

    protected AbstractMediator mMediator;

    public AbstractColleague(AbstractMediator mediator) {
        mMediator = mediator;
    }
}
