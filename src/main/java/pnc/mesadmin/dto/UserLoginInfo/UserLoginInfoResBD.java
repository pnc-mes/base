package pnc.mesadmin.dto.UserLoginInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/27.
 */
public class UserLoginInfoResBD implements Serializable{

    @JsonProperty("UserRd")
    private Integer UserRd;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("RealName")
    private String RealName;

    @JsonProperty("ShiftInfo")
    private UserLoginInfoResDShift ShiftInfo;

    @JsonProperty("LineInfo")
    private UserLoginInfoResDLine LineInfo;

    @JsonIgnore
    public Integer getUserRd() {
        return UserRd;
    }
    @JsonIgnore
    public void setUserRd(Integer userRd) {
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
    public UserLoginInfoResDShift getShiftInfo() {
        return ShiftInfo;
    }
    @JsonIgnore
    public void setShiftInfo(UserLoginInfoResDShift shiftInfo) {
        ShiftInfo = shiftInfo;
    }
    @JsonIgnore
    public UserLoginInfoResDLine getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(UserLoginInfoResDLine lineInfo) {
        LineInfo = lineInfo;
    }
}
