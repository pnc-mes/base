package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:序号 绑定 字段 信息表
 * 创建人：郝赞
 * 创建时间：2018-8-27
 * 修改人：
 * 修改时间：
 */
public class SerialRuleTBFInfo {
        private int Ruid;
        private String Guid;
        private String SerialGd;
        private String FieldCode;
        private String FieldName;
        private String Creator;
        private Date CreateTime;
        private String LastModifyMan;
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

    public String getSerialGd() {
        return SerialGd;
    }

    public void setSerialGd(String serialGd) {
        SerialGd = serialGd;
    }

    public String getFieldCode() {
        return FieldCode;
    }

    public void setFieldCode(String fieldCode) {
        FieldCode = fieldCode;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
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
