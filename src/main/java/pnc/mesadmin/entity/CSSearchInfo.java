package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 查询设置信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public class CSSearchInfo {
    private Integer Ruid;
    private String Guid;
    private Integer ColNum;
    private Integer RowNum;
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

    public Integer getColNum() {
        return ColNum;
    }

    public void setColNum(Integer colNum) {
        ColNum = colNum;
    }

    public Integer getRowNum() {
        return RowNum;
    }

    public void setRowNum(Integer rowNum) {
        RowNum = rowNum;
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
