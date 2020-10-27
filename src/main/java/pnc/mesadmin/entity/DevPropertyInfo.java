package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备特性信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2018-09-16
 * 修改人：
 * 修改时间：
 */
public class DevPropertyInfo {
    private int ruid;
    private String guid;
    private String devGd;
    private String F1;
    private String F2;
    private String F3;
    private String F4;
    private String F5;
    private String F6;
    private String F7;
    private String F8;
    private String F9;
    private String F10;
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

    public String getDevGd() {
        return devGd;
    }

    public void setDevGd(String devGd) {
        this.devGd = devGd;
    }

    public String getF1() {
        return F1;
    }

    public void setF1(String f1) {
        F1 = f1;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String f2) {
        F2 = f2;
    }

    public String getF3() {
        return F3;
    }

    public void setF3(String f3) {
        F3 = f3;
    }

    public String getF4() {
        return F4;
    }

    public void setF4(String f4) {
        F4 = f4;
    }

    public String getF5() {
        return F5;
    }

    public void setF5(String f5) {
        F5 = f5;
    }

    public String getF6() {
        return F6;
    }

    public void setF6(String f6) {
        F6 = f6;
    }

    public String getF7() {
        return F7;
    }

    public void setF7(String f7) {
        F7 = f7;
    }

    public String getF8() {
        return F8;
    }

    public void setF8(String f8) {
        F8 = f8;
    }

    public String getF9() {
        return F9;
    }

    public void setF9(String f9) {
        F9 = f9;
    }

    public String getF10() {
        return F10;
    }

    public void setF10(String f10) {
        F10 = f10;
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
