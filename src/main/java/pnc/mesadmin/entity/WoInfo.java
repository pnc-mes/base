package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单信息实体类
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_woinfo")
public class WoInfo {
    private int ruid;
    private  String woTGd;
    private String guid;
    private String woCode;
    private String urcyGd;
    private String maVerGd;
    private String bomVerGd;
    private String wFVerGd;
    private String orderCode;
    private float num;
    private float finishNum;
    private float wIPNum;
    private float unCBatNum;
    private String unitGd;
    private String pKEFlag;
    private String pEndFlag;
    private String trayPackGd;
    private String boxPackGd;
    private String dSource;
    private Date jStartDate;
    private Date jFinishDate;
    private Date sStartDate;
    private Date sFinishDate;
    private Date releaseDate;
    private String status;
    private String creator;
    private Date createTime;
    private Date lastModifyTime;
    private String lastModifyMan;
    private String Remark;

    public String getdSource() {
        return dSource;
    }

    public void setdSource(String dSource) {
        this.dSource = dSource;
    }

    public String getpKEFlag() {
        return pKEFlag;
    }

    public void setpKEFlag(String pKEFlag) {
        this.pKEFlag = pKEFlag;
    }

    public String getpEndFlag() {
        return pEndFlag;
    }

    public void setpEndFlag(String pEndFlag) {
        this.pEndFlag = pEndFlag;
    }

    public String getUrcyGd() {
        return urcyGd;
    }

    public void setUrcyGd(String urcyGd) {
        this.urcyGd = urcyGd;
    }

    public String getWoTGd() {
        return woTGd;
    }

    public void setWoTGd(String woTGd) {
        this.woTGd = woTGd;
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

    public String getBomVerGd() {
        return bomVerGd;
    }

    public void setBomVerGd(String bomVerGd) {
        this.bomVerGd = bomVerGd;
    }

    public String getwFVerGd() {
        return wFVerGd;
    }

    public void setwFVerGd(String wFVerGd) {
        this.wFVerGd = wFVerGd;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public float getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(float finishNum) {
        this.finishNum = finishNum;
    }

    public float getUnCBatNum() {
        return unCBatNum;
    }

    public void setUnCBatNum(float unCBatNum) {
        this.unCBatNum = unCBatNum;
    }

    public String getUnitGd() {
        return unitGd;
    }

    public void setUnitGd(String unitGd) {
        this.unitGd = unitGd;
    }

    public String getTrayPackGd() {
        return trayPackGd;
    }

    public void setTrayPackGd(String trayPackGd) {
        this.trayPackGd = trayPackGd;
    }

    public String getBoxPackGd() {
        return boxPackGd;
    }

    public void setBoxPackGd(String boxPackGd) {
        this.boxPackGd = boxPackGd;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public float getwIPNum() {
        return wIPNum;
    }

    public void setwIPNum(float wIPNum) {
        this.wIPNum = wIPNum;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}
