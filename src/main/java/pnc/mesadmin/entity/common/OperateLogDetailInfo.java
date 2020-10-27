package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @ProjectName: mesadmin
 * @Package: pnc.module.entity
 * @ClassName: OperateLogInfo
 * @Author: zhangliangliang
 * @Description:
 * @Date: 2019/6/19 8:41
 * @Version: 1.0
 */
public class OperateLogDetailInfo {
    private Integer ruid;
    private String guid;
    private String operateLogGd;
    private String batch;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;


    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOperateLogGd() {
        return operateLogGd;
    }

    public void setOperateLogGd(String operateLogGd) {
        this.operateLogGd = operateLogGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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
