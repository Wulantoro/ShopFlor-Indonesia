package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class InputReject implements Parcelable
{

    private int docEntry;
    private int id;
    private int hostHeadEntry;
    private int lineNumber;
    private String rejectCode;
    private String rejectName;
    private String rejectQty;
    
    public final static Parcelable.Creator<InputReject> CREATOR = new Creator<InputReject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public InputReject createFromParcel(Parcel in) {
            return new InputReject(in);
        }

        public InputReject[] newArray(int size) {
            return (new InputReject[size]);
        }

    }
            ;

    protected InputReject(Parcel in) {
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hostHeadEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.lineNumber = ((int) in.readValue((int.class.getClassLoader())));
        this.rejectCode = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectName = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectQty = ((String) in.readValue((String.class.getClassLoader())));
    }

    public InputReject() {
    }

    public int getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(int docEntry) {
        this.docEntry = docEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostHeadEntry() {
        return hostHeadEntry;
    }

    public void setHostHeadEntry(int hostHeadEntry) {
        this.hostHeadEntry = hostHeadEntry;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
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
        dest.writeValue(id);
        dest.writeValue(hostHeadEntry);
        dest.writeValue(lineNumber);
        dest.writeValue(rejectCode);
        dest.writeValue(rejectName);
        dest.writeValue(rejectQty);
    }

    public int describeContents() {
        return 0;
    }
}
