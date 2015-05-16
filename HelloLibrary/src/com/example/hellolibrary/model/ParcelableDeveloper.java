package com.example.hellolibrary.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Access modifiers, accessors and regular constructors ommited for brevity
 * http://www.developerphil.com/parcelable-vs-serializable/
 */
public class ParcelableDeveloper implements Parcelable {

    private String name;
    private int yearsOfExperience;
    private List<Skill> skillSet;
    private float favoriteFloat;

    public ParcelableDeveloper(Parcel in) {
        this.name = in.readString();
        this.yearsOfExperience = in.readInt();
        this.skillSet = new ArrayList<Skill>();
        in.readTypedList(skillSet, Skill.CREATOR);
        this.favoriteFloat = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(yearsOfExperience);
        dest.writeTypedList(skillSet);
        dest.writeFloat(favoriteFloat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ParcelableDeveloper> CREATOR = new Parcelable.Creator<ParcelableDeveloper>() {

        public ParcelableDeveloper createFromParcel(Parcel in) {
            return new ParcelableDeveloper(in);
        }

        public ParcelableDeveloper[] newArray(int size) {
            return new ParcelableDeveloper[size];
        }
    };

    static class Skill implements Parcelable {

        private String name;
        private boolean programmingRelated;

        public Skill(Parcel in) {
            this.name = in.readString();
            this.programmingRelated = (in.readInt() == 1);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(programmingRelated ? 1 : 0);
        }

        public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>() {

            public Skill createFromParcel(Parcel in) {
                return new Skill(in);
            }

            public Skill[] newArray(int size) {
                return new Skill[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "name: " + name + ", yearsOfExperience " + yearsOfExperience + ", favoriteFloat "
                + favoriteFloat;
    }

}
