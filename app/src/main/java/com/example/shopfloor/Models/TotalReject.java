package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TotalReject implements Parcelable
{

    private String totReject;
    public final static Parcelable.Creator<TotalReject> CREATOR = new Parcelable.Creator<TotalReject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TotalReject createFromParcel(Parcel in) {
            return new TotalReject(in);
        }

        public TotalReject[] newArray(int size) {
            return (new TotalReject[size]);
        }

    }
            ;

    protected TotalReject(Parcel in) {
        this.totReject = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TotalReject() {
    }

    public String getTotReject() {
        return totReject;
    }

    public void setTotReject(String totReject) {
        this.totReject = totReject;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totReject);
    }

    public int describeContents() {
        return 0;
    }

}
