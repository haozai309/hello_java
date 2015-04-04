package com.pattern.chain;

/*
 * Chain pattern: avoid coupling the sender of a request to its receiver by giving more than one
 * object a chance to handle the request. Chain the receiving objects and pass the request along the
 * chain until an object handles it.
 */
public abstract class Handler {

    public final static int FATHER_LEVEL_REQUEST = 1;
    public final static int HUSBAND_LEVEL_REQUEST = 2;
    public final static int SON_LEVEL_REQUEST = 3;

    private int mLevel = 0;

    private Handler mNextHandler;

    public Handler(int level) {
        mLevel = level;
    }

    public final void handleMessage(IWomen women) {
        if (women.getType() == mLevel) {
            response(women);

        } else if (mNextHandler != null) {
            mNextHandler.handleMessage(women);

        } else {
            System.out.println("----- no more, fail. -----");
        }
    }

    public void setNext(Handler handler) {
        mNextHandler = handler;
    }

    protected abstract void response(IWomen women);
}
