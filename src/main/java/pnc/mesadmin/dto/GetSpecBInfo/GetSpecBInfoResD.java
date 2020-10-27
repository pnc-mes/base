package pnc.mesadmin.dto.GetSpecBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveIOSInfo.SaveIOSInfoReqBD06;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/13.
 */
public class GetSpecBInfoResD implements Serializable{

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("WFName")
    private String WFName;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("OptName")
    private String OptName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("DoBatchInfo")
    private SaveIOSInfoReqBD06 DoBatchInfo;

    @JsonProperty("DevGInfo")
    private List<GetSpecBInfoResDDevG> DevGInfo;

    @JsonProperty("SOPInfo")
    private List<GetSpecBInfoResDSOP> SOPInfo;

    @JsonProperty("WJSpecInfo")
    private List<GetSpecBInfoResDWJSpec> WJSpecInfo;

    @JsonProperty("WCSpecInfo")
    private List<GetSpecBInfoResDWCSpec> WCSpecInfo;

    @JsonProperty("BOMInfo")
    private List<GetSpecBInfoResDBOM> BOMInfo;

    @JsonProperty("DCInfo")
    private List<GetSpecBInfoResDDC> DCInfo;

    @JsonProperty("RCGInfo")
    private List<GetSpecBInfoResDRCG> RCGInfo;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
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
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
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
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
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
    public String getOptName() {
        return OptName;
    }

    @JsonIgnore
    public void setOptName(String optName) {
        OptName = optName;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveIOSInfoReqBD06 getDoBatchInfo() {
        return DoBatchInfo;
    }

    @JsonIgnore
    public void setDoBatchInfo(SaveIOSInfoReqBD06 doBatchInfo) {
        DoBatchInfo = doBatchInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDDevG> getDevGInfo() {
        return DevGInfo;
    }

    @JsonIgnore
    public void setDevGInfo(List<GetSpecBInfoResDDevG> devGInfo) {
        DevGInfo = devGInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDSOP> getSOPInfo() {
        return SOPInfo;
    }

    @JsonIgnore
    public void setSOPInfo(List<GetSpecBInfoResDSOP> SOPInfo) {
        this.SOPInfo = SOPInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDWJSpec> getWJSpecInfo() {
        return WJSpecInfo;
    }

    @JsonIgnore
    public void setWJSpecInfo(List<GetSpecBInfoResDWJSpec> WJSpecInfo) {
        this.WJSpecInfo = WJSpecInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDWCSpec> getWCSpecInfo() {
        return WCSpecInfo;
    }

    @JsonIgnore
    public void setWCSpecInfo(List<GetSpecBInfoResDWCSpec> WCSpecInfo) {
        this.WCSpecInfo = WCSpecInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDBOM> getBOMInfo() {
        return BOMInfo;
    }

    @JsonIgnore
    public void setBOMInfo(List<GetSpecBInfoResDBOM> BOMInfo) {
        this.BOMInfo = BOMInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDDC> getDCInfo() {
        return DCInfo;
    }

    @JsonIgnore
    public void setDCInfo(List<GetSpecBInfoResDDC> DCInfo) {
        this.DCInfo = DCInfo;
    }

    @JsonIgnore
    public List<GetSpecBInfoResDRCG> getRCGInfo() {
        return RCGInfo;
    }

    @JsonIgnore
    public void setRCGInfo(List<GetSpecBInfoResDRCG> RCGInfo) {
        this.RCGInfo = RCGInfo;
    }
}
