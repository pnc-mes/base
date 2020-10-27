package pnc.mesadmin.dto.SaveCusInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存客户信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class SaveCusInfoReqBD00 implements Serializable{

    @JsonProperty("CusName")
    private String CusName;

    @JsonProperty("Contactor")
    private String Contactor;

    @JsonProperty("Mobile")
    private String Mobile;

    @JsonProperty("Address")
    private String Address;

    @JsonProperty("Remark")
    private String Remark;

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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
