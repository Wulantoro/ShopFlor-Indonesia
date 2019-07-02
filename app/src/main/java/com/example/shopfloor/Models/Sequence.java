package com.example.shopfloor.Models;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;


public class Sequence implements Parcelable
{

    private int U_Sequence;
    private String U_Quantity;
    private String itemCode;
    private int docnum;
    private String status;
    private String itemName;
    private String uDesc;
    private String uIsActive;
    private String code;
    private String name;
    private String wccode;
    private String plannedQty;
    private String uWCCode;
    public final static Parcelable.Creator<Sequence> CREATOR = new Creator<Sequence>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sequence createFromParcel(Parcel in) {
            return new Sequence(in);
        }

        public Sequence[] newArray(int size) {
            return (new Sequence[size]);
        }

    }
            ;

    protected Sequence(Parcel in) {
        this.U_Sequence = ((int) in.readValue((int.class.getClassLoader())));
        this.U_Quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.docnum = ((int) in.readValue((int.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.uDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.wccode = ((String) in.readValue((String.class.getClassLoader())));
        this.plannedQty = ((String) in.readValue((String.class.getClassLoader())));
        this.uWCCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public int getUSequence() {
        return U_Sequence;
    }

    public void setUSequence(int uSequence) {
        this.U_Sequence = uSequence;
    }

    public String getUQuantity() {
        return U_Quantity;
    }

    public void setUQuantity(String uQuantity) {
        this.U_Quantity = uQuantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getDocnum() {
        return docnum;
    }

    public void setDocnum(int docnum) {
        this.docnum = docnum;
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

    public String getUWCCode() {
        return uWCCode;
    }

    public void setUWCCode(String uWCCode) {
        this.uWCCode = uWCCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(U_Sequence);
        dest.writeValue(U_Quantity);
        dest.writeValue(itemCode);
        dest.writeValue(docnum);
        dest.writeValue(status);
        dest.writeValue(itemName);
        dest.writeValue(uDesc);
        dest.writeValue(uIsActive);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(wccode);
        dest.writeValue(plannedQty);
        dest.writeValue(uWCCode);
    }

    public int describeContents() {
        return 0;
    }

/*public class Sequence implements Parcelable
{

    private int U_Sequence;
    private String U_Quantity;
    private String itemCode;
    private int DocNum;
    private String status;
    private String itemName;
    private String uDesc;
    private String uIsActive;
    private String code;
    private String name;
    private String wccode;
    private String plannedQty;
    private String U_WCCode;
    public final static Parcelable.Creator<Sequence> CREATOR = new Creator<Sequence>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sequence createFromParcel(Parcel in) {
            return new Sequence(in);
        }

        public Sequence[] newArray(int size) {
            return (new Sequence[size]);
        }

    }
            ;

    protected Sequence(Parcel in) {
        this.U_Sequence = ((int) in.readValue((int.class.getClassLoader())));
        this.U_Quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.DocNum = ((int) in.readValue((int.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.uDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.uIsActive = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.wccode = ((String) in.readValue((String.class.getClassLoader())));
        this.plannedQty = ((String) in.readValue((String.class.getClassLoader())));
        this.U_WCCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Sequence() {
    }

    public int getUSequence() {
        return U_Sequence;
    }

    public void setUSequence(int uSequence) {
        this.U_Sequence = uSequence;
    }

    public String getUQuantity() {
        return U_Quantity;
    }

    public void setUQuantity(String uQuantity) {
        this.U_Quantity = uQuantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getDocNum() {
        return DocNum;
    }

    public void setDocNum(int docNum) {
        this.DocNum = docNum;
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

    public String getU_WCCode() {
        return U_WCCode;
    }

    public void setU_WCCode(String u_WCCode) {
        this.U_WCCode = u_WCCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(U_Sequence);
        dest.writeValue(U_Quantity);
        dest.writeValue(itemCode);
        dest.writeValue(DocNum);
        dest.writeValue(status);
        dest.writeValue(itemName);
        dest.writeValue(uDesc);
        dest.writeValue(uIsActive);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(wccode);
        dest.writeValue(plannedQty);
        dest.writeValue(U_WCCode);
    }

    public int describeContents() {
        return 0;
    }

/*public class Sequence implements Parcelable {

    private int U_Sequence;
    private String U_Quantity;
    public final static Parcelable.Creator<Sequence> CREATOR = new Creator<Sequence>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sequence createFromParcel(Parcel in) {
            return new Sequence(in);
        }

        public Sequence[] newArray(int size) {
            return (new Sequence[size]);
        }

    };

    protected Sequence(Parcel in) {
        this.U_Sequence = ((int) in.readValue((int.class.getClassLoader())));
        this.U_Quantity = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Sequence() {
    }

    public int getUSequence() {
        return U_Sequence;
    }

    public void setUSequence(int uSequence) {
        this.U_Sequence = uSequence;
    }

    public String getUQuantity() {
        return U_Quantity;
    }

    public void setUQuantity(String uQuantity) {
        this.U_Quantity = uQuantity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(U_Sequence);
        dest.writeValue(U_Quantity);
    }

    public int describeContents() {
        return 0;
    }*/
}