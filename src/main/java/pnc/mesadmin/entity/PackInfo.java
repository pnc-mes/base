package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印日志信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-21
 * 修改人：
 * 修改时间：
 */
public class PackInfo {
    private int ruid;
    private String guid;
    private String pCode;
    private String barType;
    private String barCode;
    private String woCode;
    private String maVerGd;
    private String printTGd;
    private float num;
    private String Status;
    private float pWeight;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String BeforeCheckQC; //入库前检验 00合格，01不合格
    private String AfterCheckQC; //出库前检验 00合格，01不合格
    private String SaleStatus; //销售状态
    private String isRework; //返工状态 默认空，00返工
    private String FreezeMsg; //冻结原因
    private String ObsoleteMsg; //作废原因
    private String isFull; //是否满箱
    private String TorsionStatus; //流转状态 00待检验,01入库检验,02在库，03备货，04出库检验，05离库

    public String getIsFull() {
        return isFull;
    }

    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }

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

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getBarType() {
        return barType;
    }

    public void setBarType(String barType) {
        this.barType = barType;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getWoCode() {
        return woCode;
    }

    public void setWoCode(String woCode) {
        this.woCode = woCode;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public String getPrintTGd() {
        return printTGd;
    }

    public void setPrintTGd(String printTGd) {
        this.printTGd = printTGd;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public float getpWeight() {
        return pWeight;
    }

    public void setpWeight(float pWeight) {
        this.pWeight = pWeight;
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

    public String getFreezeMsg() {
        return FreezeMsg;
    }

    public void setFreezeMsg(String freezeMsg) {
        FreezeMsg = freezeMsg;
    }

    public String getObsoleteMsg() {
        return ObsoleteMsg;
    }

    public void setObsoleteMsg(String obsoleteMsg) {
        ObsoleteMsg = obsoleteMsg;
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

    public String getSaleStatus() {
        return SaleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        SaleStatus = saleStatus;
    }


    public String getIsRework() {
        return isRework;
    }

    public void setIsRework(String isRework) {
        this.isRework = isRework;
    }

    public String getTorsionStatus() {
        return TorsionStatus;
    }

    public void setTorsionStatus(String torsionStatus) {
        TorsionStatus = torsionStatus;
    }
}
