package com.pattern.chain;

public class Women implements IWomen {

    private int mType;
    private String mRequest;

    public Women(int type, String request) {
        mType = type;
        mRequest = request;
        switch (type) {
        case 1:
            mRequest = "Daughter's request " + request;
            break;

        case 2:
            mRequest = "Wife's request " + request;
            break;

        case 3:
            mRequest = "Mother's request " + request;
            break;

        default:
            break;
        }
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
