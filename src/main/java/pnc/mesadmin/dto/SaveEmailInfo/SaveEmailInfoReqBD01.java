package pnc.mesadmin.dto.SaveEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/5.
 */
public class SaveEmailInfoReqBD01 implements Serializable {

    @JsonProperty("EmailRd")
    private int EmailRd;
    @JsonIgnore
    public int getEmailRd() {
        return EmailRd;
    }
    @JsonIgnore
    public void setEmailRd(int emailRd) {
        EmailRd = emailRd;
    }
}
