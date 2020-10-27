package pnc.mesadmin.dto.GetCKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKBarInfoResD {
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
