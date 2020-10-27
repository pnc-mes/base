package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 名称 上下料log记录表
 * 创建时间：2019-06-15
 * 修改人：yin.yang
 * 修改时间：
 */

@TableName(value = "tpm_UDMaterialLogInfo")
public class UDMaterialLogInfo {
    @TableId(value = "Ruid", type = IdType.AUTO)
    private Integer Ruid;
    private String Guid;
    private String UdType; //00上料，01下料
    private String LuType; //00工序，01机台
    private String BatchNo; //物料批次号
    private String LotNo; //物料批号
    private String MaterialCode; //物料代码
    private String MaterialName; //物料名称
    private String MaterialDes; //物料描述
    private String ReplaceMaterial; //是否替代物料 00是-01否
    private String WOCode; //工单号
    private String SpecName;//工序名称
    private String SpecGuid; //工艺标识
    private String DevGd; //设备标识
    private String DevName; //设备名称
    private BigDecimal SumNum; //上料/下料数量
    private BigDecimal CanNum; //下料时剩余数量 --系统记录
    private BigDecimal RealityNum; //下料时剩余数量 --手动输入
    private String RealityType; //下料类型 00合格，01原损，02制损，03其它
    private String UnitName; //单位
    private String Creator; //创建人
    private Date CreateTime;// 创建时间
    private String Remark;// 备注

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getUdType() {
        return UdType;
    }

    public void setUdType(String udType) {
        UdType = udType;
    }

    public String getLuType() {
        return LuType;
    }

    public void setLuType(String luType) {
        LuType = luType;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialDes() {
        return MaterialDes;
    }

    public void setMaterialDes(String materialDes) {
        MaterialDes = materialDes;
    }

    public String getWOCode() {
        return WOCode;
    }

    public void setWOCode(String WOCode) {
        this.WOCode = WOCode;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String specName) {
        SpecName = specName;
    }

    public String getSpecGuid() {
        return SpecGuid;
    }

    public void setSpecGuid(String specGuid) {
        SpecGuid = specGuid;
    }

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getDevName() {
        return DevName;
    }

    public void setDevName(String devName) {
        DevName = devName;
    }

    public BigDecimal getSumNum() {
        return SumNum;
    }

    public void setSumNum(BigDecimal sumNum) {
        SumNum = sumNum;
    }

    public BigDecimal getCanNum() {
        return CanNum;
    }

    public void setCanNum(BigDecimal canNum) {
        CanNum = canNum;
    }

    public BigDecimal getRealityNum() {
        return RealityNum;
    }

    public void setRealityNum(BigDecimal realityNum) {
        RealityNum = realityNum;
    }

    public String getRealityType() {
        return RealityType;
    }

    public void setRealityType(String realityType) {
        RealityType = realityType;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getReplaceMaterial() {
        return ReplaceMaterial;
    }

    public void setReplaceMaterial(String replaceMaterial) {
        ReplaceMaterial = replaceMaterial;
    }
}
