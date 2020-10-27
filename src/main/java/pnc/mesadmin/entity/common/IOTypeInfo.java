package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/16 18:51
 * @Description:
 */
public class IOTypeInfo {
    private int Ruid;
    private String Guid;
    private String IOTypeName;
    private String IOType;
    private String Remark;
    private Date CreateTime;
    private Date LastModifyTime;

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

    public String getIOTypeName() {
        return IOTypeName;
    }

    public void setIOTypeName(String IOTypeName) {
        this.IOTypeName = IOTypeName;
    }

    public String getIOType() {
        return IOType;
    }

    public void setIOType(String IOType) {
        this.IOType = IOType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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
}
