package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/16 18:47
 * @Description:
 */
public class BHFKTypeInfo {
    private int Ruid;
    private String Guid;
    private String BHFKName;
    private Date CreateTime;
    private Date LastModifyTime;
    private String Remark;

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getBHFKName() {
        return BHFKName;
    }

    public void setBHFKName(String BHFKName) {
        this.BHFKName = BHFKName;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
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
