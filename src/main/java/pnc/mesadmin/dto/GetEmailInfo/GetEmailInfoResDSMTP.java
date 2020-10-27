package pnc.mesadmin.dto.GetEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/11 16:49
 * @Description:
 */
public class GetEmailInfoResDSMTP implements Serializable {
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
