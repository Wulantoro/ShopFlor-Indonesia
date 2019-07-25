package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LastReject implements Parcelable
{

    private int docEntry;
    private Object lineNumber;
    private String rejectCode;
    private String rejectName;
    private String rejectQty;
    public final static Parcelable.Creator<LastReject> CREATOR = new Creator<LastReject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LastReject createFromParcel(Parcel in) {
            return new LastReject(in);
        }

        public LastReject[] newArray(int size) {
            return (new LastReject[size]);
        }

    }
            ;

    protected LastReject(Parcel in) {
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.lineNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.rejectCode = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectName = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectQty = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LastReject() {
    }

    public int getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(int docEntry) {
        this.docEntry = docEntry;
    }

    public Object getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Object lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

    public String getRejectName() {
        return rejectName;
    }

    public void setRejectName(String rejectName) {
        this.rejectName = rejectName;
    }

    public String getRejectQty() {
        return rejectQty;
    }

    public void setRejectQty(String rejectQty) {
        this.rejectQty = rejectQty;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docEntry);
        dest.writeValue(lineNumber);
        dest.writeValue(rejectCode);
        dest.writeValue(rejectName);
        dest.writeValue(rejectQty);
    }

    public int describeContents() {
        return 0;
    }

}
