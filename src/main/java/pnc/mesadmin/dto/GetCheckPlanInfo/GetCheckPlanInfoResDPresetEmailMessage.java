package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCheckPlanInfoResDPresetEmailMessage implements Serializable {
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
