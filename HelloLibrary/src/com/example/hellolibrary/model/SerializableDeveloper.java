package com.example.hellolibrary.model;

import java.io.Serializable;
import java.util.List;

/*
 * http://www.developerphil.com/parcelable-vs-serializable/
 * access modifiers, accessors and constructors omitted for brevity
 */
public class SerializableDeveloper implements Serializable {

    private String name;
    private int yearsOfExperience;
    private List<Skill> skillSet;
    private float favoriteFloat;

    public static class Skill implements Serializable {
        String name;
        boolean programmingRelated;
    }
}
