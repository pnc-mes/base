package pnc.mesadmin.dto.GetCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:59
 * @Description:
 */
public class GetCTInfoReq00 {
    @JsonProperty("CTRd")
    private int CTRd;

    @JsonIgnore
    public int getCTRd() {
        return CTRd;
    }
    @JsonIgnore
    public void setCTRd(int CTRd) {
        this.CTRd = CTRd;
    }
}
