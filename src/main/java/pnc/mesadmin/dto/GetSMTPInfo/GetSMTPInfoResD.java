package pnc.mesadmin.dto.GetSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class GetSMTPInfoResD implements Serializable{
    @JsonProperty("SMTPRd")
    private int SMTPRd;

    @JsonProperty("SMTPName")
    private String SMTPName;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("Port")
    private int Port;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("SMTPURL")
    private String SMTPURL;

    @JsonProperty("UseSSL")
    private Boolean UseSSL;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String  CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;
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
    @JsonIgnore
    public String getUserName() {
        return UserName;
    }
    @JsonIgnore
    public void setUserName(String userName) {
        UserName = userName;
    }
    @JsonIgnore
    public String getPassword() {
        return Password;
    }
    @JsonIgnore
    public void setPassword(String password) {
        Password = password;
    }
    @JsonIgnore
    public String getSMTPURL() {
        return SMTPURL;
    }
    @JsonIgnore
    public void setSMTPURL(String SMTPURL) {
        this.SMTPURL = SMTPURL;
    }
    @JsonIgnore
    public Boolean getUseSSL() {
        return UseSSL;
    }
    @JsonIgnore
    public void setUseSSL(Boolean useSSL) {
        UseSSL = useSSL;
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
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
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
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public int getPort() {
        return Port;
    }
    @JsonIgnore
    public void setPort(int port) {
        Port = port;
    }
}
