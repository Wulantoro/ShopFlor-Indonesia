package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Workcenter implements Parcelable {

   /* private String code;
    private String name;
    private int docEntry;
    private String canceled;
    private String object;
    private Object logInst;
    private int userSign;
    private String transfered;
    private String createDate;
    private int createTime;
    private Object updateDate;
    private Object updateTime;
    private String dataSource;
    private String uGroupCode;
    private String uIsActive;
    public final static Parcelable.Creator<Workcenter> CREATOR = new Creator<Workcenter>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Workcenter createFromParcel(Parcel in) {
            return new Workcenter(in);
        }
        public Workcenter[] newArray(int size) {
            return (new Workcenter[size]);
        }
    };
    protected Workcenter(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.canceled = ((String) in.readValue((String.class.getClassLoader())));
        this.object = ((String) in.readValue((String.class.getClassLoader())));
        this.logInst = ((Object) in.readValue((Object.class.getClassLoader())));
        this.userSign = ((int) in.readValue((int.class.getClassLoader())));
        this.transfered = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createTime = ((int) in.readValue((int.class.getClassLoader())));
        this.updateDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updateTime = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dataSource = ((String) in.readValue((String.class.getClassLoader())));
        this.uGroupCode = ((String) in.readValue((String.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
    }
    public Workcenter() {
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
    public Object getLogInst() {
        return logInst;
    }
    public void setLogInst(Object logInst) {
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
    public Object getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }
    public Object getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }
    public String getDataSource() {
        return dataSource;
    }
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    public String getUGroupCode() {
        return uGroupCode;
    }
    public void setUGroupCode(String uGroupCode) {
        this.uGroupCode = uGroupCode;
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
        dest.writeValue(uGroupCode);
        dest.writeValue(uIsActive);
    }
    public int describeContents() {
        return 0;
    }*/


  private String code;
  private String name;
  private int docEntry;
  private String canceled;
  private String object;
  private Object logInst;
  private int userSign;
  private String transfered;
  private String createDate;
  private int createTime;
  private Object updateDate;
  private Object updateTime;
  private String dataSource;
  private String uGroupCode;
  private String uIsActive;
  public final static Parcelable.Creator<Workcenter> CREATOR = new Creator<Workcenter>() {


    @SuppressWarnings({
            "unchecked"
    })
    public Workcenter createFromParcel(Parcel in) {
      return new Workcenter(in);
    }

    public Workcenter[] newArray(int size) {
      return (new Workcenter[size]);
    }

  }
          ;

  protected Workcenter(Parcel in) {
    this.code = ((String) in.readValue((String.class.getClassLoader())));
    this.name = ((String) in.readValue((String.class.getClassLoader())));
    this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
    this.canceled = ((String) in.readValue((String.class.getClassLoader())));
    this.object = ((String) in.readValue((String.class.getClassLoader())));
    this.logInst = ((Object) in.readValue((Object.class.getClassLoader())));
    this.userSign = ((int) in.readValue((int.class.getClassLoader())));
    this.transfered = ((String) in.readValue((String.class.getClassLoader())));
    this.createDate = ((String) in.readValue((String.class.getClassLoader())));
    this.createTime = ((int) in.readValue((int.class.getClassLoader())));
    this.updateDate = ((Object) in.readValue((Object.class.getClassLoader())));
    this.updateTime = ((Object) in.readValue((Object.class.getClassLoader())));
    this.dataSource = ((String) in.readValue((String.class.getClassLoader())));
    this.uGroupCode = ((String) in.readValue((String.class.getClassLoader())));
    this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Workcenter() {
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

  public Object getLogInst() {
    return logInst;
  }

  public void setLogInst(Object logInst) {
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

  public Object getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Object updateDate) {
    this.updateDate = updateDate;
  }

  public Object getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Object updateTime) {
    this.updateTime = updateTime;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public String getUGroupCode() {
    return uGroupCode;
  }

  public void setUGroupCode(String uGroupCode) {
    this.uGroupCode = uGroupCode;
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
    dest.writeValue(uGroupCode);
    dest.writeValue(uIsActive);
  }

  public int describeContents() {
    return 0;
  }

}