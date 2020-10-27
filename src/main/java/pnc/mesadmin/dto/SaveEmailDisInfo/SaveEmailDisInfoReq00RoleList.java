package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:15
 * @Description:
 */
public class SaveEmailDisInfoReq00RoleList {
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
