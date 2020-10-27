package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Description 设备信息
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
@TableName("tpm_devInfo")
public class DevInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private int ruid;
    private String guid;
    private String devName; //设备名称
    private String devCode; //设备编号
    private String devModel; //设备型号
    private Integer devTypeRd; //设备类型ID
    private String devStatus; //设备状态 00在线 01离线 02闲置
    private Integer lineRd; //线别ID
    private String supplierName; //设备厂商
    private String entryTime; //进场时间
    private String runTime; //运行时长
    private String duraMax; //使用次数
    private String accumulatedRunTime; //累计运行时长
    private String accumulatedDuraMax; //累计使用次数
    private Integer maintainPlanId; //设备保养计划ID
    private Integer checkPlanId; //设备点检计划ID
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

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String devModel) {
        this.devModel = devModel;
    }

    public Integer getDevTypeRd() {
        return devTypeRd;
    }

    public void setDevTypeRd(Integer devTypeRd) {
        this.devTypeRd = devTypeRd;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getLineRd() {
        return lineRd;
    }

    public void setLineRd(Integer lineRd) {
        this.lineRd = lineRd;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getDuraMax() {
        return duraMax;
    }

    public void setDuraMax(String duraMax) {
        this.duraMax = duraMax;
    }

    public String getAccumulatedRunTime() {
        return accumulatedRunTime;
    }

    public void setAccumulatedRunTime(String accumulatedRunTime) {
        this.accumulatedRunTime = accumulatedRunTime;
    }

    public String getAccumulatedDuraMax() {
        return accumulatedDuraMax;
    }

    public void setAccumulatedDuraMax(String accumulatedDuraMax) {
        this.accumulatedDuraMax = accumulatedDuraMax;
    }

    public Integer getMaintainPlanId() {
        return maintainPlanId;
    }

    public void setMaintainPlanId(Integer maintainPlanId) {
        this.maintainPlanId = maintainPlanId;
    }

    public Integer getCheckPlanId() {
        return checkPlanId;
    }

    public void setCheckPlanId(Integer checkPlanId) {
        this.checkPlanId = checkPlanId;
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
