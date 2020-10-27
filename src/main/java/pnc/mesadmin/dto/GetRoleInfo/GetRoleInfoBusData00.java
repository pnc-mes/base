package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import pnc.mesadmin.dto.PageInfo;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetRoleInfoBusData00 implements Serializable{

    @JsonProperty("RoleRd")
    private int RoleRd;

    @JsonIgnore
    public int getRoleRd() {
        return RoleRd;
    }

    @JsonIgnore
    public void setRoleRd(int roleRd) {
        RoleRd = roleRd;
    }
}
