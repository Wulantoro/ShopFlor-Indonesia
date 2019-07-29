package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class Upcriteria implements Parcelable
{

    private int docEntry;
    private Object lineNumber;
    private Object criteria;
    private Object criteriaDesc;
    private Object valueType;
    private Object standard;
    private Object actualResult;
    private Object actualRemarks;
    public final static Parcelable.Creator<Upcriteria> CREATOR = new Creator<Upcriteria>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Upcriteria createFromParcel(Parcel in) {
            return new Upcriteria(in);
        }

        public Upcriteria[] newArray(int size) {
            return (new Upcriteria[size]);
        }

    }
            ;

    protected Upcriteria(Parcel in) {
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.lineNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.criteria = ((Object) in.readValue((Object.class.getClassLoader())));
        this.criteriaDesc = ((Object) in.readValue((Object.class.getClassLoader())));
        this.valueType = ((Object) in.readValue((Object.class.getClassLoader())));
        this.standard = ((Object) in.readValue((Object.class.getClassLoader())));
        this.actualResult = ((Object) in.readValue((Object.class.getClassLoader())));
        this.actualRemarks = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Upcriteria() {
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

    public Object getCriteria() {
        return criteria;
    }

    public void setCriteria(Object criteria) {
        this.criteria = criteria;
    }

    public Object getCriteriaDesc() {
        return criteriaDesc;
    }

    public void setCriteriaDesc(Object criteriaDesc) {
        this.criteriaDesc = criteriaDesc;
    }

    public Object getValueType() {
        return valueType;
    }

    public void setValueType(Object valueType) {
        this.valueType = valueType;
    }

    public Object getStandard() {
        return standard;
    }

    public void setStandard(Object standard) {
        this.standard = standard;
    }

    public Object getActualResult() {
        return actualResult;
    }

    public void setActualResult(Object actualResult) {
        this.actualResult = actualResult;
    }

    public Object getActualRemarks() {
        return actualRemarks;
    }

    public void setActualRemarks(Object actualRemarks) {
        this.actualRemarks = actualRemarks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docEntry);
        dest.writeValue(lineNumber);
        dest.writeValue(criteria);
        dest.writeValue(criteriaDesc);
        dest.writeValue(valueType);
        dest.writeValue(standard);
        dest.writeValue(actualResult);
        dest.writeValue(actualRemarks);
    }

    public int describeContents() {
        return 0;
    }

}

/*public class Upcriteria implements Parcelable
{

    private int docEntry;
    private int id;
    private int hostHeadEntry;
    private int lineNumber;
    private String rejectCode;
    private String rejectName;
    private String rejectQty;
    public final static Parcelable.Creator<Upcriteria> CREATOR = new Creator<Upcriteria>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Upcriteria createFromParcel(Parcel in) {
            return new Upcriteria(in);
        }

        public Upcriteria[] newArray(int size) {
            return (new Upcriteria[size]);
        }

    }
            ;

    protected Upcriteria(Parcel in) {
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hostHeadEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.lineNumber = ((int) in.readValue((int.class.getClassLoader())));
        this.rejectCode = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectName = ((String) in.readValue((String.class.getClassLoader())));
        this.rejectQty = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Upcriteria() {
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

}*/
