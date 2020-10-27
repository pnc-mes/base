package pnc.mesadmin.dto.SaveMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/21.
 */
public class SaveMTInfoReqBD02 implements Serializable {
    @JsonProperty("PMTRd")
    private int PMTRd;

    @JsonProperty("MTRd")
    private int MTRd;

    @JsonProperty("MTCode")
    private String MTCode;

    @JsonProperty("MTName")
    private String MTName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonProperty("MaType")
    private String MaType;

    @JsonIgnore
    public int getPMTRd() {
        return PMTRd;
    }

    @JsonIgnore
    public void setPMTRd(int PMTRd) {
        this.PMTRd = PMTRd;
    }

    @JsonIgnore
    public int getMTRd() {
        return MTRd;
    }

    @JsonIgnore
    public void setMTRd(int MTRd) {
        this.MTRd = MTRd;
    }

    @JsonIgnore
    public String getMTCode() {
        return MTCode;
    }

    @JsonIgnore
    public void setMTCode(String MTCode) {
        this.MTCode = MTCode;
    }

    @JsonIgnore
    public String getMTName() {
        return MTName;
    }

    @JsonIgnore
    public void setMTName(String MTName) {
        this.MTName = MTName;
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
    public int getExpandRd() {
        return ExpandRd;
    }
    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }

    @JsonIgnore
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }
}
