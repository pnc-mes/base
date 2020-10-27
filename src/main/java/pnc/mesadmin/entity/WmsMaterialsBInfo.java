package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：谷阳原材料批次信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2019-10-30
 * 修改人：
 * 修改时间：
 */
@TableName("wms_materialsbinfo")
public class WmsMaterialsBInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String woSource; //单号来源 00#采购单 01#来料通知单 02#杂收 03#电池片解析
    private String woGd; //单号标识
    private String gradeName; //等级
    private String batchNo; //批号
    private String LineNumber; //行号
    private String maVerGd; //物料版本标识
    private String batch; //批次
    private String supBatch; //包裹批次
    private BigDecimal num; //总量
    private BigDecimal canNum; //可用数量
    private BigDecimal conSuNum; //消耗量
    private BigDecimal differenceNum; //总共退料数量
    private String bSource; //批次来源 00#创建 01#拆分 02#合并 默认00
    private Date expireDate; //到期时间
    private String bad; //标记不良 00#不良 01#未设置 默认01
    private String badNum; //不良数量
    private String iQCStatus; //IQC质检状态 00#待检 01#免检 02#已检 默认00
    private String ckResult; //检验结果 00#合格 01#不合格 02#降级使用 03#未设置 默认03
    private String status; //批次状态 00#正常 01#上料冻结 02#采购退货冻结 03生产退料冻结 04#消耗完作废
    // 05 来料入库审核冻结 07#工单领料冻结 08#调拨出冻结 09#调拨入冻结
    private String inStockStatus; //在库状态 00#在库 01#离库 02#未设置 默认02
    private String afterCheckQC; // 出库前检验 00#合格 01#不合格
    private String beforeCheckQC; // 入库前检验 00#合格 01#不合格
    private String creator;
    private Date createTime;
    private String lastModifier;
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

    public String getWoSource() {
        return woSource;
    }

    public void setWoSource(String woSource) {
        this.woSource = woSource;
    }

    public String getWoGd() {
        return woGd;
    }

    public void setWoGd(String woGd) {
        this.woGd = woGd;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getLineNumber() {
        return LineNumber;
    }

    public void setLineNumber(String lineNumber) {
        LineNumber = lineNumber;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSupBatch() {
        return supBatch;
    }

    public void setSupBatch(String supBatch) {
        this.supBatch = supBatch;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getCanNum() {
        return canNum;
    }

    public void setCanNum(BigDecimal canNum) {
        this.canNum = canNum;
    }

    public BigDecimal getConSuNum() {
        return conSuNum;
    }

    public void setConSuNum(BigDecimal conSuNum) {
        this.conSuNum = conSuNum;
    }

    public BigDecimal getDifferenceNum() {
        return differenceNum;
    }

    public void setDifferenceNum(BigDecimal differenceNum) {
        this.differenceNum = differenceNum;
    }

    public String getbSource() {
        return bSource;
    }

    public void setbSource(String bSource) {
        this.bSource = bSource;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getBadNum() {
        return badNum;
    }

    public void setBadNum(String badNum) {
        this.badNum = badNum;
    }

    public String getiQCStatus() {
        return iQCStatus;
    }

    public void setiQCStatus(String iQCStatus) {
        this.iQCStatus = iQCStatus;
    }

    public String getCkResult() {
        return ckResult;
    }

    public void setCkResult(String ckResult) {
        this.ckResult = ckResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInStockStatus() {
        return inStockStatus;
    }

    public void setInStockStatus(String inStockStatus) {
        this.inStockStatus = inStockStatus;
    }

    public String getAfterCheckQC() {
        return afterCheckQC;
    }

    public void setAfterCheckQC(String afterCheckQC) {
        this.afterCheckQC = afterCheckQC;
    }

    public String getBeforeCheckQC() {
        return beforeCheckQC;
    }

    public void setBeforeCheckQC(String beforeCheckQC) {
        this.beforeCheckQC = beforeCheckQC;
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

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
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
