package com.pattern.adapter;

import java.util.Map;

/*
 * Adapter pattern
 *   Convert the interface of a class into another interface clients expect. Adapter lets classes
 *   work together that couldn't otherwise because of incompatible interfaces.
 */
public class OuterUserInfo extends OuterUser implements IUserInfo {

    private Map<String, String> mBaseInfo = super.getUserBaseInfo();
    private Map<String, String> mHomeInfo = super.getUserHomeInfo();
    private Map<String, String> mOfficeInfo = super.getUserHomeInfo();

    @Override
    public String getUserName() {
        String userName = mBaseInfo.get("userName");
        return userName;
    }

    @Override
    public String getHomeAddress() {
        String homeAddress = mBaseInfo.get("homeAddress");
        return homeAddress;
    }

    @Override
    public String getMobileNumber() {
        String mobileNumber = mBaseInfo.get("mobileNumber");
        return mobileNumber;
    }

    @Override
    public String getOfficeTelNumber() {
        String officeTelNumber = mOfficeInfo.get("officeTelNumber");
        return officeTelNumber;
    }

    @Override
    public String getJobPosition() {
        String jobPosition = mOfficeInfo.get("jobPosition");
        return jobPosition;
    }

    @Override
    public String getHomeTelNumber() {
        String homeTelNumber = mHomeInfo.get("homeTelNumber");
        return homeTelNumber;
    }

}
