package pnc.mesadmin.dto.GetAllStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/3/9.
 */
public class GetAllStoreInfoReqBD00 implements Serializable {
    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("RoleRd")
    private String RoleRd;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }

    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }

    @JsonIgnore
    public String getRoleRd() {
        return RoleRd;
    }

    @JsonIgnore
    public void setRoleRd(String roleRd) {
        RoleRd = roleRd;
    }
}
