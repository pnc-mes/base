package pnc.mesadmin.dto.SaveMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/6 16:13
 * @Description:
 */
public class SaveMaPerionInfoReq01 implements Serializable {
    @JsonProperty("MaPerionRd")
    private Integer MaPerionRd;

    @JsonIgnore
    public Integer getMaPerionRd() {
        return MaPerionRd;
    }
    @JsonIgnore
    public void setMaPerionRd(Integer maPerionRd) {
        MaPerionRd = maPerionRd;
    }
}
