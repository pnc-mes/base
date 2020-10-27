package pnc.mesadmin.dto.SaveRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/18.
 */
public class SaveRoleInfoReqBD00 implements Serializable{

    @JsonProperty("RoleName")
    private String RoleName;

    @JsonProperty("MenuInfo")
    private List<SaveRoleInfoReqBD00MenuInfo> MenuInfo;

    @JsonProperty("ScanPVInfo")
    private List<SaveRoleInfoReqBD00ScanPVInfo> ScanPVInfo;

    @JsonProperty("ExecPVInfo")
    private List<SaveRoleInfoReqBD00ScanPVInfo> ExecPVInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getRemark() {
        return Remark;
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
    public List<SaveRoleInfoReqBD00MenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<SaveRoleInfoReqBD00MenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }

    @JsonIgnore
    public List<SaveRoleInfoReqBD00ScanPVInfo> getScanPVInfo() {
        return ScanPVInfo;
    }

    @JsonIgnore
    public void setScanPVInfo(List<SaveRoleInfoReqBD00ScanPVInfo> scanPVInfo) {
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
