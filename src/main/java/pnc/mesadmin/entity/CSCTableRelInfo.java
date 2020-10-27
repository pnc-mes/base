package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 通用查询配置与表格设置关系信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public class CSCTableRelInfo {
    private Integer Ruid;
    private String CSConfigGd;
    private String CSTableGd;
    private String Creator;
    private Date CreateTime;

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getCSConfigGd() {
        return CSConfigGd;
    }

    public void setCSConfigGd(String CSConfigGd) {
        this.CSConfigGd = CSConfigGd;
    }

    public String getCSTableGd() {
        return CSTableGd;
    }

    public void setCSTableGd(String CSTableGd) {
        this.CSTableGd = CSTableGd;
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
}
