package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/6.
 */
public class PresetEmailMessageInfo {
    @JsonProperty("EmailMessageRd")
    private Integer EmailMessageRd;

    @JsonProperty("EmailMessageName")
    private String EmailMessageName;

    @JsonIgnore
    public Integer getEmailMessageRd() {
        return EmailMessageRd;
    }
    @JsonIgnore
    public void setEmailMessageRd(Integer emailMessageRd) {
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
