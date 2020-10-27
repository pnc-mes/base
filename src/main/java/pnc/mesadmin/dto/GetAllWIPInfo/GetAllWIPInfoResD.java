package pnc.mesadmin.dto.GetAllWIPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/17.
 */
public class GetAllWIPInfoResD implements Serializable {

    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("WFName")
    private String WFName;
    @JsonProperty("Num")
    private Float Num;
    @JsonProperty("UnTNum")
    private Float UnTNum;
    @JsonProperty("WIPNum")
    private Float WIPNum;
    @JsonProperty("FinishNum")
    private Float FinishNum;
    @JsonProperty("JStartDate")
    private String JStartDate;
    @JsonProperty("ReleaseDate")
    private String ReleaseDate;
    @JsonProperty("SStartDate")
    private String SStartDate;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }
    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
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
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }
    @JsonIgnore
    public String getWFName() {
        return WFName;
    }
    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
    @JsonIgnore
    public Float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
    }
    @JsonIgnore
    public Float getUnTNum() {
        return UnTNum;
    }
    @JsonIgnore
    public void setUnTNum(Float unTNum) {
        UnTNum = unTNum;
    }
    @JsonIgnore
    public Float getWIPNum() {
        return WIPNum;
    }
    @JsonIgnore
    public void setWIPNum(Float WIPNum) {
        this.WIPNum = WIPNum;
    }
    @JsonIgnore
    public Float getFinishNum() {
        return FinishNum;
    }
    @JsonIgnore
    public void setFinishNum(Float finishNum) {
        FinishNum = finishNum;
    }
    @JsonIgnore
    public String getJStartDate() {
        return JStartDate;
    }
    @JsonIgnore
    public void setJStartDate(String JStartDate) {
        this.JStartDate = JStartDate;
    }
    @JsonIgnore
    public String getReleaseDate() {
        return ReleaseDate;
    }
    @JsonIgnore
    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }
    @JsonIgnore
    public String getSStartDate() {
        return SStartDate;
    }
    @JsonIgnore
    public void setSStartDate(String SStartDate) {
        this.SStartDate = SStartDate;
    }
}
