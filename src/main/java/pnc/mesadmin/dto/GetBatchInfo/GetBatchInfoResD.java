package pnc.mesadmin.dto.GetBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetBatchInfoResD {
    @JsonProperty("BRd")
    private int BRd;
    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("WFName")
    private String WFName;
    @JsonProperty("Spec")
    private GetBatchInfoResDSpec Spec;
    @JsonProperty("PrintTInfo")
    private GetBatchInfoResDPrintTInfo PrintTInfo;
    @JsonProperty("PrintTName")
    private String PrintTName;
    @JsonProperty("PrintInfo")
    private GetBatchInfoResDPrintInfo PrintInfo;
    @JsonProperty("BCount")
    private float BCount;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("JStartDate")
    private String JStartDate;
    @JsonProperty("JFinishDate")
    private String JFinishDate;
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
    public int getBRd() {
        return BRd;
    }
    @JsonIgnore
    public void setBRd(int BRd) {
        this.BRd = BRd;
    }
    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }
    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
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
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }
    @JsonIgnore
    public String getWFName() {
        return WFName;
    }
    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
    @JsonIgnore
    public GetBatchInfoResDSpec getSpec() {
        return Spec;
    }
    @JsonIgnore
    public void setSpec(GetBatchInfoResDSpec spec) {
        Spec = spec;
    }
    @JsonIgnore
    public GetBatchInfoResDPrintTInfo getPrintTInfo() {
        return PrintTInfo;
    }
    @JsonIgnore
    public void setPrintTInfo(GetBatchInfoResDPrintTInfo printTInfo) {
        PrintTInfo = printTInfo;
    }
    @JsonIgnore
    public String getPrintTName() {
        return PrintTName;
    }
    @JsonIgnore
    public void setPrintTName(String printTName) {
        PrintTName = printTName;
    }
    @JsonIgnore
    public GetBatchInfoResDPrintInfo getPrintInfo() {
        return PrintInfo;
    }
    @JsonIgnore
    public void setPrintInfo(GetBatchInfoResDPrintInfo printInfo) {
        PrintInfo = printInfo;
    }
    @JsonIgnore
    public float getBCount() {
        return BCount;
    }
    @JsonIgnore
    public void setBCount(float BCount) {
        this.BCount = BCount;
    }
    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
    @JsonIgnore
    public String getJStartDate() {
        return JStartDate;
    }
    @JsonIgnore
    public void setJStartDate(String JStartDate) {
        this.JStartDate = JStartDate;
    }
    @JsonIgnore
    public String getJFinishDate() {
        return JFinishDate;
    }
    @JsonIgnore
    public void setJFinishDate(String JFinishDate) {
        this.JFinishDate = JFinishDate;
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
