package pnc.mesadmin.dto.SaveUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新增用户信息
 * 创建人：张亮亮
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：2017-06-08
 */
public class SaveUserInfoReqBD00 implements Serializable {

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("RealName")
    private String RealName;
    @JsonProperty("MobileNo")
    private String MobileNo;

    @JsonProperty("EmailAddress")
    private String EmailAddress;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("Password")
    private String Password;
    @JsonProperty("ShiftRd")
    private int ShiftRd;

    @JsonIgnore
    public String getPassword() {
        return Password;
    }
    @JsonIgnore
    public void setPassword(String password) {
        Password = password;
    }

    @JsonProperty("RoleInfo")
    private List<SaveUserInfoReqBD00Role> RoleInfo;

    @JsonIgnore
    public String getUserName() {
        return UserName;
    }

    @JsonIgnore
    public void setUserName(String userName) {
        UserName = userName;
    }

    @JsonIgnore
    public String getRealName() {
        return RealName;
    }

    @JsonIgnore
    public void setRealName(String realName) {
        RealName = realName;
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
    public List<SaveUserInfoReqBD00Role> getRoleInfo() {
        return RoleInfo;
    }

    @JsonIgnore
    public void setRoleInfo(List<SaveUserInfoReqBD00Role> roleInfo) {
        RoleInfo = roleInfo;
    }
    @JsonIgnore
    public String getMobileNo() {
        return MobileNo;
    }
    @JsonIgnore
    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
    @JsonIgnore
    public String getEmailAddress() {
        return EmailAddress;
    }
    @JsonIgnore
    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }
    @JsonIgnore
    public int getShiftRd() {
        return ShiftRd;
    }
    @JsonIgnore
    public void setShiftRd(int shiftRd) {
        ShiftRd = shiftRd;
    }
}
