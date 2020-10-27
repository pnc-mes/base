package pnc.mesadmin.dto.GetEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:40
 * @Description:
 */
public class GetEmailDisInfoResDAddressList {
    @JsonProperty("Recipient")
    private String Recipient;

    @JsonProperty("EmailAddress")
    private String EmailAddress;
    @JsonIgnore
    public String getRecipient() {
        return Recipient;
    }
    @JsonIgnore
    public void setRecipient(String recipient) {
        Recipient = recipient;
    }
    @JsonIgnore
    public String getEmailAddress() {
        return EmailAddress;
    }
    @JsonIgnore
    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }
}
