package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 脚本输入参数信息表
 * @author: yin.yang
 * @create: 2018-12-08
 **/
public class PrintScrptInInfo {
    private Integer Ruid;
    private String Guid;
    private String PrintTGd;
    private String FieldCode;
    private String FieldSource;
    private String Val;
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

    public String getPrintTGd() {
        return PrintTGd;
    }

    public void setPrintTGd(String printTGd) {
        PrintTGd = printTGd;
    }

    public String getFieldCode() {
        return FieldCode;
    }

    public void setFieldCode(String fieldCode) {
        FieldCode = fieldCode;
    }

    public String getFieldSource() {
        return FieldSource;
    }

    public void setFieldSource(String fieldSource) {
        FieldSource = fieldSource;
    }

    public String getVal() {
        return Val;
    }

    public void setVal(String val) {
        Val = val;
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
