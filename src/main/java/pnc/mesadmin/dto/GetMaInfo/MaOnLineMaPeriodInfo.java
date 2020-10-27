package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by LENOVO on 2018/9/14.
 */
public class MaOnLineMaPeriodInfo {

    @JsonProperty("MaPerionRd")
    private Integer MaPerionRd;
    @JsonProperty("MaPeriodName")
    private String MaPeriodName;

    @JsonIgnore
    public Integer getMaPerionRd() {
        return MaPerionRd;
    }
    @JsonIgnore
    public void setMaPerionRd(Integer maPerionRd) {
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
