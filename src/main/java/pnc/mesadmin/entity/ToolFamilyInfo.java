package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：状态信息实体类
 * 创建人：HAOZAN
 * 创建时间：2018-6-13
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_toolfamilyinfo")
public class ToolFamilyInfo implements Serializable {
    private Integer Ruid;
    private String Guid;
    private String ToolFamilyName;
    private String Description;
    private String FaGd;
    private String DevSMGd;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getToolFamilyName() {
        return ToolFamilyName;
    }

    public void setToolFamilyName(String toolFamilyName) {
        ToolFamilyName = toolFamilyName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFaGd() {
        return FaGd;
    }

    public void setFaGd(String faGd) {
        FaGd = faGd;
    }

    public String getDevSMGd() {
        return DevSMGd;
    }

    public void setDevSMGd(String devSMGd) {
        DevSMGd = devSMGd;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
