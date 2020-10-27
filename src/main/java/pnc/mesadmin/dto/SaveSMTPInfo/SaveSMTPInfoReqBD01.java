package pnc.mesadmin.dto.SaveSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class SaveSMTPInfoReqBD01 implements Serializable {

    @JsonProperty("SMTPRd")
    private int SMTPRd;
    @JsonIgnore

    public int getSMTPRd() {
        return SMTPRd;
    }
    @JsonIgnore
    public void setSMTPRd(int SMTPRd) {
        this.SMTPRd = SMTPRd;
    }
}
