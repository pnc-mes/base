package pnc.mesadmin.entity;


import java.util.Date;

/**
 * Created by PNC on 2017/6/1.
 */
public class GetOpertInfo {
    private int OpertRd;
    private String OptName;

    private int WCRd;

    private String WCName;

    private int BLRd;

    private String BLName;

    private int DCVerRd;

    private String DCName;

    private String SpecOption;

    private String PackOpt;

    private String CkWeight;

    private String PackType;

    private String Creator;

    private Date CreateTime;

    private String LastModifyMan;

    private Date LastModifyTime;

    private String Remark;
    public int getOpertRd() {
        return OpertRd;
    }

    public void setOpertRd(int opertRd) {
        OpertRd = opertRd;
    }

    public String getOptName() {
        return OptName;
    }

    public void setOptName(String optName) {
        OptName = optName;
    }

    public int getWCRd() {
        return WCRd;
    }

    public void setWCRd(int WCRd) {
        this.WCRd = WCRd;
    }

    public String getWCName() {
        return WCName;
    }

    public void setWCName(String WCName) {
        this.WCName = WCName;
    }

    public int getBLRd() {
        return BLRd;
    }

    public void setBLRd(int BLRd) {
        this.BLRd = BLRd;
    }

    public String getBLName() {
        return BLName;
    }

    public void setBLName(String BLName) {
        this.BLName = BLName;
    }

    public int getDCVerRd() {
        return DCVerRd;
    }

    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    public String getDCName() {
        return DCName;
    }

    public void setDCName(String DCName) {
        this.DCName = DCName;
    }

    public String getSpecOption() {
        return SpecOption;
    }

    public void setSpecOption(String specOption) {
        SpecOption = specOption;
    }

    public String getPackOpt() {
        return PackOpt;
    }

    public void setPackOpt(String packOpt) {
        PackOpt = packOpt;
    }

    public String getCkWeight() {
        return CkWeight;
    }

    public void setCkWeight(String ckWeight) {
        CkWeight = ckWeight;
    }

    public String getPackType() {
        return PackType;
    }

    public void setPackType(String packType) {
        PackType = packType;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }


}
