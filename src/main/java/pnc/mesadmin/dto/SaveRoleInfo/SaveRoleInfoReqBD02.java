package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD02 implements Serializable{

    @JsonProperty("RoleRd")
    private int RoleRd;

    @JsonProperty("RoleName")
    private String RoleName;

    @JsonProperty("MenuInfo")
    private List<SaveRoleInfoReqBD02MenuInfo> MenuInfo;

    @JsonProperty("ScanPVInfo")
    private List<SaveRoleInfoReqBD02ScanPVInfo> ScanPVInfo;

    @JsonProperty("ExecPVInfo")
    private List<SaveRoleInfoReqBD00ScanPVInfo> ExecPVInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public int getRoleRd() {
        return RoleRd;
    }

    @JsonIgnore
    public void setRoleRd(int roleRd) {
        RoleRd = roleRd;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;

    }

    @JsonIgnore
    public String getRoleName() {
        return RoleName;
    }

    @JsonIgnore
    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD02MenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<SaveRoleInfoReqBD02MenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD02ScanPVInfo> getScanPVInfo() {
        return ScanPVInfo;
    }

    @JsonIgnore
    public void setScanPVInfo(List<SaveRoleInfoReqBD02ScanPVInfo> scanPVInfo) {
        ScanPVInfo = scanPVInfo;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD00ScanPVInfo> getExecPVInfo() {
        return ExecPVInfo;
    }

    @JsonIgnore
    public void setExecPVInfo(List<SaveRoleInfoReqBD00ScanPVInfo> execPVInfo) {
        ExecPVInfo = execPVInfo;
    }
}
