package pnc.mesadmin.dto.GetAllCMethodInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetAllCMethodInfoResD implements Serializable {
    @JsonProperty("CMethodRd")
    private int CMethodRd;

    @JsonProperty("CheckMethodCode")
    private String CheckMethodCode;

    @JsonProperty("CheckMethodName")
    private String CheckMethodName;
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
}
