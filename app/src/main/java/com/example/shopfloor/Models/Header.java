package com.example.shopfloor.Models;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Header implements Parcelable
{

    private int docEntry;
    private int id;
    private String docNum;
    private String docDate;
    private String prodNo;
    private String prodCode;
    private String prodName;
    private String prodPlanQty;
    private String prodStatus;
    private String routeCode;
    private String routeName;
    private String sequence;
    private String sequenceQty;
    private String shift;
    private String shiftName;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String jamMulai;
    private String jamSelesai;
    private String inQty;
    private String outQty;
    private Object remarks;
    private String userId;
    private String QcName;
    private int posted;
    private int targetEntry;
    private String uploadTime;
    private String workCenter;
    private String status;
    public final static Parcelable.Creator<Header> CREATOR = new Creator<Header>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Header createFromParcel(Parcel in) {
            return new Header(in);
        }

        public Header[] newArray(int size) {
            return (new Header[size]);
        }

    }
            ;

    protected Header(Parcel in) {
        this.docEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.docNum = ((String) in.readValue((String.class.getClassLoader())));
        this.docDate = ((String) in.readValue((String.class.getClassLoader())));
        this.prodNo = ((String) in.readValue((String.class.getClassLoader())));
        this.prodCode = ((String) in.readValue((String.class.getClassLoader())));
        this.prodName = ((String) in.readValue((String.class.getClassLoader())));
        this.prodPlanQty = ((String) in.readValue((String.class.getClassLoader())));
        this.prodStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.routeCode = ((String) in.readValue((String.class.getClassLoader())));
        this.routeName = ((String) in.readValue((String.class.getClassLoader())));
        this.sequence = ((String) in.readValue((String.class.getClassLoader())));
        this.sequenceQty = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.shiftName = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalMulai = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalSelesai = ((String) in.readValue((String.class.getClassLoader())));
        this.jamMulai = ((String) in.readValue((String.class.getClassLoader())));
        this.jamSelesai = ((String) in.readValue((String.class.getClassLoader())));
        this.inQty = ((String) in.readValue((String.class.getClassLoader())));
        this.outQty = ((String) in.readValue((String.class.getClassLoader())));
        this.remarks = ((Object) in.readValue((Object.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.QcName = ((String) in.readValue((String.class.getClassLoader())));
        this.posted = ((int) in.readValue((int.class.getClassLoader())));
        this.targetEntry = ((int) in.readValue((int.class.getClassLoader())));
        this.uploadTime = ((String) in.readValue((String.class.getClassLoader())));
        this.workCenter = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Header() {
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

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPlanQty() {
        return prodPlanQty;
    }

    public void setProdPlanQty(String prodPlanQty) {
        this.prodPlanQty = prodPlanQty;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequenceQty() {
        return sequenceQty;
    }

    public void setSequenceQty(String sequenceQty) {
        this.sequenceQty = sequenceQty;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getInQty() {
        return inQty;
    }

    public void setInQty(String inQty) {
        this.inQty = inQty;
    }

    public String getOutQty() {
        return outQty;
    }

    public void setOutQty(String outQty) {
        this.outQty = outQty;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQcName() {
        return QcName;
    }

    public void setQcName(String mobileId) {
        this.QcName = QcName;
    }

    public int getPosted() {
        return posted;
    }

    public void setPosted(int posted) {
        this.posted = posted;
    }

    public int getTargetEntry() {
        return targetEntry;
    }

    public void setTargetEntry(int targetEntry) {
        this.targetEntry = targetEntry;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docEntry);
        dest.writeValue(id);
        dest.writeValue(docNum);
        dest.writeValue(docDate);
        dest.writeValue(prodNo);
        dest.writeValue(prodCode);
        dest.writeValue(prodName);
        dest.writeValue(prodPlanQty);
        dest.writeValue(prodStatus);
        dest.writeValue(routeCode);
        dest.writeValue(routeName);
        dest.writeValue(sequence);
        dest.writeValue(sequenceQty);
        dest.writeValue(shift);
        dest.writeValue(shiftName);
        dest.writeValue(tanggalMulai);
        dest.writeValue(tanggalSelesai);
        dest.writeValue(jamMulai);
        dest.writeValue(jamSelesai);
        dest.writeValue(inQty);
        dest.writeValue(outQty);
        dest.writeValue(remarks);
        dest.writeValue(userId);
        dest.writeValue(QcName);
        dest.writeValue(posted);
        dest.writeValue(targetEntry);
        dest.writeValue(uploadTime);
        dest.writeValue(workCenter);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }
}
