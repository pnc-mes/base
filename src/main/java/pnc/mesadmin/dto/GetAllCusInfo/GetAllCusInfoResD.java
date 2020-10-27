package pnc.mesadmin.dto.GetAllCusInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息列表DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/5/26
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllCusInfoResD implements Serializable{

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
}
