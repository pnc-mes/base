package pnc.mesadmin.dto.newmove.GetAllRVInfoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllRVInfoResB implements Serializable {

    @JsonProperty("RVID")
    private Integer RVID;
    @JsonProperty("WoRd")
    private Integer WoRd;
    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaterialAttr")
    private String MaterialAttr;
    @JsonProperty("LineName")
    private String LineName;
    @JsonProperty("ShiftName")
    private String ShiftName;
    @JsonProperty("PELGradeName")
    private String PELGradeName;
    @JsonProperty("TELGradeName")
    private String TELGradeName;
    @JsonProperty("YHGradeName")
    private String YHGradeName;
    @JsonProperty("ZJGradeName")
    private String ZJGradeName;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("PELBadName")
    private String PELBadName;

    @JsonProperty("PELResult")
    private String PELResult;

    @JsonProperty("TELBadName")
    private String TELBadName;

    @JsonProperty("TELResult")
    private String TELResult;

    @JsonProperty("YHBadName")
    private String YHBadName;

    @JsonProperty("YHLocationCode")
    private String YHLocationCode;

    @JsonProperty("ZJBadName")
    private String ZJBadName;

    @JsonProperty("ZJLocationCode")
    private String ZJLocationCode;

    @JsonIgnore
    public Integer getRVID() {
        return RVID;
    }

    @JsonIgnore
    public void setRVID(Integer RVID) {
        this.RVID = RVID;
    }

    @JsonIgnore
    public Integer getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(Integer woRd) {
        WoRd = woRd;
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
    public String getMaterialAttr() {
        return MaterialAttr;
    }

    @JsonIgnore
    public void setMaterialAttr(String materialAttr) {
        MaterialAttr = materialAttr;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }

    @JsonIgnore
    public String getShiftName() {
        return ShiftName;
    }

    @JsonIgnore
    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    @JsonIgnore
    public String getPELGradeName() {
        return PELGradeName;
    }

    @JsonIgnore
    public void setPELGradeName(String PELGradeName) {
        this.PELGradeName = PELGradeName;
    }

    @JsonIgnore
    public String getTELGradeName() {
        return TELGradeName;
    }

    @JsonIgnore
    public void setTELGradeName(String TELGradeName) {
        this.TELGradeName = TELGradeName;
    }

    @JsonIgnore
    public String getYHGradeName() {
        return YHGradeName;
    }

    @JsonIgnore
    public void setYHGradeName(String YHGradeName) {
        this.YHGradeName = YHGradeName;
    }

    @JsonIgnore
    public String getZJGradeName() {
        return ZJGradeName;
    }

    @JsonIgnore
    public void setZJGradeName(String ZJGradeName) {
        this.ZJGradeName = ZJGradeName;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public String getPELBadName() {
        return PELBadName;
    }

    @JsonIgnore
    public void setPELBadName(String PELBadName) {
        this.PELBadName = PELBadName;
    }

    @JsonIgnore
    public String getPELResult() {
        return PELResult;
    }

    @JsonIgnore
    public void setPELResult(String PELResult) {
        this.PELResult = PELResult;
    }

    @JsonIgnore
    public String getTELBadName() {
        return TELBadName;
    }

    @JsonIgnore
    public void setTELBadName(String TELBadName) {
        this.TELBadName = TELBadName;
    }

    @JsonIgnore
    public String getTELResult() {
        return TELResult;
    }

    @JsonIgnore
    public void setTELResult(String TELResult) {
        this.TELResult = TELResult;
    }

    @JsonIgnore
    public String getYHBadName() {
        return YHBadName;
    }

    @JsonIgnore
    public void setYHBadName(String YHBadName) {
        this.YHBadName = YHBadName;
    }

    @JsonIgnore
    public String getYHLocationCode() {
        return YHLocationCode;
    }

    @JsonIgnore
    public void setYHLocationCode(String YHLocationCode) {
        this.YHLocationCode = YHLocationCode;
    }

    @JsonIgnore
    public String getZJBadName() {
        return ZJBadName;
    }

    @JsonIgnore
    public void setZJBadName(String ZJBadName) {
        this.ZJBadName = ZJBadName;
    }

    @JsonIgnore
    public String getZJLocationCode() {
        return ZJLocationCode;
    }

    @JsonIgnore
    public void setZJLocationCode(String ZJLocationCode) {
        this.ZJLocationCode = ZJLocationCode;
    }
}
