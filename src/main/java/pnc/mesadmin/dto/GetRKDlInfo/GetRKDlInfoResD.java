package pnc.mesadmin.dto.GetRKDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoResD;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单明细信息DTO返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class GetRKDlInfoResD extends GetAllRKInfoResD implements Serializable{

    @JsonProperty("RkDtlRd")
    private int RkDtlRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("ScanNum")
    private float ScanNum;

    @JsonProperty("UnScanNum")
    private float UnScanNum;

    @JsonProperty("UnitName")
    private String UnitName;
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }

    @JsonIgnore
    public int getRkDtlRd() {
        return RkDtlRd;
    }

    @JsonIgnore
    public void setRkDtlRd(int rkDtlRd) {
        RkDtlRd = rkDtlRd;
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
    public float getScanNum() {
        return ScanNum;
    }

    @JsonIgnore
    public void setScanNum(float scanNum) {
        ScanNum = scanNum;
    }

    @JsonIgnore
    public float getUnScanNum() {
        return UnScanNum;
    }

    @JsonIgnore
    public void setUnScanNum(float unScanNum) {
        UnScanNum = unScanNum;
    }
}
