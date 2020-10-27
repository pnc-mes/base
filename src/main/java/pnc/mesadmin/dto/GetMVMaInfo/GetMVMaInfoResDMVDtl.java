package pnc.mesadmin.dto.GetMVMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public class GetMVMaInfoResDMVDtl {
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("MOSName")
    private String MOSName;
    @JsonProperty("MONum")
    private float MONum;
    @JsonProperty("MISName")
    private String MISName;
    @JsonProperty("MINum")
    private float MINum;
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
    public String getMOSName() {
        return MOSName;
    }
    @JsonIgnore
    public void setMOSName(String MOSName) {
        this.MOSName = MOSName;
    }
    @JsonIgnore
    public float getMONum() {
        return MONum;
    }
    @JsonIgnore
    public void setMONum(float MONum) {
        this.MONum = MONum;
    }
    @JsonIgnore
    public String getMISName() {
        return MISName;
    }
    @JsonIgnore
    public void setMISName(String MISName) {
        this.MISName = MISName;
    }
    @JsonIgnore
    public float getMINum() {
        return MINum;
    }
    @JsonIgnore
    public void setMINum(float MINum) {
        this.MINum = MINum;
    }
}
