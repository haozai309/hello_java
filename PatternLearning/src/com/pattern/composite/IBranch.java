package com.pattern.composite;

import java.util.ArrayList;

public interface IBranch extends ICorp {

    public void addSubordinate(ICorp corp);

    public ArrayList<ICorp> getSubordinate();

}
