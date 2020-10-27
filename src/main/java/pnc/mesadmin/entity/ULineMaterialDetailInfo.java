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

@TableName(value = "tpm_ULineMaterialDetailInfo")
public class ULineMaterialDetailInfo {
    @TableId(value = "ruid", type = IdType.AUTO)
    private Integer ruid;
    private String guid;
    private String ulmGuid; //主表标识
    private String luType; //00工序物料(默认)，01机台物料
    private String devGd; //设备标识
    private String devName; //设备名称
    private String batchNo; //物料批次号
    private String lotNo; //物料批号
    private String materialCode; //物料代码
    private String materialName; //物料名称
    private String materialDes; //物料描述
    private String replaceMaterial; //是否替代物料 00是-01否
    private BigDecimal sumNum; //上料前物料总数量
    private BigDecimal canNum; //可用数量
    private String unitName; //单位
    private String status; //00-默认（正常状态），01-消耗用尽，02-删除以进行下料操作（状态置为01删除状态）
    private String creator; //创建人
    private Date createTime;// 创建时间
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

    public String getLuType() {
        return luType;
    }

    public void setLuType(String luType) {
        this.luType = luType;
    }

    public String getDevGd() {
        return devGd;
    }

    public void setDevGd(String devGd) {
        this.devGd = devGd;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getUlmGuid() {
        return ulmGuid;
    }

    public void setUlmGuid(String ulmGuid) {
        this.ulmGuid = ulmGuid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDes() {
        return materialDes;
    }

    public void setMaterialDes(String materialDes) {
        this.materialDes = materialDes;
    }

    public BigDecimal getSumNum() {
        return sumNum;
    }

    public void setSumNum(BigDecimal sumNum) {
        this.sumNum = sumNum;
    }

    public BigDecimal getCanNum() {
        return canNum;
    }

    public void setCanNum(BigDecimal canNum) {
        this.canNum = canNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReplaceMaterial() {
        return replaceMaterial;
    }

    public void setReplaceMaterial(String replaceMaterial) {
        this.replaceMaterial = replaceMaterial;
    }
}
