package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：邮件内容列表信息Model
 * 创建人：乔帅阳
 * 创建时间：2017-6-12
 * 修改人：
 * 修改时间：
 */
public class EmailMessageInfo implements Serializable{
    private int Ruid;
    private String Guid;
    private String EmailName;
    private String Description;
    private String SMTPGd;
    private String Subject;
    private String Message;
    private String SenderGd;
    private Boolean MessageFormat;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;


    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getEmailName() {
        return EmailName;
    }

    public void setEmailName(String emailName) {
        EmailName = emailName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getMessageFormat() {
        return MessageFormat;
    }

    public void setMessageFormat(Boolean messageFormat) {
        MessageFormat = messageFormat;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getSMTPGd() {
        return SMTPGd;
    }

    public void setSMTPGd(String SMTPGd) {
        this.SMTPGd = SMTPGd;
    }

    public String getSenderGd() {
        return SenderGd;
    }

    public void setSenderGd(String senderGd) {
        SenderGd = senderGd;
    }
}
