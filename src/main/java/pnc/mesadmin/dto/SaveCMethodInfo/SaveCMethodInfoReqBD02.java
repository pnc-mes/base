package pnc.mesadmin.dto.SaveCMethodInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCMethodInfoReqBD02 implements Serializable {

    @JsonProperty("CMethodRd")
    private int CMethodRd;

    @JsonProperty("CheckMethodCode")
    private String CheckMethodCode;

    @JsonProperty("CheckMethodName")
    private String CheckMethodName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getCMethodRd() {
        return CMethodRd;
    }
    @JsonIgnore
    public void setCMethodRd(int CMethodRd) {
        this.CMethodRd = CMethodRd;
    }
    @JsonIgnore
    public String getCheckMethodCode() {
        return CheckMethodCode;
    }
    @JsonIgnore
    public void setCheckMethodCode(String checkMethodCode) {
        CheckMethodCode = checkMethodCode;
    }
    @JsonIgnore
    public String getCheckMethodName() {
        return CheckMethodName;
    }
    @JsonIgnore
    public void setCheckMethodName(String checkMethodName) {
        CheckMethodName = checkMethodName;
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
