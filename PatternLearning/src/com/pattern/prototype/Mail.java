package com.pattern.prototype;

/*
 * Prototype pattern: specify the kinds of objects to create using a prototypical instance, and
 * create new objects by copying this prototype.
 */
public class Mail implements Cloneable {

    private String mReceiver;

    private String mSubject;

    private String mAppellation;

    private String mContext;

    private String mTail;

    public Mail(AdvTemplate advTemplate) {
        this.mContext = advTemplate.getAdvContext();
        this.mSubject = advTemplate.getAdvSubject();
    }

    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String receiver) {
        this.mReceiver = receiver;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getAppellation() {
        return mAppellation;
    }

    public void setAppellation(String appellation) {
        this.mAppellation = appellation;
    }

    public String getContext() {
        return mContext;
    }

    public void setContext(String context) {
        this.mContext = context;
    }

    public String getTail() {
        return mTail;
    }

    public void setTail(String tail) {
        this.mTail = tail;
    }

}
