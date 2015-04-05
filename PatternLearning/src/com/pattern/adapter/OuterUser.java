package com.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class OuterUser implements IOuterUser {

    @Override
    public Map<String, String> getUserBaseInfo() {
        HashMap<String, String> baseInfoMap = new HashMap<String, String>();
        baseInfoMap.put("userName", "Name: Wong");
        baseInfoMap.put("mobileNumber", "Mobile number: 789");
        return baseInfoMap;
    }

    @Override
    public Map<String, String> getUserOfficeInfo() {
        HashMap<String, String> homeInfo = new HashMap<String, String>();
        homeInfo.put("homeTelNumber", "Home number: 324");
        homeInfo.put("homeAddress", "Home address: XXX");
        return homeInfo;
    }

    @Override
    public Map<String, String> getUserHomeInfo() {
        HashMap<String, String> officeInfo = new HashMap<String, String>();
        officeInfo.put("jobPosition", "Job position: XXX");
        officeInfo.put("officeTelNumber", "Office tel number: 892");
        return officeInfo;
    }

}
