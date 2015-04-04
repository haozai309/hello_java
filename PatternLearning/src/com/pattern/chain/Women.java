package com.pattern.chain;

public class Women implements IWomen {

    private int mType;
    private String mRequest;

    public Women(int type, String request) {
        mType = type;
        mRequest = request;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public String getRequest() {
        return mRequest;
    }

}
