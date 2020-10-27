package pnc.mesadmin.dto.SaveEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/5.
 */
public class SaveEmailInfoReqBD00 implements Serializable {
    @JsonProperty("EmailName")
    private String EmailName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Subject")
    private String Subject;

    @JsonProperty("Message")
    private String Message;

    @JsonProperty("SendRd")
    private int SendRd;

    @JsonProperty("SMTPRd")
    private int SMTPRd;

    @JsonProperty("MessageFormat")
    private Boolean MessageFormat;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getEmailName() {
        return EmailName;
    }
    @JsonIgnore
    public void setEmailName(String emailName) {
        EmailName = emailName;
    }
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public String getSubject() {
        return Subject;
    }
    @JsonIgnore
    public void setSubject(String subject) {
        Subject = subject;
    }
    @JsonIgnore
    public String getMessage() {
        return Message;
    }
    @JsonIgnore
    public void setMessage(String message) {
        Message = message;
    }
    @JsonIgnore
    public Boolean getMessageFormat() {
        return MessageFormat;
    }
    @JsonIgnore
    public void setMessageFormat(Boolean messageFormat) {
        MessageFormat = messageFormat;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public int getSendRd() {
        return SendRd;
    }
    @JsonIgnore
    public void setSendRd(int sendRd) {
        SendRd = sendRd;
    }
    @JsonIgnore
    public int getSMTPRd() {
        return SMTPRd;
    }
    @JsonIgnore
    public void setSMTPRd(int SMTPRd) {
        this.SMTPRd = SMTPRd;
    }
}
