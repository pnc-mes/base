package pnc.mesadmin.dto.SaveOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 17:07
 * @Description:
 */
public class SaveOutMaInfoReq03 {
    @JsonProperty("OutMaRd")
    private int OutMaRd;
    @JsonIgnore
    public int getOutMaRd() {
        return OutMaRd;
    }
    @JsonIgnore
    public void setOutMaRd(int outMaRd) {
        OutMaRd = outMaRd;
    }
}
