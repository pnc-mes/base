package pnc.mesadmin.dto.GetMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/6
 * @Description:
 */
public class GetMaPerionInfoReq00 implements Serializable {
    @JsonProperty("MaPerionRd")
    private int MaPerionRd;
    @JsonIgnore
    public int getMaPerionRd() {
        return MaPerionRd;
    }
    @JsonIgnore
    public void setMaPerionRd(int maPerionRd) {
        MaPerionRd = maPerionRd;
    }
}
