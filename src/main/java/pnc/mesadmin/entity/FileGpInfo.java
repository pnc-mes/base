package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息Model
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_filegpinfo")
public class FileGpInfo {
    private int ruid;
    private String guid;
    private String fileGpName;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFileGpName() {
        return fileGpName;
    }

    public void setFileGpName(String fileGpName) {
        this.fileGpName = fileGpName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
