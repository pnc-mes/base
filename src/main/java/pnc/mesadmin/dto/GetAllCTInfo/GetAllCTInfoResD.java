package pnc.mesadmin.dto.GetAllCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:45
 * @Description:
 */
public class GetAllCTInfoResD implements Serializable {
    @JsonProperty("CTRd")
    private int CTRd;

    @JsonProperty("CTName")
    private String CTName;
    @JsonIgnore
    public int getCTRd() {
        return CTRd;
    }
    @JsonIgnore
    public void setCTRd(int CTRd) {
        this.CTRd = CTRd;
    }
    @JsonIgnore
    public String getCTName() {
        return CTName;
    }
    @JsonIgnore
    public void setCTName(String CTName) {
        this.CTName = CTName;
    }
}
