package pnc.mesadmin.dto.SaveCpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：编辑企业信息DTO
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class SaveCpInfoReqBD02 implements Serializable {

    @JsonProperty("CpRd")
    private int CpRd;

    @JsonProperty("CpName")
    private String CpName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Remark")
    private String Remark;

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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
