package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD01 implements Serializable{

    @JsonProperty("RoleRd")
    private int roleRd;

    @JsonIgnore
    public int getRoleRd() {
        return roleRd;
    }

    @JsonIgnore
    public void setRoleRd(int roleRd) {
        this.roleRd = roleRd;
    }
}
