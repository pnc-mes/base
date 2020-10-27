package pnc.mesadmin.dto.GetSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetSUBInfoResD {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("BadNum")
    private float BadNum;

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PtName")
    private String PtName;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("PrName")
    private String PrName;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("RefBatch")
    private List<GetSUBInfoResDRefBatch> RefBatch;

    @JsonIgnore
    public String getPtName() {
        return PtName;
    }

    @JsonIgnore
    public void setPtName(String ptName) {
        PtName = ptName;
    }

    @JsonIgnore
    public String getPrName() {
        return PrName;
    }

    @JsonIgnore
    public void setPrName(String prName) {
        PrName = prName;
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
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public float getBadNum() {
        return BadNum;
    }

    @JsonIgnore
    public void setBadNum(float badNum) {
        BadNum = badNum;
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
    public int getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
    }

    @JsonIgnore
    public int getPrintRd() {
        return PrintRd;
    }

    @JsonIgnore
    public void setPrintRd(int printRd) {
        PrintRd = printRd;
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
    public List<GetSUBInfoResDRefBatch> getRefBatch() {
        return RefBatch;
    }

    @JsonIgnore
    public void setRefBatch(List<GetSUBInfoResDRefBatch> refBatch) {
        RefBatch = refBatch;
    }
}
