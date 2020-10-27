package pnc.mesadmin.dto.GetCpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取企业信息DTO返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class GetCpInfoResD implements Serializable{

    @JsonProperty("CpRd")
    private int CpRd;

    @JsonProperty("CpCode")
    private String CpCode;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("CpName")
    private String CpName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Address")
    private String Address;

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
    public String getCpCode() {
        return CpCode;
    }

    @JsonIgnore
    public void setCpCode(String cpCode) {
        CpCode = cpCode;
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
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
