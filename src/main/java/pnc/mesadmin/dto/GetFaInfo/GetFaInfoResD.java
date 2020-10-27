package pnc.mesadmin.dto.GetFaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工厂信息DTO返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetFaInfoResD implements Serializable{

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("FaName")
    private String FaName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("CpRd")
    private int CpRd;

    @JsonProperty("CpName")
    private String CpName;

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
    public int getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }

    @JsonIgnore
    public String getFaName() {
        return FaName;
    }

    @JsonIgnore
    public void setFaName(String faName) {
        FaName = faName;
    }

    @JsonIgnore
    public String getContactor() {
        return Contactor;
    }

    @JsonIgnore
    public void setContactor(String contactor) {
        Contactor = contactor;
    }

    @JsonIgnore
    public String getAddress() {
        return Address;
    }

    @JsonIgnore
    public void setAddress(String address) {
        Address = address;
    }

    @JsonIgnore
    public int getCpRd() {
        return CpRd;
    }

    @JsonIgnore
    public void setCpRd(int cpRd) {
        CpRd = cpRd;
    }

    @JsonIgnore
    public String getCpName() {
        return CpName;
    }

    @JsonIgnore
    public void setCpName(String cpName) {
        CpName = cpName;
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
