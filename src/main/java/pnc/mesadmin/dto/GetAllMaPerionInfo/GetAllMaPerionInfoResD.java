package pnc.mesadmin.dto.GetAllMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: haozan
 * @Date: 2018/9/6
 * @Description:
 */
public class GetAllMaPerionInfoResD {
    @JsonProperty("MaPerionRd")
    private int MaPerionRd;

    @JsonProperty("MaPeriodName")
    private String MaPeriodName;

    @JsonIgnore
    public int getMaPerionRd() {
        return MaPerionRd;
    }

    @JsonIgnore
    public void setMaPerionRd(int maPerionRd) {
        MaPerionRd = maPerionRd;
    }
    @JsonIgnore
    public String getMaPeriodName() {
        return MaPeriodName;
    }
    @JsonIgnore
    public void setMaPeriodName(String maPeriodName) {
        MaPeriodName = maPeriodName;
    }
}
