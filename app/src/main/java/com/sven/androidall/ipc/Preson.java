package com.sven.androidall.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class Preson implements Parcelable {
    String name;
    int age;

    protected Preson(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Preson> CREATOR = new Creator<Preson>() {
        @Override
        public Preson createFromParcel(Parcel in) {
            return new Preson(in);
        }

        @Override
        public Preson[] newArray(int size) {
            return new Preson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
