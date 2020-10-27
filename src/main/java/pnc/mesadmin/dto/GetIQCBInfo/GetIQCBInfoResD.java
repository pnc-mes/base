package pnc.mesadmin.dto.GetIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/6/10.
 */
public class GetIQCBInfoResD {
    @JsonProperty("IQCRd")
    private int IQCRd;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("CkResult")
    private String CkResult;
    @JsonProperty("BadInfo")
    private List<GetIQCBInfoResDBad> BadInfo;
    @JsonProperty("CentInfo")
    private List<GetIQCBInfoResDCent> CentInfo;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("LastModifyTime")
    private String LastModifyTime;
    @JsonProperty("Remark")
    private String Remark;


    @JsonIgnore
    public int getIQCRd() {
        return IQCRd;
    }
    @JsonIgnore
    public void setIQCRd(int IQCRd) {
        this.IQCRd = IQCRd;
    }
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }
    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }
    @JsonIgnore
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }
    @JsonIgnore
    public float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
    @JsonIgnore
    public String getCkResult() {
        return CkResult;
    }
    @JsonIgnore
    public void setCkResult(String ckResult) {
        CkResult = ckResult;
    }
    @JsonIgnore
    public List<GetIQCBInfoResDBad> getBadInfo() {
        return BadInfo;
    }
    @JsonIgnore
    public void setBadInfo(List<GetIQCBInfoResDBad> badInfo) {
        BadInfo = badInfo;
    }
    @JsonIgnore
    public List<GetIQCBInfoResDCent> getCentInfo() {
        return CentInfo;
    }
    @JsonIgnore
    public void setCentInfo(List<GetIQCBInfoResDCent> centInfo) {
        CentInfo = centInfo;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
