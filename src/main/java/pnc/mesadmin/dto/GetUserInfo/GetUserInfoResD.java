package pnc.mesadmin.dto.GetUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户信息Data
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class GetUserInfoResD implements Serializable{

    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("RealName")
    private String RealName;

    @JsonProperty("ShiftInfo")
    private GetUserInfoResBDShift ShiftInfo;

    @JsonProperty("RoleInfo")
    private List<GetUserInfoResBDRole> RoleInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("MobileNo")
    private String MobileNo;


    @JsonProperty("EmailAddress")
    private String EmailAddress;


    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }

    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }

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
    public List<GetUserInfoResBDRole> getRoleInfo() {
        return RoleInfo;
    }

    @JsonIgnore
    public void setRoleInfo(List<GetUserInfoResBDRole> roleInfo) {
        RoleInfo = roleInfo;
    }

    @JsonIgnore
    public  String getCreator() {
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
    public GetUserInfoResBDShift getShiftInfo() {
        return ShiftInfo;
    }
    @JsonIgnore
    public void setShiftInfo(GetUserInfoResBDShift shiftInfo) {
        ShiftInfo = shiftInfo;
    }
}
