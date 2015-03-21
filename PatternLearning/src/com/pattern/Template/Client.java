package com.pattern.Template;

import java.util.Random;

public class Client {

    // The number of bill, should get from the database
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {
        int i = 0;
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("All rights reserved by XXX bank");
        while (i < MAX_COUNT) {
            mail.setAppellation(getRandString(5) + " sir (lady)");
            mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            sendMail(mail);
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
