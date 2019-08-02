package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Criteria implements Parcelable {


//    private int uSequence;
    private int seq;
    private int U_sequence;
    private int uQuantity;
    private String itemCode;
    private int docNum;
//    private String docnum
    private String status;
    private String itemName;
    private String uDesc;
    private String uIsActive;
    private String code;
    private String name;
    private String wccode;
    private String plannedQty;
    private String U_ItemCode;
    private String U_WCCode;
    private String U_Criteria;
    private String U_Standard;
    private Object U_CriteriaName;
    private String U_ValueType;
    private String actualResult;
    private int lineNumber;
    public final static Parcelable.Creator<Criteria> CREATOR = new Creator<Criteria>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Criteria createFromParcel(Parcel in) {
            return new Criteria(in);
        }

        public Criteria[] newArray(int size) {
            return (new Criteria[size]);
        }

    }
            ;

    protected Criteria(Parcel in) {
        this.seq = ((int) in.readValue((int.class.getClassLoader())));
        this.U_sequence = ((int) in.readValue((int.class.getClassLoader())));
        this.uQuantity = ((int) in.readValue((int.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.docNum = ((int) in.readValue((int.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.uDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.wccode = ((String) in.readValue((String.class.getClassLoader())));
        this.plannedQty = ((String) in.readValue((String.class.getClassLoader())));
        this.U_WCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.U_ItemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.U_WCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.U_Criteria = ((String) in.readValue((String.class.getClassLoader())));
        this.U_Standard = ((String) in.readValue((String.class.getClassLoader())));
        this.U_CriteriaName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.U_ValueType = ((String) in.readValue((String.class.getClassLoader())));
        this.actualResult = ((String) in.readValue((Object.class.getClassLoader())));
        this.lineNumber = ((int) in.readValue((Object.class.getClassLoader())));
    }

    public Criteria() {
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getUSequence() {
        return U_sequence;
    }

    public void setUSequence(int uSequence) {
        this.U_sequence = uSequence;
    }

    public int getUQuantity() {
        return uQuantity;
    }

    public void setUQuantity(int uQuantity) {
        this.uQuantity = uQuantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUIsActive() {
        return uIsActive;
    }

    public void setUIsActive(String uIsActive) {
        this.uIsActive = uIsActive;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWccode() {
        return wccode;
    }

    public void setWccode(String wccode) {
        this.wccode = wccode;
    }

    public String getPlannedQty() {
        return plannedQty;
    }

    public void setPlannedQty(String plannedQty) {
        this.plannedQty = plannedQty;
    }

    public String getUItemCode() {
        return U_ItemCode;
    }

    public void setUItemCode(String uItemCode) {
        this.U_ItemCode = uItemCode;
    }

    public String getUWCCode() {
        return U_WCCode;
    }

    public void setUWCCode(String uWCCode) {
        this.U_WCCode = uWCCode;
    }

    public String getUCriteria() {
        return U_Criteria;
    }

    public void setUCriteria(String uCriteria) {
        this.U_Criteria = uCriteria;
    }

    public String getUStandard() {
        return U_Standard;
    }

    public void setUStandard(String uStandard) {
        this.U_Standard = uStandard;
    }

    public Object getUCriteriaName() {
        return U_CriteriaName;
    }

    public void setUCriteriaName(Object uCriteriaName) {
        this.U_CriteriaName = uCriteriaName;
    }

    public String getUValueType() {
        return U_ValueType;
    }

    public void setUValueType(String uValueType) {
        this.U_ValueType = uValueType;
    }


    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(seq);
        dest.writeValue(U_sequence);
        dest.writeValue(uQuantity);
        dest.writeValue(itemCode);
        dest.writeValue(docNum);
        dest.writeValue(status);
        dest.writeValue(itemName);
        dest.writeValue(uDesc);
        dest.writeValue(uIsActive);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(wccode);
        dest.writeValue(plannedQty);
        dest.writeValue(U_ItemCode);
        dest.writeValue(U_WCCode);
        dest.writeValue(U_Criteria);
        dest.writeValue(U_Standard);
        dest.writeValue(U_CriteriaName);
        dest.writeValue(U_ValueType);
        dest.writeValue(actualResult);
        dest.writeValue(lineNumber);
    }

    public int describeContents() {
        return 0;
    }

   /* private String U_ItemCode;
    private String U_WCCode;
    private String U_Criteria;
    private String U_Standard;
    private Object U_CriteriaName;
    private String U_ValueType;
    public final static Parcelable.Creator<Criteria> CREATOR = new Creator<Criteria>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Criteria createFromParcel(Parcel in) {
            return new Criteria(in);
        }

        public Criteria[] newArray(int size) {
            return (new Criteria[size]);
        }

    };

    protected Criteria(Parcel in) {
        this.U_ItemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.U_WCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.U_Criteria = ((String) in.readValue((String.class.getClassLoader())));
        this.U_Standard = ((String) in.readValue((String.class.getClassLoader())));
        this.U_CriteriaName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.U_ValueType = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Criteria() {
    }

    public String getUItemCode() {
        return U_ItemCode;
    }

    public void setUItemCode(String uItemCode) {
        this.U_ItemCode = uItemCode;
    }

    public String getUWCCode() {
        return U_WCCode;
    }

    public void setUWCCode(String uWCCode) {
        this.U_WCCode = uWCCode;
    }

    public String getUCriteria() {
        return U_Criteria;
    }

    public void setUCriteria(String uCriteria) {
        this.U_Criteria = uCriteria;
    }

    public String getUStandard() {
        return U_Standard;
    }

    public void setUStandard(String uStandard) {
        this.U_Standard = uStandard;
    }

    public Object getUCriteriaName() {
        return U_CriteriaName;
    }

    public void setUCriteriaName(Object uCriteriaName) {
        this.U_CriteriaName = uCriteriaName;
    }

    public String getUValueType() {
        return U_ValueType;
    }

    public void setUValueType(String uValueType) {
        this.U_ValueType = uValueType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(U_ItemCode);
        dest.writeValue(U_WCCode);
        dest.writeValue(U_Criteria);
        dest.writeValue(U_Standard);
        dest.writeValue(U_CriteriaName);
        dest.writeValue(U_ValueType);
    }

    public int describeContents() {
        return 0;
    }*/
}