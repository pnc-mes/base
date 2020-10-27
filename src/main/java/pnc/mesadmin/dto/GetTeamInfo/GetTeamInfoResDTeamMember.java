package pnc.mesadmin.dto.GetTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:32
 * @Description:
 */
public class GetTeamInfoResDTeamMember {
    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("RealName")
    private String RealName;

    @JsonProperty("MobileNo")
    private String MobileNo;

    @JsonProperty("EmailAddress")
    private String EmailAddress;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }
    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
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
}
