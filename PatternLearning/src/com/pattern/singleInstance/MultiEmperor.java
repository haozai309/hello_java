package com.pattern.singleInstance;

import java.util.ArrayList;
import java.util.Random;

/*
 * Singleton pattern with max number.
 */
public class MultiEmperor {

    private static final int MAX_NUMBER_EMPEROR = 2;

    private static ArrayList<String> mNameList = new ArrayList<String>();

    private static ArrayList<MultiEmperor> mEmperorList = new ArrayList<MultiEmperor>();

    private static int mCountNumber = 0;

    static {
        for (int i = 0; i < MAX_NUMBER_EMPEROR; i++) {
            mEmperorList.add(new MultiEmperor("This is emperor " + (i + 1)));
        }
    }

    private MultiEmperor() {

    }

    private MultiEmperor(String name) {
        mNameList.add(name);
    }

    public static MultiEmperor getinstance() {
        Random random = new Random();
        mCountNumber = random.nextInt(MAX_NUMBER_EMPEROR);
        return mEmperorList.get(mCountNumber);
    }

    public void say() {
        System.out.println(mNameList.get(mCountNumber));
    }
}
