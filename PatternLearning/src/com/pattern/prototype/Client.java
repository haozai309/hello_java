package com.pattern.prototype;

import java.util.Random;

public class Client {

    // The number of bill, should get from the database
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {
        int i = 0;
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("All rights reserved by XXX bank");
        while (i < MAX_COUNT) {
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(getRandString(5) + " sir (lady)");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");

            // use a clone of mail, thread safe even the sendMail use multiple thread
            sendMail(cloneMail);
            ++i;
        }
    }

    public static void sendMail(Mail mail) {
        System.out.println("Title: " + mail.getSubject() + "\tReceiver: " + mail.getReceiver()
                + "\t... send successfully.");
    }

    public static String getRandString(int maxLength) {
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < maxLength; ++i) {
            sb.append(source.charAt(rand.nextInt(source.length())));

        }
        return sb.toString();
    }

}
