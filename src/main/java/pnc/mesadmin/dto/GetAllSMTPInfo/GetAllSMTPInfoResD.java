package pnc.mesadmin.dto.GetAllSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class GetAllSMTPInfoResD implements Serializable {
    @JsonProperty("SMTPRd")
    private int SMTPRd;
    @JsonProperty("SMTPName")
    private String SMTPName;
    @JsonIgnore
    public int getSMTPRd() {
        return SMTPRd;
    }
    @JsonIgnore
    public void setSMTPRd(int SMTPRd) {
        this.SMTPRd = SMTPRd;
    }
    @JsonIgnore
    public String getSMTPName() {
        return SMTPName;
    }
    @JsonIgnore
    public void setSMTPName(String SMTPName) {
        this.SMTPName = SMTPName;
    }
}
