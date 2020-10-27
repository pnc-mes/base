package pnc.mesadmin.dto.GetAllRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetAllRoleInfoResD implements Serializable{

    @JsonProperty("RoleRd")
    private int RoleRd;

    @JsonProperty("RoleName")
    private String RoleName;

    @JsonIgnore
    public int getRoleRd() {
        return RoleRd;
    }

    @JsonIgnore
    public void setRoleRd(int roleRd) {
        RoleRd = roleRd;
    }

    @JsonIgnore
    public String getRoleName() {
        return RoleName;
    }

    @JsonIgnore
    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
