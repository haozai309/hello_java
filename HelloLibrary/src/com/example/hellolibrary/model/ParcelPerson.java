package com.example.hellolibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelPerson implements Parcelable {

    private String name;

    private int age;

    public ParcelPerson() {
    }

    public ParcelPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Parcelable.Creator<ParcelPerson> CREATOR = new Parcelable.Creator<ParcelPerson>() {

        @Override
        public ParcelPerson createFromParcel(Parcel source) {
            ParcelPerson person = new ParcelPerson();
            person.name = source.readString();
            person.age = source.readInt();
            return person;
        }

        @Override
        public ParcelPerson[] newArray(int size) {
            return new ParcelPerson[size];
        }
    };

    @Override
    public String toString() {
        return "name is " + name + ", age is " + age;
    }
}
