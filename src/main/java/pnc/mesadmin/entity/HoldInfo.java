package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：冻结日志表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-08-31
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_holdinfo")
public class HoldInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String batch;
    private String specVerGd;
    private String reaDes;
    private String creator;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getReaDes() {
        return reaDes;
    }

    public void setReaDes(String reaDes) {
        this.reaDes = reaDes;
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

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }
}
