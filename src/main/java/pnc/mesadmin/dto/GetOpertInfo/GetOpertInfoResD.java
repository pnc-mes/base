package pnc.mesadmin.dto.GetOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业信息DTO返回业务数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetOpertInfoResD implements Serializable{
    @JsonProperty("ReaCGRd")
    private int ReaCGRd;
    @JsonProperty("ReaCGName")
    private String ReaCGName;
    @JsonProperty("OpertRd")
    private int OpertRd;
    @JsonProperty("OptName")
    private String OptName;
    @JsonProperty("WCRd")
    private int WCRd;
    @JsonProperty("WCName")
    private String WCName;
    @JsonProperty("BLRd")
    private int BLRd;
    @JsonProperty("BLName")
    private String BLName;
    @JsonProperty("DCName")
    private String DCName;
    @JsonProperty("SpecOption")
    private String SpecOption;
    @JsonProperty("PackInfo")
    private GetOpertInfoResDPack PackInfo;
    @JsonProperty("BadOutSpec")
    private String BadOutSpec;
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
    public int getOpertRd() {
        return OpertRd;
    }
    @JsonIgnore
    public void setOpertRd(int opertRd) {
        OpertRd = opertRd;
    }
    @JsonIgnore
    public String getOptName() {
        return OptName;
    }
    @JsonIgnore
    public void setOptName(String optName) {
        OptName = optName;
    }
    @JsonIgnore
    public int getWCRd() {
        return WCRd;
    }
    @JsonIgnore
    public void setWCRd(int WCRd) {
        this.WCRd = WCRd;
    }
    @JsonIgnore
    public String getWCName() {
        return WCName;
    }
    @JsonIgnore
    public void setWCName(String WCName) {
        this.WCName = WCName;
    }
    @JsonIgnore
    public int getBLRd() {
        return BLRd;
    }
    @JsonIgnore
    public void setBLRd(int BLRd) {
        this.BLRd = BLRd;
    }
    @JsonIgnore
    public String getBLName() {
        return BLName;
    }
    @JsonIgnore
    public void setBLName(String BLName) {
        this.BLName = BLName;
    }
    @JsonIgnore
    public String getDCName() {
        return DCName;
    }
    @JsonIgnore
    public void setDCName(String DCName) {
        this.DCName = DCName;
    }
    @JsonIgnore
    public String getSpecOption() {
        return SpecOption;
    }
    @JsonIgnore
    public void setSpecOption(String specOption) {
        SpecOption = specOption;
    }
    @JsonIgnore
    public GetOpertInfoResDPack getPackInfo() {
        return PackInfo;
    }
    @JsonIgnore
    public void setPackInfo(GetOpertInfoResDPack packInfo) {
        PackInfo = packInfo;
    }
    @JsonIgnore
    public String getBadOutSpec() {
        return BadOutSpec;
    }
    @JsonIgnore
    public void setBadOutSpec(String badOutSpec) {
        BadOutSpec = badOutSpec;
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
    @JsonIgnore
    public int getReaCGRd() {
        return ReaCGRd;
    }
    @JsonIgnore
    public void setReaCGRd(int reaCGRd) {
        ReaCGRd = reaCGRd;
    }
    @JsonIgnore
    public String getReaCGName() {
        return ReaCGName;
    }
    @JsonIgnore
    public void setReaCGName(String reaCGName) {
        ReaCGName = reaCGName;
    }
}
