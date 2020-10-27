package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:26
 * @Description:
 */
public class DevMalfInfo {
    private int ruid;
    private String guid;
    private String devMalfCode;
    private String devMalfName;
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

    public String getDevMalfCode() {
        return devMalfCode;
    }

    public void setDevMalfCode(String devMalfCode) {
        this.devMalfCode = devMalfCode;
    }

    public String getDevMalfName() {
        return devMalfName;
    }

    public void setDevMalfName(String devMalfName) {
        this.devMalfName = devMalfName;
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
