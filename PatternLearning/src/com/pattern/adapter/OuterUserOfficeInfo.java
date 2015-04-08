package com.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class OuterUserOfficeInfo implements IOuterUserOfficeInfo {

    @Override
    public Map<String, String> getUserOfficeInfo() {
        HashMap<String, String> officeInfo = new HashMap<String, String>();
        officeInfo.put("jobPosition", "Job position: XXX");
        officeInfo.put("officeTelNumber", "Office tel number: 892");
        return officeInfo;
    }

}
