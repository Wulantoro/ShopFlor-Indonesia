package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Totreject implements Parcelable
{

    private String total;
    public final static Parcelable.Creator<Totreject> CREATOR = new Creator<Totreject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Totreject createFromParcel(Parcel in) {
            return new Totreject(in);
        }

        public Totreject[] newArray(int size) {
            return (new Totreject[size]);
        }

    }
            ;

    protected Totreject(Parcel in) {
        this.total = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Totreject() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(total);
    }

    public int describeContents() {
        return 0;
    }
}
