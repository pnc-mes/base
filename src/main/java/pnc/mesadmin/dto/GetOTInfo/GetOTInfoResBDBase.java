package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪DTO返回业务数据实体类Body
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class GetOTInfoResBDBase implements Serializable{
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("LineName")
    private String LineName;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaterialType")
    private String MaterialType;
    @JsonProperty("CanNum")
    private float CanNum;
    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("WFName")
    private String WFName;
    @JsonProperty("BOMName")
    private String BOMName;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("IsPack")
    private String IsPack;
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
    public String getMaterialType() {
        return MaterialType;
    }
    @JsonIgnore
    public void setMaterialType(String materialType) {
        MaterialType = materialType;
    }
    @JsonIgnore
    public float getCanNum() {
        return CanNum;
    }
    @JsonIgnore
    public void setCanNum(float canNum) {
        CanNum = canNum;
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
    public String getWFName() {
        return WFName;
    }
    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
    @JsonIgnore
    public String getBOMName() {
        return BOMName;
    }
    @JsonIgnore
    public void setBOMName(String BOMName) {
        this.BOMName = BOMName;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public String getIsPack() {
        return IsPack;
    }
    @JsonIgnore
    public void setIsPack(String isPack) {
        IsPack = isPack;
    }
    @JsonIgnore
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }
}
