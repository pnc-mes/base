package pnc.mesadmin.dto.GetEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PNC on 2018/7/4.
 */
public class GetEmailInfoResD implements Serializable {


    @JsonProperty("EmailRd")
    private int EmailRd;

    @JsonProperty("EmailName")
    private String EmailName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Subject")
    private String Subject;

    @JsonProperty("Message")
    private String Message;


    @JsonProperty("MessageFormat")
    private Boolean MessageFormat;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private Date CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private Date LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("SMTP")
    private GetEmailInfoResDSMTP SMTP;

    @JsonProperty("Sender")
    private GetEmailInfoResDSender Sender;



    @JsonIgnore
    public int getEmailRd() {
        return EmailRd;
    }
    @JsonIgnore
    public void setEmailRd(int emailRd) {
        EmailRd = emailRd;
    }
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
    public GetEmailInfoResDSMTP getSMTP() {
        return SMTP;
    }
    @JsonIgnore
    public void setSMTP(GetEmailInfoResDSMTP SMTP) {
        this.SMTP = SMTP;
    }
    @JsonIgnore
    public GetEmailInfoResDSender getSender() {
        return Sender;
    }
    @JsonIgnore
    public void setSender(GetEmailInfoResDSender sender) {
        Sender = sender;
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
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
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
    public Date getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public Date getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }
}
