package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Reject implements Parcelable {

    private String code;
    private String name;
    public final static Parcelable.Creator<Reject> CREATOR = new Creator<Reject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Reject createFromParcel(Parcel in) {
            return new Reject(in);
        }

        public Reject[] newArray(int size) {
            return (new Reject[size]);
        }

    }
            ;

    protected Reject(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Reject() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

   /* private String code;
    private String name;
    private int docEntry;
    private String canceled;
    private String object;
    private int logInst;
    private int userSign;
    private String transfered;
    private String createDate;
    private int createTime;
    private String updateDate;
    private int updateTime;
    private String dataSource;
    private String uIsActive;
    public final static Parcelable.Creator<Reject> CREATOR = new Creator<Reject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Reject createFromParcel(Parcel in) {
            return new Reject(in);
        }

        public Reject[] newArray(int size) {
            return (new Reject[size]);
        }

    };

    protected Reject(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((Object.class.getClassLoader())));
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.canceled = ((String) in.readValue((String.class.getClassLoader())));
        this.object = ((String) in.readValue((String.class.getClassLoader())));
        this.logInst = ((int) in.readValue((int.class.getClassLoader())));
        this.userSign = ((int) in.readValue((int.class.getClassLoader())));
        this.transfered = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createTime = ((int) in.readValue((int.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateTime = ((int) in.readValue((int.class.getClassLoader())));
        this.dataSource = ((String) in.readValue((String.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Reject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(int docEntry) {
        this.docEntry = docEntry;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getLogInst() {
        return logInst;
    }

    public void setLogInst(int logInst) {
        this.logInst = logInst;
    }

    public int getUserSign() {
        return userSign;
    }

    public void setUserSign(int userSign) {
        this.userSign = userSign;
    }

    public String getTransfered() {
        return transfered;
    }

    public void setTransfered(String transfered) {
        this.transfered = transfered;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUIsActive() {
        return uIsActive;
    }

    public void setUIsActive(String uIsActive) {
        this.uIsActive = uIsActive;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(docEntry);
        dest.writeValue(canceled);
        dest.writeValue(object);
        dest.writeValue(logInst);
        dest.writeValue(userSign);
        dest.writeValue(transfered);
        dest.writeValue(createDate);
        dest.writeValue(createTime);
        dest.writeValue(updateDate);
        dest.writeValue(updateTime);
        dest.writeValue(dataSource);
        dest.writeValue(uIsActive);
    }

    public int describeContents() {
        return 0;
    }*/
}
