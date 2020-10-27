package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 换货记录表
 * @author: yin.yang
 * @create: 2019-04-24
 **/
public class ReplaceLogInfo {
    private Integer Ruid;
    private String Guid;
    private String ReplaceTypeRemark;
    private String ReplaceCode;
    private String BarType;
    private String BarCode;
    private String ReplaceBarCode;
    private String Creator;
    private Date CreateTime;
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

    public String getReplaceTypeRemark() {
        return ReplaceTypeRemark;
    }

    public void setReplaceTypeRemark(String replaceTypeRemark) {
        ReplaceTypeRemark = replaceTypeRemark;
    }

    public String getReplaceCode() {
        return ReplaceCode;
    }

    public void setReplaceCode(String replaceCode) {
        ReplaceCode = replaceCode;
    }

    public String getBarType() {
        return BarType;
    }

    public void setBarType(String barType) {
        BarType = barType;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getReplaceBarCode() {
        return ReplaceBarCode;
    }

    public void setReplaceBarCode(String replaceBarCode) {
        ReplaceBarCode = replaceBarCode;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
