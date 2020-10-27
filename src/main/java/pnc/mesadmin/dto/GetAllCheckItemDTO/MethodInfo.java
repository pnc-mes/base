package pnc.mesadmin.dto.GetAllCheckItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/31.
 */
public class MethodInfo implements Serializable{

    @JsonProperty("CMethodRd")
    private Integer CMethodRd;
    @JsonProperty("CheckMethodName")
    private String CheckMethodName;

    @JsonIgnore
    public Integer getCMethodRd() {
        return CMethodRd;
    }
    @JsonIgnore
    public void setCMethodRd(Integer CMethodRd) {
        this.CMethodRd = CMethodRd;
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
