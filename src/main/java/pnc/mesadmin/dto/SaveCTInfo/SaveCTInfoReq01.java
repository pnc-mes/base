package pnc.mesadmin.dto.SaveCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 15:16
 * @Description:
 */
public class SaveCTInfoReq01 {
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
