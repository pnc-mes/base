package pnc.mesadmin.dto.SaveUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新增用户信息
 * 创建人：张亮亮
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveUserInfoReqBD00Role implements Serializable {

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
