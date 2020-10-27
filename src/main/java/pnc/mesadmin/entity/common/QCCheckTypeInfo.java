package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/16 18:52
 * @Description:
 */
public class QCCheckTypeInfo {
    private int Ruid;
    private String Guid;
    private String QCCheckName;
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

    public String getQCCheckName() {
        return QCCheckName;
    }

    public void setQCCheckName(String QCCheckName) {
        this.QCCheckName = QCCheckName;
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
