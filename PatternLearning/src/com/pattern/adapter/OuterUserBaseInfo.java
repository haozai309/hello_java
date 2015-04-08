package com.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class OuterUserBaseInfo implements IOuterUserBaseInfo {

    @Override
    public Map<String, String> getUserBaseInfo() {
        HashMap<String, String> baseInfoMap = new HashMap<String, String>();
        baseInfoMap.put("userName", "Name: Wong");
        baseInfoMap.put("mobileNumber", "Mobile number: 789");
        return baseInfoMap;
    }

}
