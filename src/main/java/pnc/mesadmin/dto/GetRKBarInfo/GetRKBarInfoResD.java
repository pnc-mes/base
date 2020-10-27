package pnc.mesadmin.dto.GetRKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单条码信息DTO返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class GetRKBarInfoResD implements Serializable{

    @JsonProperty("BarCode")
    private String BarCode;

    @JsonProperty("ScannerTime")
    private String ScannerTime;

    @JsonProperty("ProductDate")
    private String ProductDate;

    @JsonProperty("ExpireDate")
    private String ExpireDate;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @JsonIgnore
    public String getScannerTime() {
        return ScannerTime;
    }

    @JsonIgnore
    public void setScannerTime(String scannerTime) {
        ScannerTime = scannerTime;
    }

    @JsonIgnore
    public String getProductDate() {
        return ProductDate;
    }

    @JsonIgnore
    public void setProductDate(String productDate) {
        ProductDate = productDate;
    }

    @JsonIgnore
    public String getExpireDate() {
        return ExpireDate;
    }

    @JsonIgnore
    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
