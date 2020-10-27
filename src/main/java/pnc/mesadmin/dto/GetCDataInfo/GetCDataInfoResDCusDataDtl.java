package pnc.mesadmin.dto.GetCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:30
 * @Description:
 */
public class GetCDataInfoResDCusDataDtl implements Serializable {
    @JsonProperty("CusDataDtlRd")
    private int CusDataDtlRd;

    @JsonProperty("DisplayName")
    private String DisplayName;

    @JsonProperty("Val")
    private String Val;

    @JsonIgnore
    public int getCusDataDtlRd() {
        return CusDataDtlRd;
    }
    @JsonIgnore
    public void setCusDataDtlRd(int cusDataDtlRd) {
        CusDataDtlRd = cusDataDtlRd;
    }
    @JsonIgnore
    public String getDisplayName() {
        return DisplayName;
    }
    @JsonIgnore
    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
    @JsonIgnore
    public String getVal() {
        return Val;
    }
    @JsonIgnore
    public void setVal(String val) {
        Val = val;
    }
}
