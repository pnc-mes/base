package pnc.mesadmin.entity;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具关系日志Model
 * 创建人：潘俊峰
 * 创建时间：2018-12-10
 * 修改人：
 * 修改时间：
 */
public class CarrierRelationLogInfo {
    private int ruid;
    private String carrierGd;
    private String batch;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getCarrierGd() {
        return carrierGd;
    }

    public void setCarrierGd(String carrierGd) {
        this.carrierGd = carrierGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
