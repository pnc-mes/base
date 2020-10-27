package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：从页面传过来的请求信息RKMaInfo
 * 创建人：张亮亮
 * 创建时间：2017-8-9
 * 修改人：
 * 修改时间：
 */
public class SavePickInfoReq02RKMa {
    @JsonProperty("PKDtlRd")
    private int PKDtlRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("ReMaVerRd")
    private int ReMaVerRd;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public int getPKDtlRd() {
        return PKDtlRd;
    }
    @JsonIgnore
    public void setPKDtlRd(int PKDtlRd) {
        this.PKDtlRd = PKDtlRd;
    }
    @JsonIgnore
    public int getReMaVerRd() {
        return ReMaVerRd;
    }
    @JsonIgnore
    public void setReMaVerRd(int reMaVerRd) {
        ReMaVerRd = reMaVerRd;
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
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
