package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:14
 * @Description:
 */
public class SaveEmailDisInfoReq00UserList {
    @JsonProperty("UserRd")
    private int UserRd;
    @JsonProperty("UserName")
    private String UserName;
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
    public String getUserName() {
        return UserName;
    }
    @JsonIgnore
    public void setUserName(String userName) {
        UserName = userName;
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
