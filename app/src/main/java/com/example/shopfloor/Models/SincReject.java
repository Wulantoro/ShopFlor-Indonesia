package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SincReject implements Parcelable
{

    private int DocEntry;
    private int lineId;
    private int visOrder;
    private String object;
    private Object logInst;
    private int U_LineNum;
    private Object U_Type;
    private String U_Qty;
    public final static Parcelable.Creator<SincReject> CREATOR = new Creator<SincReject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SincReject createFromParcel(Parcel in) {
            return new SincReject(in);
        }

        public SincReject[] newArray(int size) {
            return (new SincReject[size]);
        }

    }
            ;

    protected SincReject(Parcel in) {
        this.DocEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.lineId = ((int) in.readValue((int.class.getClassLoader())));
        this.visOrder = ((int) in.readValue((int.class.getClassLoader())));
        this.object = ((String) in.readValue((String.class.getClassLoader())));
        this.logInst = ((Object) in.readValue((Object.class.getClassLoader())));
        this.U_LineNum = ((int) in.readValue((int.class.getClassLoader())));
        this.U_Type = ((Object) in.readValue((Object.class.getClassLoader())));
        this.U_Qty = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SincReject() {
    }

    public int getDocEntry() {
        return DocEntry;
    }

    public void setDocEntry(int docEntry) {
        this.DocEntry = docEntry;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getVisOrder() {
        return visOrder;
    }

    public void setVisOrder(int visOrder) {
        this.visOrder = visOrder;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Object getLogInst() {
        return logInst;
    }

    public void setLogInst(Object logInst) {
        this.logInst = logInst;
    }

    public int getULineNum() {
        return U_LineNum;
    }

    public void setULineNum(int uLineNum) {
        this.U_LineNum = uLineNum;
    }

    public Object getUType() {
        return U_Type;
    }

    public void setUType(Object uType) {
        this.U_Type = uType;
    }

    public String getUQty() {
        return U_Qty;
    }

    public void setUQty(String uQty) {
        this.U_Qty = uQty;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(DocEntry);
        dest.writeValue(lineId);
        dest.writeValue(visOrder);
        dest.writeValue(object);
        dest.writeValue(logInst);
        dest.writeValue(U_LineNum);
        dest.writeValue(U_Type);
        dest.writeValue(U_Qty);
    }

    public int describeContents() {
        return 0;
    }

}