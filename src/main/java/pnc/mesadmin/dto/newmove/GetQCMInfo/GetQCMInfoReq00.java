package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/7.
 */
public class GetQCMInfoReq00 implements Serializable {
    @JsonProperty("QCheckMaRd")
    private Integer QCheckMaRd;
    @JsonIgnore

    public Integer getQCheckMaRd() {
        return QCheckMaRd;
    }
    @JsonIgnore
    public void setQCheckMaRd(Integer QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
    }
}
