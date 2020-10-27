package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具信息实体类
 * 创建人：HAOZAN
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_carrierinfo")
public class CarrierInfo implements Serializable {
    private Integer Ruid;
    private String Guid;
    private String CarrierName;
    private String SupplierGd;
    private String CarrierFamilyGd;
    private String VenderSN;
    private String DevSMGd;
    private String DevSGD;
    private String FaGd;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String Status;

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

    public String getCarrierName() {
        return CarrierName;
    }

    public void setCarrierName(String carrierName) {
        CarrierName = carrierName;
    }

    public String getSupplierGd() {
        return SupplierGd;
    }

    public void setSupplierGd(String supplierGd) {
        SupplierGd = supplierGd;
    }

    public String getCarrierFamilyGd() {
        return CarrierFamilyGd;
    }

    public void setCarrierFamilyGd(String carrierFamilyGd) {
        CarrierFamilyGd = carrierFamilyGd;
    }

    public String getVenderSN() {
        return VenderSN;
    }

    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }

    public String getDevSMGd() {
        return DevSMGd;
    }

    public void setDevSMGd(String devSMGd) {
        DevSMGd = devSMGd;
    }

    public String getFaGd() {
        return FaGd;
    }

    public void setFaGd(String faGd) {
        FaGd = faGd;
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

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDevSGD() {
        return DevSGD;
    }

    public void setDevSGD(String devSGD) {
        DevSGD = devSGD;
    }
}
