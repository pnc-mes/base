package pnc.mesadmin.dto.newmove.SaveZOutMaInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/3 09:07
 * @Description:
 */
public class SaveZOutMaInfoReq00OutMa {
    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("LRd")
    private int LRd;

    @JsonProperty("BarType")
    private String BarType;


    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("F1")
    private String F1;

    @JsonProperty("F2")
    private String F2;

    @JsonProperty("F3")
    private String F3;

    @JsonProperty("F4")
    private String F4;
    @JsonIgnore
    public String getF1() {
        return F1;
    }
    @JsonIgnore
    public void setF1(String f1) {
        F1 = f1;
    }
    @JsonIgnore
    public String getF2() {
        return F2;
    }
    @JsonIgnore
    public void setF2(String f2) {
        F2 = f2;
    }
    @JsonIgnore
    public String getF3() {
        return F3;
    }
    @JsonIgnore
    public void setF3(String f3) {
        F3 = f3;
    }
    @JsonIgnore
    public String getF4() {
        return F4;
    }
    @JsonIgnore
    public void setF4(String f4) {
        F4 = f4;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }
    @JsonIgnore
    public float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public int getLRd() {
        return LRd;
    }
    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }
    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}
