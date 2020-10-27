package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次信息Model
 * 创建人：王怀龙
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_binfo")
public class BInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String woSource;
    private String woGd;
    private String woDlGd;
    private String bType;
    private String isBarBatch;
    private String maVerGd;
    private String batch;
    private String lineGd;
    private String supBatch;
    private float num;
    private float canNum;
    private float consuNum;
    private String unitGd;
    private String wFVerGd;
    private String specVerGd;
    private String bLName;
    private String printTGd;
    private String bSource;
    private Date jStartDate;
    private Date jFinishDate;
    private Date sStartDate;
    private Date sFinishDate;
    private Date expireDate;
    private String bad;
    private float badNum;
    private String iQCStatus;
    private String ckResult;
    private String status;
    private String inStockStatus;
    private String wFStatus;
    private String packStatus;
    private String specLFlag;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String BeforeCheckQC;//入库前检验
    private String AfterCheckQC;//出库前检验
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

    public String getWoDlGd() {
        return woDlGd;
    }

    public void setWoDlGd(String woDlGd) {
        this.woDlGd = woDlGd;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getIsBarBatch() {
        return isBarBatch;
    }

    public void setIsBarBatch(String isBarBatch) {
        this.isBarBatch = isBarBatch;
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

    public String getLineGd() {
        return lineGd;
    }

    public void setLineGd(String lineGd) {
        this.lineGd = lineGd;
    }

    public String getSupBatch() {
        return supBatch;
    }

    public void setSupBatch(String supBatch) {
        this.supBatch = supBatch;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public float getCanNum() {
        return canNum;
    }

    public void setCanNum(float canNum) {
        this.canNum = canNum;
    }

    public float getConsuNum() {
        return consuNum;
    }

    public void setConsuNum(float consuNum) {
        this.consuNum = consuNum;
    }

    public String getUnitGd() {
        return unitGd;
    }

    public void setUnitGd(String unitGd) {
        this.unitGd = unitGd;
    }

    public String getwFVerGd() {
        return wFVerGd;
    }

    public void setwFVerGd(String wFVerGd) {
        this.wFVerGd = wFVerGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getbLName() {
        return bLName;
    }

    public void setbLName(String bLName) {
        this.bLName = bLName;
    }

    public String getPrintTGd() {
        return printTGd;
    }

    public void setPrintTGd(String printTGd) {
        this.printTGd = printTGd;
    }

    public String getbSource() {
        return bSource;
    }

    public void setbSource(String bSource) {
        this.bSource = bSource;
    }

    public Date getjStartDate() {
        return jStartDate;
    }

    public void setjStartDate(Date jStartDate) {
        this.jStartDate = jStartDate;
    }

    public Date getjFinishDate() {
        return jFinishDate;
    }

    public void setjFinishDate(Date jFinishDate) {
        this.jFinishDate = jFinishDate;
    }

    public Date getsStartDate() {
        return sStartDate;
    }

    public void setsStartDate(Date sStartDate) {
        this.sStartDate = sStartDate;
    }

    public Date getsFinishDate() {
        return sFinishDate;
    }

    public void setsFinishDate(Date sFinishDate) {
        this.sFinishDate = sFinishDate;
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

    public float getBadNum() {
        return badNum;
    }

    public void setBadNum(float badNum) {
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

    public String getwFStatus() {
        return wFStatus;
    }

    public void setwFStatus(String wFStatus) {
        this.wFStatus = wFStatus;
    }

    public String getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(String packStatus) {
        this.packStatus = packStatus;
    }

    public String getSpecLFlag() {
        return specLFlag;
    }

    public void setSpecLFlag(String specLFlag) {
        this.specLFlag = specLFlag;
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

    public String getBeforeCheckQC() {
        return BeforeCheckQC;
    }

    public void setBeforeCheckQC(String beforeCheckQC) {
        BeforeCheckQC = beforeCheckQC;
    }

    public String getAfterCheckQC() {
        return AfterCheckQC;
    }

    public void setAfterCheckQC(String afterCheckQC) {
        AfterCheckQC = afterCheckQC;
    }
}
