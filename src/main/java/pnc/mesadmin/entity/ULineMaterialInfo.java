package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 名称 产线执行物料信息
 * 创建时间：2019-10-12
 * 修改人：yin.yang
 * 修改时间：
 */
@TableName(value = "tpm_ULineMaterialInfo")
public class ULineMaterialInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private Integer ruid;
    private String guid;
    private String woCode; //工单号
    private String specGuid; //工序标识
    private String specName;//工序名称
    private BigDecimal warningVaule; //物料预计值
    private String creator; //创建人
    private Date createTime;// 创建时间
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;// 备注

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

    public String getWoCode() {
        return woCode;
    }

    public void setWoCode(String woCode) {
        this.woCode = woCode;
    }

    public String getSpecGuid() {
        return specGuid;
    }

    public void setSpecGuid(String specGuid) {
        this.specGuid = specGuid;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public BigDecimal getWarningVaule() {
        return warningVaule;
    }

    public void setWarningVaule(BigDecimal warningVaule) {
        this.warningVaule = warningVaule;
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
