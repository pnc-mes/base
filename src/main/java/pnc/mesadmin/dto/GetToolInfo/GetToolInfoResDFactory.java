package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/18 11:17
 * @Description:
 */
public class GetToolInfoResDFactory implements Serializable {
    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("FaName")
    private String FaName;

    @JsonIgnore
    public int getFaRd() {
        return FaRd;
    }
    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }
    @JsonIgnore
    public String getFaName() {
        return FaName;
    }
    @JsonIgnore
    public void setFaName(String faName) {
        FaName = faName;
    }
}
