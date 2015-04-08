package com.pattern.adapter;

import java.util.Map;

/*
 * Adapter pattern
 *   Convert the interface of a class into another interface clients expect. Adapter lets classes
 *   work together that couldn't otherwise because of incompatible interfaces.
 */
public class OuterUserInfo implements IUserInfo {

    private IOuterUserBaseInfo mBaseInfo = null;
    private IOuterUserHomeInfo mHomeInfo = null;
    private IOuterUserOfficeInfo mOfficeInfo = null;

    private Map<String, String> mBaseMap = null;
    private Map<String, String> mHomeMap = null;
    private Map<String, String> mOfficeMap = null;

    public OuterUserInfo(IOuterUserBaseInfo baseInfo, IOuterUserHomeInfo homeInfo,
            IOuterUserOfficeInfo officeInfo) {
        mBaseInfo = baseInfo;
        mHomeInfo = homeInfo;
        mOfficeInfo = officeInfo;
        mBaseMap = mBaseInfo.getUserBaseInfo();
        mHomeMap = mHomeInfo.getUserHomeInfo();
        mOfficeMap = mOfficeInfo.getUserOfficeInfo();
    }

    @Override
    public String getUserName() {
        String userName = mBaseMap.get("userName");
        return userName;
    }

    @Override
    public String getHomeAddress() {
        String homeAddress = mHomeMap.get("homeAddress");
        return homeAddress;
    }

    @Override
    public String getMobileNumber() {
        String mobileNumber = mBaseMap.get("mobileNumber");
        return mobileNumber;
    }

    @Override
    public String getOfficeTelNumber() {
        String officeTelNumber = mOfficeMap.get("officeTelNumber");
        return officeTelNumber;
    }

    @Override
    public String getJobPosition() {
        String jobPosition = mOfficeMap.get("jobPosition");
        return jobPosition;
    }

    @Override
    public String getHomeTelNumber() {
        String homeTelNumber = mHomeMap.get("homeTelNumber");
        return homeTelNumber;
    }

}
