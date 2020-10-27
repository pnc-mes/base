package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 13:52
 * @Description:
 */
public class OutMaInfo {
    private int Ruid;
    private String Guid;
    private String OutCode;
    private String PickerGd;
    private String Use;
    private String ExStatus;
    private String SStatus;
    private Date  PreOutDate;
    private String DSource; // 00 销售订单 01 自建 02 返工出库 03 客户验货出库
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getOutCode() {
        return OutCode;
    }

    public void setOutCode(String outCode) {
        OutCode = outCode;
    }

    public String getPickerGd() {
        return PickerGd;
    }

    public void setPickerGd(String pickerGd) {
        PickerGd = pickerGd;
    }

    public String getUse() {
        return Use;
    }

    public void setUse(String use) {
        Use = use;
    }

    public String getExStatus() {
        return ExStatus;
    }

    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }

    public String getSStatus() {
        return SStatus;
    }

    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }

    public Date getPreOutDate() {
        return PreOutDate;
    }

    public void setPreOutDate(Date preOutDate) {
        PreOutDate = preOutDate;
    }

    public String getDSource() {
        return DSource;
    }

    public void setDSource(String DSource) {
        this.DSource = DSource;
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
