package pnc.mesadmin.dto.GetAllBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by PNC on 2017/6/9.
 */
public class GetAllBatchInfoResD {
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("WoSource")
    private String WoSource;
    @JsonProperty("CreateDate")
    private String CreateDate;


    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getCreateDate() {
        return CreateDate;
    }

    @JsonIgnore
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
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
    public String getWoSource() {
        return WoSource;
    }

    @JsonIgnore
    public void setWoSource(String woSource) {
        WoSource = woSource;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
}
