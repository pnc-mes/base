package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具信息实体类
 * 创建人：HAOZAN
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_toolinfo")
public class ToolInfo implements Serializable {
    private Integer Ruid;
    private String Guid;
    private String ToolName;
    private String SupplierGd;
    private String ToolFamilyGd;
    private String DevSMGd;
    //熊伟开始
    private String DevSGd;
    //熊伟结束

    private Integer UsrNum;
    private String VenderSN;
    private String FaGd;
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
        this.Guid = guid;
    }

    public String getToolName() {
        return ToolName;
    }

    public void setToolName(String toolName) {
        ToolName = toolName;
    }

    public String getSupplierGd() {
        return SupplierGd;
    }

    public void setSupplierGd(String supplierGd) {
        SupplierGd = supplierGd;
    }

    public String getToolFamilyGd() {
        return ToolFamilyGd;
    }

    public void setToolFamilyGd(String toolFamilyGd) {
        ToolFamilyGd = toolFamilyGd;
    }

    public String getDevSMGd() {
        return DevSMGd;
    }

    public void setDevSMGd(String devSMGd) {
        DevSMGd = devSMGd;
    }

    public Integer getUsrNum() {
        return UsrNum;
    }

    public void setUsrNum(Integer usrNum) {
        UsrNum = usrNum;
    }

    public String getVenderSN() {
        return VenderSN;
    }

    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }

    public String getFaGd() {
        return FaGd;
    }

    public void setFaGd(String faGd) {
        FaGd = faGd;
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

    public String getDevSGd() {
        return DevSGd;
    }

    public void setDevSGd(String devSGd) {
        DevSGd = devSGd;
    }
}
