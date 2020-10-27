package pnc.mesadmin.dto.newmove.GetAllQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetAllQCMInfoResD implements Serializable {


    @JsonProperty("QCheckMaRd")
    private int QCheckMaRd;

    @JsonProperty("QCheckMaCode")
    private String QCheckMaCode;

    @JsonIgnore

    public int getQCheckMaRd() {
        return QCheckMaRd;
    }
    @JsonIgnore
    public void setQCheckMaRd(int QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
    }
    @JsonIgnore
    public String getQCheckMaCode() {
        return QCheckMaCode;
    }
    @JsonIgnore
    public void setQCheckMaCode(String QCheckMaCode) {
        this.QCheckMaCode = QCheckMaCode;
    }
}
