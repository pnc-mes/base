package pnc.mesadmin.dto.GetSupInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetSupInfoResD implements Serializable{

    @JsonProperty("SupRd")
    private int SupRd;

    @JsonProperty("SupCode")
    private String SupCode;

    @JsonProperty("SupName")
    private String SupName;

    @JsonProperty("SupFName")
    private String SupFName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Mobile")
    private String Mobile;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("Status")
    private String Status;

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
    public int getSupRd() {
        return SupRd;
    }

    @JsonIgnore
    public String getSupCode() {
        return SupCode;
    }

    @JsonIgnore
    public void setSupCode(String supCode) {
        SupCode = supCode;
    }

    @JsonIgnore
    public String getSupFName() {
        return SupFName;
    }

    @JsonIgnore
    public void setSupFName(String supFName) {
        SupFName = supFName;
    }

    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
    }

    @JsonIgnore
    public String getSupName() {
        return SupName;
    }

    @JsonIgnore
    public void setSupName(String supName) {
        SupName = supName;
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
    public String getMobile() {
        return Mobile;
    }

    @JsonIgnore
    public void setMobile(String mobile) {
        Mobile = mobile;
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
