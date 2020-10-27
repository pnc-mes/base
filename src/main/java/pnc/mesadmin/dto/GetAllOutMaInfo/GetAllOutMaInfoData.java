package pnc.mesadmin.dto.GetAllOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 13:48
 * @Description:
 */
public class GetAllOutMaInfoData {
    @JsonProperty("OutMaRd")
    private int OutMaRd;

    @JsonProperty("OutCode")
    private String OutCode;
    @JsonIgnore
    public int getOutMaRd() {
        return OutMaRd;
    }
    @JsonIgnore
    public void setOutMaRd(int outMaRd) {
        OutMaRd = outMaRd;
    }
    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }
    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }
}
