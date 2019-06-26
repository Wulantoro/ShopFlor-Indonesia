package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;



public class Productorder implements Parcelable
{
    private int docNum;
    private int uPDNO;
    private String itemCode;
    private String status;
    private int uSequence;
    private String itemName;
    private String uDesc;
    private int U_SEQ;
    private String uIsActive;
    private String name;
    private String code;
    private String wccode;
    private String plannedQty;
    private String U_WCCode;

    public final static Parcelable.Creator<Productorder> CREATOR = new Creator<Productorder>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Productorder createFromParcel(Parcel in) {
            return new Productorder(in);
        }

        public Productorder[] newArray(int size) {
            return (new Productorder[size]);
        }

    }
            ;

    protected Productorder(Parcel in) {
        this.docNum = ((int) in.readValue((int.class.getClassLoader())));
        this.uPDNO = ((int) in.readValue((int.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.uSequence = ((int) in.readValue((int.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.uDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.U_SEQ = ((int) in.readValue((int.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.plannedQty = ((String) in.readValue((String.class.getClassLoader())));
        this.U_WCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.wccode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Productorder() {
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public int getUPDNO() {
        return uPDNO;
    }

    public void setUPDNO(int uPDNO) {
        this.uPDNO = uPDNO;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUSequence() {
        return uSequence;
    }

    public void setUSequence(int uSequence) {
        this.uSequence = uSequence;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUDesc() {
        return uDesc;
    }

    public void setUDesc(String uDesc) {
        this.uDesc = uDesc;
    }

    public int getUSEQ() {
        return U_SEQ;
    }

    public void setUSEQ(int uSEQ) {
        this.U_SEQ = uSEQ;
    }

    public String getUIsActive() {
        return uIsActive;
    }

    public void setUIsActive(String uIsActive) {
        this.uIsActive = uIsActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlannedQty() {
        return plannedQty;
    }

    public void setPlannedQty(String plannedQty) {
        this.plannedQty = plannedQty;
    }

    public String getU_WCCode() {
        return U_WCCode;
    }

    public void setU_WCCode(String U_WCCode) {
        this.U_WCCode = U_WCCode;
    }

    public String getWccode() {
        return wccode;
    }

    public void setWccode(String wccode) {
        this.wccode = wccode;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docNum);
        dest.writeValue(uPDNO);
        dest.writeValue(itemCode);
        dest.writeValue(status);
        dest.writeValue(uSequence);
        dest.writeValue(itemName);
        dest.writeValue(uDesc);
        dest.writeValue(U_SEQ);
        dest.writeValue(uIsActive);
        dest.writeValue(name);
        dest.writeValue(code);
        dest.writeValue(plannedQty);
        dest.writeValue(U_WCCode);
        dest.writeValue(wccode);

    }

    public int describeContents() {
        return 0;
    }
}