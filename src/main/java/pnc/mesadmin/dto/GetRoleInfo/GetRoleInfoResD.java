package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetRoleInfoResD implements Serializable{

    @JsonProperty("RoleRd")
    private int RoleRd;

    @JsonProperty("RoleName")
    private String RoleName;

    @JsonProperty("MenuInfo")
    private List<GetRoleInfoResDMenuInfo> MenuInfo;

    @JsonProperty("ScanPVInfo")
    private List<GetRoleInfoResDScanPVInfo> ScanPVInfo;

    @JsonProperty("ExecPVInfo")
    private List<GetRoleInfoResDScanPVInfo> ExecPVInfo;


    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

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

    @JsonIgnore
    public List<GetRoleInfoResDMenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<GetRoleInfoResDMenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    @JsonIgnore
    public List<GetRoleInfoResDScanPVInfo> getScanPVInfo() {
        return ScanPVInfo;
    }

    @JsonIgnore
    public void setScanPVInfo(List<GetRoleInfoResDScanPVInfo> scanPVInfo) {
        ScanPVInfo = scanPVInfo;
    }

    @JsonIgnore
    public List<GetRoleInfoResDScanPVInfo> getExecPVInfo() {
        return ExecPVInfo;
    }

    @JsonIgnore
    public void setExecPVInfo(List<GetRoleInfoResDScanPVInfo> execPVInfo) {
        ExecPVInfo = execPVInfo;
    }
}
