package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:36
 * @Description:
 */
public class GetMaxTimeInfoResDPresetEmailMessage {

    @JsonProperty("EmailMessageRd")
    private int EmailMessageRd;

    @JsonProperty("EmailMessageName")
    private String EmailMessageName;

    @JsonIgnore
    public int getEmailMessageRd() {
        return EmailMessageRd;
    }
    @JsonIgnore
    public void setEmailMessageRd(int emailMessageRd) {
        EmailMessageRd = emailMessageRd;
    }
    @JsonIgnore
    public String getEmailMessageName() {
        return EmailMessageName;
    }
    @JsonIgnore
    public void setEmailMessageName(String emailMessageName) {
        EmailMessageName = emailMessageName;
    }
}
