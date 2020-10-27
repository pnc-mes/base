package pnc.mesadmin.dto.GetCusInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class GetCusInfoResD implements Serializable{

    @JsonProperty("CusRd")
    private int CusRd;

    @JsonProperty("CusName")
    private String CusName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Mobile")
    private String Mobile;

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
    public int getCusRd() {
        return CusRd;
    }

    @JsonIgnore
    public void setCusRd(int cusRd) {
        CusRd = cusRd;
    }

    @JsonIgnore
    public String getCusName() {
        return CusName;
    }

    @JsonIgnore
    public void setCusName(String cusName) {
        CusName = cusName;
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
