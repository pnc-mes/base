package pnc.mesadmin.dto.GetAllUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户列表信息dto返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class GetAllUserInfoResD implements Serializable{

    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("RealName")
    private String RealName;

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
}
