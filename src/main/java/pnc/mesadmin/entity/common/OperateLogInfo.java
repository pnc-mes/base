package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @ProjectName: mesadmin
 * @Package: pnc.module.entity
 * @ClassName: OperateLogInfo
 * @Author: zhangliangliang
 * @Description: mes 拆箱 装箱 冻结 解冻 的日志记录
 * @Date: 2019/6/19 8:41
 * @Version: 1.0
 */
public class OperateLogInfo {
    private Integer ruid;
    private String guid;
    private String operateType;//00 成品的拆箱 01 装箱拆箱 02 冻结箱号 03 解冻箱号
    private String operateText; //原因
    private String barCode;//箱号
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

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateText() {
        return operateText;
    }

    public void setOperateText(String operateText) {
        this.operateText = operateText;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
