package pnc.mesadmin.dto.GetStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：仓库信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-15
 * 修改人：
 * 修改时间：
 */
public class GetStoreInfoResD implements Serializable {
    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("StoreCode")
    private String StoreCode;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("StoreType")
    private String StoreType;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("F1")
    private String F1;

    @JsonProperty("F2")
    private String F2;

    @JsonProperty("F3")
    private String F3;

    @JsonProperty("F4")
    private String F4;

    @JsonProperty("PMInfo")
    private List<GetStoreInfoResDPMInfo> PMInfo;

    @JsonProperty("Creator")
    private String Creator; //创建人

    @JsonProperty("CreateTime")
    private String CreateTime; //创建时间

    @JsonProperty("LastModifyMan")
    private String LastModifyMan; //最后修改人

    @JsonProperty("LastModifyTime")
    private String LastModifyTime; //最后修改时间

    @JsonProperty("Remark")
    private String Remark; //备注

    @JsonIgnore
    public String getStoreCode() {
        return StoreCode;
    }

    @JsonIgnore
    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
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
    public String getDSource() {
        return DSource;
    }

    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    @JsonIgnore
    public String getF1() {
        return F1;
    }

    @JsonIgnore
    public void setF1(String f1) {
        F1 = f1;
    }

    @JsonIgnore
    public String getF2() {
        return F2;
    }

    @JsonIgnore
    public void setF2(String f2) {
        F2 = f2;
    }

    @JsonIgnore
    public String getF3() {
        return F3;
    }

    @JsonIgnore
    public void setF3(String f3) {
        F3 = f3;
    }

    @JsonIgnore
    public String getF4() {
        return F4;
    }

    @JsonIgnore
    public void setF4(String f4) {
        F4 = f4;
    }

    @JsonIgnore
    public List<GetStoreInfoResDPMInfo> getPMInfo() {
        return PMInfo;
    }

    @JsonIgnore
    public void setPMInfo(List<GetStoreInfoResDPMInfo> PMInfo) {
        this.PMInfo = PMInfo;
    }

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    @JsonIgnore
    public String getStoreType() {
        return StoreType;
    }

    @JsonIgnore
    public void setStoreType(String storeType) {
        StoreType = storeType;
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
