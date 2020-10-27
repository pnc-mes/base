package pnc.mesadmin.dto.SaveSPartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:54
 * @Description:
 */
public class SaveSPartInfoReq00 implements Serializable {
    @JsonProperty("SPartName")
    private String SPartName;

    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("SupRd")
    private int SupRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getSPartName() {
        return SPartName;
    }
    @JsonIgnore
    public void setSPartName(String SPartName) {
        this.SPartName = SPartName;
    }
    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }
    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }
    @JsonIgnore
    public int getFaRd() {
        return FaRd;
    }
    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }
    @JsonIgnore
    public int getSupRd() {
        return SupRd;
    }
    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
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
