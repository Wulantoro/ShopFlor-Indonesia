package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SincHeader implements Parcelable
{

//    private int docEntry;
    private int DocEntry;
    private int DocNum;
    private int period;
    private int instance;
    private int series;
    private String handwrtten;
    private String canceled;
    private String object;
//    private Object logInst;
    private int LogInst;
    private int userSign;
    private String transfered;
    private String status;
    private String createDate;
    private int createTime;
    private String updateDate;
    private int updateTime;
    private String dataSource;
    private String uDocDate;
    private String uPDEntry;
    private String uPDNo;
    private int uSequence;
    private String uProduct;
    private String uProductDesc;
    private String uPlannedQty;
    private String uSequenceQty;
    private String uGRQty;
    private String uWCCode;
    private String uWCDesc;
    private String uInputQty;
    private String uOutputQty;
    private String uPDStatus;
    private String uRouteCode;
    private String uRouteDesc;
//    private String uShift;
    private String U_Shift;
    private String uShiftDesc;
    private String uRemarks;
    private Object uNoLot;
    private String uNoLotKeluar;
    private Object uOperator;
    private Object uPosConveyor;
    private Object uSectHead;
    private Object uSupervisor;
    private String uTglMulai;
    private String uTglSelesai;
    private int uJamMulai;
    private int uJamSelesai;
    private Object uSubCon;
    private String uCreatedBy;
    private String uUpdatedBy;
    public final static Parcelable.Creator<SincHeader> CREATOR = new Creator<SincHeader>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SincHeader createFromParcel(Parcel in) {
            return new SincHeader(in);
        }

        public SincHeader[] newArray(int size) {
            return (new SincHeader[size]);
        }

    }
            ;

    protected SincHeader(Parcel in) {
        this.DocEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.DocNum = ((int) in.readValue((int.class.getClassLoader())));
        this.period = ((int) in.readValue((int.class.getClassLoader())));
        this.instance = ((int) in.readValue((int.class.getClassLoader())));
        this.series = ((int) in.readValue((int.class.getClassLoader())));
        this.handwrtten = ((String) in.readValue((String.class.getClassLoader())));
        this.canceled = ((String) in.readValue((String.class.getClassLoader())));
        this.object = ((String) in.readValue((String.class.getClassLoader())));
        this.LogInst = (int) in.readValue((Object.class.getClassLoader()));
        this.userSign = ((int) in.readValue((int.class.getClassLoader())));
        this.transfered = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createTime = ((int) in.readValue((int.class.getClassLoader())));
        this.updateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updateTime = ((int) in.readValue((int.class.getClassLoader())));
        this.dataSource = ((String) in.readValue((String.class.getClassLoader())));
        this.uDocDate = ((String) in.readValue((String.class.getClassLoader())));
        this.uPDEntry = ((String) in.readValue((String.class.getClassLoader())));
        this.uPDNo = ((String) in.readValue((String.class.getClassLoader())));
        this.uSequence = ((int) in.readValue((int.class.getClassLoader())));
        this.uProduct = ((String) in.readValue((String.class.getClassLoader())));
        this.uProductDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uPlannedQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uSequenceQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uGRQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uWCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.uWCDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uInputQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uOutputQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uPDStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.uRouteCode = ((String) in.readValue((String.class.getClassLoader())));
        this.uRouteDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.U_Shift = ((String) in.readValue((String.class.getClassLoader())));
        this.uShiftDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uRemarks = ((String) in.readValue((String.class.getClassLoader())));
        this.uNoLot = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uNoLotKeluar = ((String) in.readValue((String.class.getClassLoader())));
        this.uOperator = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uPosConveyor = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uSectHead = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uSupervisor = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uTglMulai = ((String) in.readValue((String.class.getClassLoader())));
        this.uTglSelesai = ((String) in.readValue((String.class.getClassLoader())));
        this.uJamMulai = ((int) in.readValue((int.class.getClassLoader())));
        this.uJamSelesai = ((int) in.readValue((int.class.getClassLoader())));
        this.uSubCon = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uCreatedBy = ((String) in.readValue((String.class.getClassLoader())));
        this.uUpdatedBy = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SincHeader() {
    }

    public int getDocEntry() {
        return DocEntry;
    }

    public void setDocEntry(int docEntry) {
        this.DocEntry = docEntry;
    }

    public int getDocNum() {
        return DocNum;
    }

    public void setDocNum(int docNum) {
        this.DocNum = docNum;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getHandwrtten() {
        return handwrtten;
    }

    public void setHandwrtten(String handwrtten) {
        this.handwrtten = handwrtten;
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
        return LogInst;
    }

    public void setLogInst(Object logInst) {
        this.LogInst = (int) logInst;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUDocDate() {
        return uDocDate;
    }

    public void setUDocDate(String uDocDate) {
        this.uDocDate = uDocDate;
    }

    public String getUPDEntry() {
        return uPDEntry;
    }

    public void setUPDEntry(String uPDEntry) {
        this.uPDEntry = uPDEntry;
    }

    public String getUPDNo() {
        return uPDNo;
    }

    public void setUPDNo(String uPDNo) {
        this.uPDNo = uPDNo;
    }

    public int getUSequence() {
        return uSequence;
    }

    public void setUSequence(int uSequence) {
        this.uSequence = uSequence;
    }

    public String getUProduct() {
        return uProduct;
    }

    public void setUProduct(String uProduct) {
        this.uProduct = uProduct;
    }

    public String getUProductDesc() {
        return uProductDesc;
    }

    public void setUProductDesc(String uProductDesc) {
        this.uProductDesc = uProductDesc;
    }

    public String getUPlannedQty() {
        return uPlannedQty;
    }

    public void setUPlannedQty(String uPlannedQty) {
        this.uPlannedQty = uPlannedQty;
    }

    public String getUSequenceQty() {
        return uSequenceQty;
    }

    public void setUSequenceQty(String uSequenceQty) {
        this.uSequenceQty = uSequenceQty;
    }

    public String getUGRQty() {
        return uGRQty;
    }

    public void setUGRQty(String uGRQty) {
        this.uGRQty = uGRQty;
    }

    public String getUWCCode() {
        return uWCCode;
    }

    public void setUWCCode(String uWCCode) {
        this.uWCCode = uWCCode;
    }

    public String getUWCDesc() {
        return uWCDesc;
    }

    public void setUWCDesc(String uWCDesc) {
        this.uWCDesc = uWCDesc;
    }

    public String getUInputQty() {
        return uInputQty;
    }

    public void setUInputQty(String uInputQty) {
        this.uInputQty = uInputQty;
    }

    public String getUOutputQty() {
        return uOutputQty;
    }

    public void setUOutputQty(String uOutputQty) {
        this.uOutputQty = uOutputQty;
    }

    public String getUPDStatus() {
        return uPDStatus;
    }

    public void setUPDStatus(String uPDStatus) {
        this.uPDStatus = uPDStatus;
    }

    public String getURouteCode() {
        return uRouteCode;
    }

    public void setURouteCode(String uRouteCode) {
        this.uRouteCode = uRouteCode;
    }

    public String getURouteDesc() {
        return uRouteDesc;
    }

    public void setURouteDesc(String uRouteDesc) {
        this.uRouteDesc = uRouteDesc;
    }

    public String getUShift() {
        return U_Shift;
    }

    public void setUShift(String uShift) {
        this.U_Shift = uShift;
    }

    public String getUShiftDesc() {
        return uShiftDesc;
    }

    public void setUShiftDesc(String uShiftDesc) {
        this.uShiftDesc = uShiftDesc;
    }

    public String getURemarks() {
        return uRemarks;
    }

    public void setURemarks(String uRemarks) {
        this.uRemarks = uRemarks;
    }

    public Object getUNoLot() {
        return uNoLot;
    }

    public void setUNoLot(Object uNoLot) {
        this.uNoLot = uNoLot;
    }

    public String getUNoLotKeluar() {
        return uNoLotKeluar;
    }

    public void setUNoLotKeluar(String uNoLotKeluar) {
        this.uNoLotKeluar = uNoLotKeluar;
    }

    public Object getUOperator() {
        return uOperator;
    }

    public void setUOperator(Object uOperator) {
        this.uOperator = uOperator;
    }

    public Object getUPosConveyor() {
        return uPosConveyor;
    }

    public void setUPosConveyor(Object uPosConveyor) {
        this.uPosConveyor = uPosConveyor;
    }

    public Object getUSectHead() {
        return uSectHead;
    }

    public void setUSectHead(Object uSectHead) {
        this.uSectHead = uSectHead;
    }

    public Object getUSupervisor() {
        return uSupervisor;
    }

    public void setUSupervisor(Object uSupervisor) {
        this.uSupervisor = uSupervisor;
    }

    public String getUTglMulai() {
        return uTglMulai;
    }

    public void setUTglMulai(String uTglMulai) {
        this.uTglMulai = uTglMulai;
    }

    public String getUTglSelesai() {
        return uTglSelesai;
    }

    public void setUTglSelesai(String uTglSelesai) {
        this.uTglSelesai = uTglSelesai;
    }

    public int getUJamMulai() {
        return uJamMulai;
    }

    public void setUJamMulai(int uJamMulai) {
        this.uJamMulai = uJamMulai;
    }

    public int getUJamSelesai() {
        return uJamSelesai;
    }

    public void setUJamSelesai(int uJamSelesai) {
        this.uJamSelesai = uJamSelesai;
    }

    public Object getUSubCon() {
        return uSubCon;
    }

    public void setUSubCon(Object uSubCon) {
        this.uSubCon = uSubCon;
    }

    public String getUCreatedBy() {
        return uCreatedBy;
    }

    public void setUCreatedBy(String uCreatedBy) {
        this.uCreatedBy = uCreatedBy;
    }

    public String getUUpdatedBy() {
        return uUpdatedBy;
    }

    public void setUUpdatedBy(String uUpdatedBy) {
        this.uUpdatedBy = uUpdatedBy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(DocEntry);
        dest.writeValue(DocNum);
        dest.writeValue(period);
        dest.writeValue(instance);
        dest.writeValue(series);
        dest.writeValue(handwrtten);
        dest.writeValue(canceled);
        dest.writeValue(object);
        dest.writeValue(LogInst);
        dest.writeValue(userSign);
        dest.writeValue(transfered);
        dest.writeValue(status);
        dest.writeValue(createDate);
        dest.writeValue(createTime);
        dest.writeValue(updateDate);
        dest.writeValue(updateTime);
        dest.writeValue(dataSource);
        dest.writeValue(uDocDate);
        dest.writeValue(uPDEntry);
        dest.writeValue(uPDNo);
        dest.writeValue(uSequence);
        dest.writeValue(uProduct);
        dest.writeValue(uProductDesc);
        dest.writeValue(uPlannedQty);
        dest.writeValue(uSequenceQty);
        dest.writeValue(uGRQty);
        dest.writeValue(uWCCode);
        dest.writeValue(uWCDesc);
        dest.writeValue(uInputQty);
        dest.writeValue(uOutputQty);
        dest.writeValue(uPDStatus);
        dest.writeValue(uRouteCode);
        dest.writeValue(uRouteDesc);
        dest.writeValue(U_Shift);
        dest.writeValue(uShiftDesc);
        dest.writeValue(uRemarks);
        dest.writeValue(uNoLot);
        dest.writeValue(uNoLotKeluar);
        dest.writeValue(uOperator);
        dest.writeValue(uPosConveyor);
        dest.writeValue(uSectHead);
        dest.writeValue(uSupervisor);
        dest.writeValue(uTglMulai);
        dest.writeValue(uTglSelesai);
        dest.writeValue(uJamMulai);
        dest.writeValue(uJamSelesai);
        dest.writeValue(uSubCon);
        dest.writeValue(uCreatedBy);
        dest.writeValue(uUpdatedBy);
    }

    public int describeContents() {
        return 0;
    }

}