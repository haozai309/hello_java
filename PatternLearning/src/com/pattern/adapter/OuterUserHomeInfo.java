package com.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class OuterUserHomeInfo implements IOuterUserHomeInfo {

    @Override
    public Map<String, String> getUserHomeInfo() {
        HashMap<String, String> homeInfo = new HashMap<String, String>();
        homeInfo.put("homeTelNumber", "Home number: 324");
        homeInfo.put("homeAddress", "Home address: XXX");
        return homeInfo;
    }

}
