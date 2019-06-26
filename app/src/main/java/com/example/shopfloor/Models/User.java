package com.example.shopfloor.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class User implements Parcelable
{

    private int empID;
    private String lastName;
    private String firstName;
    private Object middleName;
    private String sex;
    private Object jobTitle;
    private Object type;
    private int dept;
    private Object branch;
    private Object workStreet;
    private Object workBlock;
    private Object workZip;
    private Object workCity;
    private Object workCounty;
    private Object workCountr;
    private Object workState;
    private Object manager;
    private Object userId;
    private Object salesPrson;
    private Object officeTel;
    private Object officeExt;
    private Object mobile;
    private Object pager;
    private Object homeTel;
    private Object fax;
    private Object email;
    private Object startDate;
    private Object status;
    private String salary;
    private String salaryUnit;
    private String emplCost;
    private String empCostUnt;
    private Object termDate;
    private Object termReason;
    private String bankCode;
    private Object bankBranch;
    private Object bankBranNo;
    private Object bankAcount;
    private Object homeStreet;
    private Object homeBlock;
    private Object homeZip;
    private Object homeCity;
    private Object homeCounty;
    private Object homeCountr;
    private Object homeState;
    private Object birthDate;
    private Object brthCountr;
    private String martStatus;
    private Object nChildren;
    private Object govID;
    private Object citizenshp;
    private Object passportNo;
    private Object passportEx;
    private Object picture;
    private Object remark;
    private Object attachment;
    private String salaryCurr;
    private String empCostCur;
    private Object workBuild;
    private Object homeBuild;
    private Object position;
    private Object atcEntry;
    private Object addrTypeW;
    private Object addrTypeH;
    private Object streetNoW;
    private Object streetNoH;
    private String dispMidNam;
    private String namePos;
    private String dispComma;
    private Object costCenter;
    private Object companyNum;
    private Object vacPreYear;
    private Object vacCurYear;
    private Object munKey;
    private String taxClass;
    private String inTaxLiabi;
    private String emTaxCCode;
    private String relPartner;
    private String exemptAmnt;
    private String exemptUnit;
    private String addiAmnt;
    private String addiUnit;
    private Object taxOName;
    private Object taxONum;
    private Object heaInsName;
    private Object heaInsCode;
    private String heaInsType;
    private Object sInsurNum;
    private String statusOfP;
    private String statusOfE;
    private Object bCodeDateV;
    private String devBAOwner;
    private Object fNameSP;
    private Object surnameSP;
    private int logInstanc;
    private int userSign;
    private Object userSign2;
    private Object updateDate;
    private String persGroup;
    private Object jTCode;
    private Object extEmpNo;
    private Object birthPlace;
    private String pymMeth;
    private String exemptCurr;
    private String addiCurr;
    private Object sTDCode;
    private Object fatherName;
    private Object cPF;
    private Object cRC;
    private String contResp;
    private String repLegal;
    private String dirfDeclar;
    private Object uFCRC;
    private Object iDType;
    private String active;
    private Object bPLId;
    private Object manualNUM;
    private Object passIssue;
    private Object passIssuer;
    private String qualCode;
    private String pRWebAccss;
    private String prePRWeb;
//    private String uSTEMUsername;
    private String U_STEM_Username;
//    private String uSTEMPassword;
    private String U_STEM_Password;
    private Object uSTEMMobileId;
    private Object uSTEMGcmId;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
            ;

    protected User(Parcel in) {
        this.empID = ((int) in.readValue((int.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.middleName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.sex = ((String) in.readValue((String.class.getClassLoader())));
        this.jobTitle = ((Object) in.readValue((Object.class.getClassLoader())));
        this.type = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dept = ((int) in.readValue((int.class.getClassLoader())));
        this.branch = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workStreet = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workBlock = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workZip = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workCity = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workCounty = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workCountr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.workState = ((Object) in.readValue((Object.class.getClassLoader())));
        this.manager = ((Object) in.readValue((Object.class.getClassLoader())));
        this.userId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.salesPrson = ((Object) in.readValue((Object.class.getClassLoader())));
        this.officeTel = ((Object) in.readValue((Object.class.getClassLoader())));
        this.officeExt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mobile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.pager = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeTel = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fax = ((Object) in.readValue((Object.class.getClassLoader())));
        this.email = ((Object) in.readValue((Object.class.getClassLoader())));
        this.startDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.status = ((Object) in.readValue((Object.class.getClassLoader())));
        this.salary = ((String) in.readValue((String.class.getClassLoader())));
        this.salaryUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.emplCost = ((String) in.readValue((String.class.getClassLoader())));
        this.empCostUnt = ((String) in.readValue((String.class.getClassLoader())));
        this.termDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.termReason = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankCode = ((String) in.readValue((String.class.getClassLoader())));
        this.bankBranch = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankBranNo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankAcount = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeStreet = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeBlock = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeZip = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeCity = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeCounty = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeCountr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeState = ((Object) in.readValue((Object.class.getClassLoader())));
        this.birthDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.brthCountr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.martStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.nChildren = ((Object) in.readValue((Object.class.getClassLoader())));
        this.govID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.citizenshp = ((Object) in.readValue((Object.class.getClassLoader())));
        this.passportNo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.passportEx = ((Object) in.readValue((Object.class.getClassLoader())));
        this.picture = ((Object) in.readValue((Object.class.getClassLoader())));
        this.remark = ((Object) in.readValue((Object.class.getClassLoader())));
        this.attachment = ((Object) in.readValue((Object.class.getClassLoader())));
        this.salaryCurr = ((String) in.readValue((String.class.getClassLoader())));
        this.empCostCur = ((String) in.readValue((String.class.getClassLoader())));
        this.workBuild = ((Object) in.readValue((Object.class.getClassLoader())));
        this.homeBuild = ((Object) in.readValue((Object.class.getClassLoader())));
        this.position = ((Object) in.readValue((Object.class.getClassLoader())));
        this.atcEntry = ((Object) in.readValue((Object.class.getClassLoader())));
        this.addrTypeW = ((Object) in.readValue((Object.class.getClassLoader())));
        this.addrTypeH = ((Object) in.readValue((Object.class.getClassLoader())));
        this.streetNoW = ((Object) in.readValue((Object.class.getClassLoader())));
        this.streetNoH = ((Object) in.readValue((Object.class.getClassLoader())));
        this.dispMidNam = ((String) in.readValue((String.class.getClassLoader())));
        this.namePos = ((String) in.readValue((String.class.getClassLoader())));
        this.dispComma = ((String) in.readValue((String.class.getClassLoader())));
        this.costCenter = ((Object) in.readValue((Object.class.getClassLoader())));
        this.companyNum = ((Object) in.readValue((Object.class.getClassLoader())));
        this.vacPreYear = ((Object) in.readValue((Object.class.getClassLoader())));
        this.vacCurYear = ((Object) in.readValue((Object.class.getClassLoader())));
        this.munKey = ((Object) in.readValue((Object.class.getClassLoader())));
        this.taxClass = ((String) in.readValue((String.class.getClassLoader())));
        this.inTaxLiabi = ((String) in.readValue((String.class.getClassLoader())));
        this.emTaxCCode = ((String) in.readValue((String.class.getClassLoader())));
        this.relPartner = ((String) in.readValue((String.class.getClassLoader())));
        this.exemptAmnt = ((String) in.readValue((String.class.getClassLoader())));
        this.exemptUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.addiAmnt = ((String) in.readValue((String.class.getClassLoader())));
        this.addiUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.taxOName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.taxONum = ((Object) in.readValue((Object.class.getClassLoader())));
        this.heaInsName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.heaInsCode = ((Object) in.readValue((Object.class.getClassLoader())));
        this.heaInsType = ((String) in.readValue((String.class.getClassLoader())));
        this.sInsurNum = ((Object) in.readValue((Object.class.getClassLoader())));
        this.statusOfP = ((String) in.readValue((String.class.getClassLoader())));
        this.statusOfE = ((String) in.readValue((String.class.getClassLoader())));
        this.bCodeDateV = ((Object) in.readValue((Object.class.getClassLoader())));
        this.devBAOwner = ((String) in.readValue((String.class.getClassLoader())));
        this.fNameSP = ((Object) in.readValue((Object.class.getClassLoader())));
        this.surnameSP = ((Object) in.readValue((Object.class.getClassLoader())));
        this.logInstanc = ((int) in.readValue((int.class.getClassLoader())));
        this.userSign = ((int) in.readValue((int.class.getClassLoader())));
        this.userSign2 = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updateDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.persGroup = ((String) in.readValue((String.class.getClassLoader())));
        this.jTCode = ((Object) in.readValue((Object.class.getClassLoader())));
        this.extEmpNo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.birthPlace = ((Object) in.readValue((Object.class.getClassLoader())));
        this.pymMeth = ((String) in.readValue((String.class.getClassLoader())));
        this.exemptCurr = ((String) in.readValue((String.class.getClassLoader())));
        this.addiCurr = ((String) in.readValue((String.class.getClassLoader())));
        this.sTDCode = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fatherName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.cPF = ((Object) in.readValue((Object.class.getClassLoader())));
        this.cRC = ((Object) in.readValue((Object.class.getClassLoader())));
        this.contResp = ((String) in.readValue((String.class.getClassLoader())));
        this.repLegal = ((String) in.readValue((String.class.getClassLoader())));
        this.dirfDeclar = ((String) in.readValue((String.class.getClassLoader())));
        this.uFCRC = ((Object) in.readValue((Object.class.getClassLoader())));
        this.iDType = ((Object) in.readValue((Object.class.getClassLoader())));
        this.active = ((String) in.readValue((String.class.getClassLoader())));
        this.bPLId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.manualNUM = ((Object) in.readValue((Object.class.getClassLoader())));
        this.passIssue = ((Object) in.readValue((Object.class.getClassLoader())));
        this.passIssuer = ((Object) in.readValue((Object.class.getClassLoader())));
        this.qualCode = ((String) in.readValue((String.class.getClassLoader())));
        this.pRWebAccss = ((String) in.readValue((String.class.getClassLoader())));
        this.prePRWeb = ((String) in.readValue((String.class.getClassLoader())));
        this.U_STEM_Username = ((String) in.readValue((String.class.getClassLoader())));
        this.U_STEM_Password = ((String) in.readValue((String.class.getClassLoader())));
        this.uSTEMMobileId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.uSTEMGcmId = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public User() {
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Object jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }

    public Object getWorkStreet() {
        return workStreet;
    }

    public void setWorkStreet(Object workStreet) {
        this.workStreet = workStreet;
    }

    public Object getWorkBlock() {
        return workBlock;
    }

    public void setWorkBlock(Object workBlock) {
        this.workBlock = workBlock;
    }

    public Object getWorkZip() {
        return workZip;
    }

    public void setWorkZip(Object workZip) {
        this.workZip = workZip;
    }

    public Object getWorkCity() {
        return workCity;
    }

    public void setWorkCity(Object workCity) {
        this.workCity = workCity;
    }

    public Object getWorkCounty() {
        return workCounty;
    }

    public void setWorkCounty(Object workCounty) {
        this.workCounty = workCounty;
    }

    public Object getWorkCountr() {
        return workCountr;
    }

    public void setWorkCountr(Object workCountr) {
        this.workCountr = workCountr;
    }

    public Object getWorkState() {
        return workState;
    }

    public void setWorkState(Object workState) {
        this.workState = workState;
    }

    public Object getManager() {
        return manager;
    }

    public void setManager(Object manager) {
        this.manager = manager;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getSalesPrson() {
        return salesPrson;
    }

    public void setSalesPrson(Object salesPrson) {
        this.salesPrson = salesPrson;
    }

    public Object getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(Object officeTel) {
        this.officeTel = officeTel;
    }

    public Object getOfficeExt() {
        return officeExt;
    }

    public void setOfficeExt(Object officeExt) {
        this.officeExt = officeExt;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getPager() {
        return pager;
    }

    public void setPager(Object pager) {
        this.pager = pager;
    }

    public Object getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(Object homeTel) {
        this.homeTel = homeTel;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalaryUnit() {
        return salaryUnit;
    }

    public void setSalaryUnit(String salaryUnit) {
        this.salaryUnit = salaryUnit;
    }

    public String getEmplCost() {
        return emplCost;
    }

    public void setEmplCost(String emplCost) {
        this.emplCost = emplCost;
    }

    public String getEmpCostUnt() {
        return empCostUnt;
    }

    public void setEmpCostUnt(String empCostUnt) {
        this.empCostUnt = empCostUnt;
    }

    public Object getTermDate() {
        return termDate;
    }

    public void setTermDate(Object termDate) {
        this.termDate = termDate;
    }

    public Object getTermReason() {
        return termReason;
    }

    public void setTermReason(Object termReason) {
        this.termReason = termReason;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Object getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(Object bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Object getBankBranNo() {
        return bankBranNo;
    }

    public void setBankBranNo(Object bankBranNo) {
        this.bankBranNo = bankBranNo;
    }

    public Object getBankAcount() {
        return bankAcount;
    }

    public void setBankAcount(Object bankAcount) {
        this.bankAcount = bankAcount;
    }

    public Object getHomeStreet() {
        return homeStreet;
    }

    public void setHomeStreet(Object homeStreet) {
        this.homeStreet = homeStreet;
    }

    public Object getHomeBlock() {
        return homeBlock;
    }

    public void setHomeBlock(Object homeBlock) {
        this.homeBlock = homeBlock;
    }

    public Object getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(Object homeZip) {
        this.homeZip = homeZip;
    }

    public Object getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(Object homeCity) {
        this.homeCity = homeCity;
    }

    public Object getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(Object homeCounty) {
        this.homeCounty = homeCounty;
    }

    public Object getHomeCountr() {
        return homeCountr;
    }

    public void setHomeCountr(Object homeCountr) {
        this.homeCountr = homeCountr;
    }

    public Object getHomeState() {
        return homeState;
    }

    public void setHomeState(Object homeState) {
        this.homeState = homeState;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getBrthCountr() {
        return brthCountr;
    }

    public void setBrthCountr(Object brthCountr) {
        this.brthCountr = brthCountr;
    }

    public String getMartStatus() {
        return martStatus;
    }

    public void setMartStatus(String martStatus) {
        this.martStatus = martStatus;
    }

    public Object getNChildren() {
        return nChildren;
    }

    public void setNChildren(Object nChildren) {
        this.nChildren = nChildren;
    }

    public Object getGovID() {
        return govID;
    }

    public void setGovID(Object govID) {
        this.govID = govID;
    }

    public Object getCitizenshp() {
        return citizenshp;
    }

    public void setCitizenshp(Object citizenshp) {
        this.citizenshp = citizenshp;
    }

    public Object getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Object passportNo) {
        this.passportNo = passportNo;
    }

    public Object getPassportEx() {
        return passportEx;
    }

    public void setPassportEx(Object passportEx) {
        this.passportEx = passportEx;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public String getSalaryCurr() {
        return salaryCurr;
    }

    public void setSalaryCurr(String salaryCurr) {
        this.salaryCurr = salaryCurr;
    }

    public String getEmpCostCur() {
        return empCostCur;
    }

    public void setEmpCostCur(String empCostCur) {
        this.empCostCur = empCostCur;
    }

    public Object getWorkBuild() {
        return workBuild;
    }

    public void setWorkBuild(Object workBuild) {
        this.workBuild = workBuild;
    }

    public Object getHomeBuild() {
        return homeBuild;
    }

    public void setHomeBuild(Object homeBuild) {
        this.homeBuild = homeBuild;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Object atcEntry) {
        this.atcEntry = atcEntry;
    }

    public Object getAddrTypeW() {
        return addrTypeW;
    }

    public void setAddrTypeW(Object addrTypeW) {
        this.addrTypeW = addrTypeW;
    }

    public Object getAddrTypeH() {
        return addrTypeH;
    }

    public void setAddrTypeH(Object addrTypeH) {
        this.addrTypeH = addrTypeH;
    }

    public Object getStreetNoW() {
        return streetNoW;
    }

    public void setStreetNoW(Object streetNoW) {
        this.streetNoW = streetNoW;
    }

    public Object getStreetNoH() {
        return streetNoH;
    }

    public void setStreetNoH(Object streetNoH) {
        this.streetNoH = streetNoH;
    }

    public String getDispMidNam() {
        return dispMidNam;
    }

    public void setDispMidNam(String dispMidNam) {
        this.dispMidNam = dispMidNam;
    }

    public String getNamePos() {
        return namePos;
    }

    public void setNamePos(String namePos) {
        this.namePos = namePos;
    }

    public String getDispComma() {
        return dispComma;
    }

    public void setDispComma(String dispComma) {
        this.dispComma = dispComma;
    }

    public Object getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Object costCenter) {
        this.costCenter = costCenter;
    }

    public Object getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Object companyNum) {
        this.companyNum = companyNum;
    }

    public Object getVacPreYear() {
        return vacPreYear;
    }

    public void setVacPreYear(Object vacPreYear) {
        this.vacPreYear = vacPreYear;
    }

    public Object getVacCurYear() {
        return vacCurYear;
    }

    public void setVacCurYear(Object vacCurYear) {
        this.vacCurYear = vacCurYear;
    }

    public Object getMunKey() {
        return munKey;
    }

    public void setMunKey(Object munKey) {
        this.munKey = munKey;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getInTaxLiabi() {
        return inTaxLiabi;
    }

    public void setInTaxLiabi(String inTaxLiabi) {
        this.inTaxLiabi = inTaxLiabi;
    }

    public String getEmTaxCCode() {
        return emTaxCCode;
    }

    public void setEmTaxCCode(String emTaxCCode) {
        this.emTaxCCode = emTaxCCode;
    }

    public String getRelPartner() {
        return relPartner;
    }

    public void setRelPartner(String relPartner) {
        this.relPartner = relPartner;
    }

    public String getExemptAmnt() {
        return exemptAmnt;
    }

    public void setExemptAmnt(String exemptAmnt) {
        this.exemptAmnt = exemptAmnt;
    }

    public String getExemptUnit() {
        return exemptUnit;
    }

    public void setExemptUnit(String exemptUnit) {
        this.exemptUnit = exemptUnit;
    }

    public String getAddiAmnt() {
        return addiAmnt;
    }

    public void setAddiAmnt(String addiAmnt) {
        this.addiAmnt = addiAmnt;
    }

    public String getAddiUnit() {
        return addiUnit;
    }

    public void setAddiUnit(String addiUnit) {
        this.addiUnit = addiUnit;
    }

    public Object getTaxOName() {
        return taxOName;
    }

    public void setTaxOName(Object taxOName) {
        this.taxOName = taxOName;
    }

    public Object getTaxONum() {
        return taxONum;
    }

    public void setTaxONum(Object taxONum) {
        this.taxONum = taxONum;
    }

    public Object getHeaInsName() {
        return heaInsName;
    }

    public void setHeaInsName(Object heaInsName) {
        this.heaInsName = heaInsName;
    }

    public Object getHeaInsCode() {
        return heaInsCode;
    }

    public void setHeaInsCode(Object heaInsCode) {
        this.heaInsCode = heaInsCode;
    }

    public String getHeaInsType() {
        return heaInsType;
    }

    public void setHeaInsType(String heaInsType) {
        this.heaInsType = heaInsType;
    }

    public Object getSInsurNum() {
        return sInsurNum;
    }

    public void setSInsurNum(Object sInsurNum) {
        this.sInsurNum = sInsurNum;
    }

    public String getStatusOfP() {
        return statusOfP;
    }

    public void setStatusOfP(String statusOfP) {
        this.statusOfP = statusOfP;
    }

    public String getStatusOfE() {
        return statusOfE;
    }

    public void setStatusOfE(String statusOfE) {
        this.statusOfE = statusOfE;
    }

    public Object getBCodeDateV() {
        return bCodeDateV;
    }

    public void setBCodeDateV(Object bCodeDateV) {
        this.bCodeDateV = bCodeDateV;
    }

    public String getDevBAOwner() {
        return devBAOwner;
    }

    public void setDevBAOwner(String devBAOwner) {
        this.devBAOwner = devBAOwner;
    }

    public Object getFNameSP() {
        return fNameSP;
    }

    public void setFNameSP(Object fNameSP) {
        this.fNameSP = fNameSP;
    }

    public Object getSurnameSP() {
        return surnameSP;
    }

    public void setSurnameSP(Object surnameSP) {
        this.surnameSP = surnameSP;
    }

    public int getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(int logInstanc) {
        this.logInstanc = logInstanc;
    }

    public int getUserSign() {
        return userSign;
    }

    public void setUserSign(int userSign) {
        this.userSign = userSign;
    }

    public Object getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Object userSign2) {
        this.userSign2 = userSign2;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getPersGroup() {
        return persGroup;
    }

    public void setPersGroup(String persGroup) {
        this.persGroup = persGroup;
    }

    public Object getJTCode() {
        return jTCode;
    }

    public void setJTCode(Object jTCode) {
        this.jTCode = jTCode;
    }

    public Object getExtEmpNo() {
        return extEmpNo;
    }

    public void setExtEmpNo(Object extEmpNo) {
        this.extEmpNo = extEmpNo;
    }

    public Object getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Object birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPymMeth() {
        return pymMeth;
    }

    public void setPymMeth(String pymMeth) {
        this.pymMeth = pymMeth;
    }

    public String getExemptCurr() {
        return exemptCurr;
    }

    public void setExemptCurr(String exemptCurr) {
        this.exemptCurr = exemptCurr;
    }

    public String getAddiCurr() {
        return addiCurr;
    }

    public void setAddiCurr(String addiCurr) {
        this.addiCurr = addiCurr;
    }

    public Object getSTDCode() {
        return sTDCode;
    }

    public void setSTDCode(Object sTDCode) {
        this.sTDCode = sTDCode;
    }

    public Object getFatherName() {
        return fatherName;
    }

    public void setFatherName(Object fatherName) {
        this.fatherName = fatherName;
    }

    public Object getCPF() {
        return cPF;
    }

    public void setCPF(Object cPF) {
        this.cPF = cPF;
    }

    public Object getCRC() {
        return cRC;
    }

    public void setCRC(Object cRC) {
        this.cRC = cRC;
    }

    public String getContResp() {
        return contResp;
    }

    public void setContResp(String contResp) {
        this.contResp = contResp;
    }

    public String getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(String repLegal) {
        this.repLegal = repLegal;
    }

    public String getDirfDeclar() {
        return dirfDeclar;
    }

    public void setDirfDeclar(String dirfDeclar) {
        this.dirfDeclar = dirfDeclar;
    }

    public Object getUFCRC() {
        return uFCRC;
    }

    public void setUFCRC(Object uFCRC) {
        this.uFCRC = uFCRC;
    }

    public Object getIDType() {
        return iDType;
    }

    public void setIDType(Object iDType) {
        this.iDType = iDType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Object getBPLId() {
        return bPLId;
    }

    public void setBPLId(Object bPLId) {
        this.bPLId = bPLId;
    }

    public Object getManualNUM() {
        return manualNUM;
    }

    public void setManualNUM(Object manualNUM) {
        this.manualNUM = manualNUM;
    }

    public Object getPassIssue() {
        return passIssue;
    }

    public void setPassIssue(Object passIssue) {
        this.passIssue = passIssue;
    }

    public Object getPassIssuer() {
        return passIssuer;
    }

    public void setPassIssuer(Object passIssuer) {
        this.passIssuer = passIssuer;
    }

    public String getQualCode() {
        return qualCode;
    }

    public void setQualCode(String qualCode) {
        this.qualCode = qualCode;
    }

    public String getPRWebAccss() {
        return pRWebAccss;
    }

    public void setPRWebAccss(String pRWebAccss) {
        this.pRWebAccss = pRWebAccss;
    }

    public String getPrePRWeb() {
        return prePRWeb;
    }

    public void setPrePRWeb(String prePRWeb) {
        this.prePRWeb = prePRWeb;
    }

    public String getUSTEMUsername() {
        return U_STEM_Username;
    }

    public void setUSTEMUsername(String uSTEMUsername) {
        this.U_STEM_Username = uSTEMUsername;
    }

    public String getUSTEMPassword() {
        return U_STEM_Password;
    }

    public void setUSTEMPassword(String uSTEMPassword) {
        this.U_STEM_Password = uSTEMPassword;
    }

    public Object getUSTEMMobileId() {
        return uSTEMMobileId;
    }

    public void setUSTEMMobileId(Object uSTEMMobileId) {
        this.uSTEMMobileId = uSTEMMobileId;
    }

    public Object getUSTEMGcmId() {
        return uSTEMGcmId;
    }

    public void setUSTEMGcmId(Object uSTEMGcmId) {
        this.uSTEMGcmId = uSTEMGcmId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(empID);
        dest.writeValue(lastName);
        dest.writeValue(firstName);
        dest.writeValue(middleName);
        dest.writeValue(sex);
        dest.writeValue(jobTitle);
        dest.writeValue(type);
        dest.writeValue(dept);
        dest.writeValue(branch);
        dest.writeValue(workStreet);
        dest.writeValue(workBlock);
        dest.writeValue(workZip);
        dest.writeValue(workCity);
        dest.writeValue(workCounty);
        dest.writeValue(workCountr);
        dest.writeValue(workState);
        dest.writeValue(manager);
        dest.writeValue(userId);
        dest.writeValue(salesPrson);
        dest.writeValue(officeTel);
        dest.writeValue(officeExt);
        dest.writeValue(mobile);
        dest.writeValue(pager);
        dest.writeValue(homeTel);
        dest.writeValue(fax);
        dest.writeValue(email);
        dest.writeValue(startDate);
        dest.writeValue(status);
        dest.writeValue(salary);
        dest.writeValue(salaryUnit);
        dest.writeValue(emplCost);
        dest.writeValue(empCostUnt);
        dest.writeValue(termDate);
        dest.writeValue(termReason);
        dest.writeValue(bankCode);
        dest.writeValue(bankBranch);
        dest.writeValue(bankBranNo);
        dest.writeValue(bankAcount);
        dest.writeValue(homeStreet);
        dest.writeValue(homeBlock);
        dest.writeValue(homeZip);
        dest.writeValue(homeCity);
        dest.writeValue(homeCounty);
        dest.writeValue(homeCountr);
        dest.writeValue(homeState);
        dest.writeValue(birthDate);
        dest.writeValue(brthCountr);
        dest.writeValue(martStatus);
        dest.writeValue(nChildren);
        dest.writeValue(govID);
        dest.writeValue(citizenshp);
        dest.writeValue(passportNo);
        dest.writeValue(passportEx);
        dest.writeValue(picture);
        dest.writeValue(remark);
        dest.writeValue(attachment);
        dest.writeValue(salaryCurr);
        dest.writeValue(empCostCur);
        dest.writeValue(workBuild);
        dest.writeValue(homeBuild);
        dest.writeValue(position);
        dest.writeValue(atcEntry);
        dest.writeValue(addrTypeW);
        dest.writeValue(addrTypeH);
        dest.writeValue(streetNoW);
        dest.writeValue(streetNoH);
        dest.writeValue(dispMidNam);
        dest.writeValue(namePos);
        dest.writeValue(dispComma);
        dest.writeValue(costCenter);
        dest.writeValue(companyNum);
        dest.writeValue(vacPreYear);
        dest.writeValue(vacCurYear);
        dest.writeValue(munKey);
        dest.writeValue(taxClass);
        dest.writeValue(inTaxLiabi);
        dest.writeValue(emTaxCCode);
        dest.writeValue(relPartner);
        dest.writeValue(exemptAmnt);
        dest.writeValue(exemptUnit);
        dest.writeValue(addiAmnt);
        dest.writeValue(addiUnit);
        dest.writeValue(taxOName);
        dest.writeValue(taxONum);
        dest.writeValue(heaInsName);
        dest.writeValue(heaInsCode);
        dest.writeValue(heaInsType);
        dest.writeValue(sInsurNum);
        dest.writeValue(statusOfP);
        dest.writeValue(statusOfE);
        dest.writeValue(bCodeDateV);
        dest.writeValue(devBAOwner);
        dest.writeValue(fNameSP);
        dest.writeValue(surnameSP);
        dest.writeValue(logInstanc);
        dest.writeValue(userSign);
        dest.writeValue(userSign2);
        dest.writeValue(updateDate);
        dest.writeValue(persGroup);
        dest.writeValue(jTCode);
        dest.writeValue(extEmpNo);
        dest.writeValue(birthPlace);
        dest.writeValue(pymMeth);
        dest.writeValue(exemptCurr);
        dest.writeValue(addiCurr);
        dest.writeValue(sTDCode);
        dest.writeValue(fatherName);
        dest.writeValue(cPF);
        dest.writeValue(cRC);
        dest.writeValue(contResp);
        dest.writeValue(repLegal);
        dest.writeValue(dirfDeclar);
        dest.writeValue(uFCRC);
        dest.writeValue(iDType);
        dest.writeValue(active);
        dest.writeValue(bPLId);
        dest.writeValue(manualNUM);
        dest.writeValue(passIssue);
        dest.writeValue(passIssuer);
        dest.writeValue(qualCode);
        dest.writeValue(pRWebAccss);
        dest.writeValue(prePRWeb);
        dest.writeValue(U_STEM_Username);
        dest.writeValue(U_STEM_Password);
        dest.writeValue(uSTEMMobileId);
        dest.writeValue(uSTEMGcmId);
    }

    public int describeContents() {
        return 0;
    }

}
