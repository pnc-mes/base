package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料包装规格信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class MaPackInfo {
    private int ruid;
    private String guid;
    private String maVerGd;
    private String packType;
    private String pTGd;
    private String tempName;
    private String serialRuleGd;
    private int num;
    private float weight;
    private float upLimit;
    private float downLimit;
    private String isDef;
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

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getpTGd() {
        return pTGd;
    }

    public void setpTGd(String pTGd) {
        this.pTGd = pTGd;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getSerialRuleGd() {
        return serialRuleGd;
    }

    public void setSerialRuleGd(String serialRuleGd) {
        this.serialRuleGd = serialRuleGd;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(float upLimit) {
        this.upLimit = upLimit;
    }

    public float getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(float downLimit) {
        this.downLimit = downLimit;
    }

    public String getIsDef() {
        return isDef;
    }

    public void setIsDef(String isDef) {
        this.isDef = isDef;
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
