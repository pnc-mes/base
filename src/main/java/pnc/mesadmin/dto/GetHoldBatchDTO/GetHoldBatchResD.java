package pnc.mesadmin.dto.GetHoldBatchDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetHoldBatchResD {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("UpdateDate")
    private String UpdateDate;

    @JsonProperty("UpdateMan")
    private String UpdateMan;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }

    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getUpdateDate() {
        return UpdateDate;
    }

    @JsonIgnore
    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    @JsonIgnore
    public String getUpdateMan() {
        return UpdateMan;
    }

    @JsonIgnore
    public void setUpdateMan(String updateMan) {
        UpdateMan = updateMan;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }
}
