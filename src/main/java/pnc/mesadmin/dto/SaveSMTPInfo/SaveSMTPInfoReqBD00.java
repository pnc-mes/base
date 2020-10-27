package pnc.mesadmin.dto.SaveSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class SaveSMTPInfoReqBD00 implements Serializable{

    @JsonProperty("Ruid")
    private Integer Ruid;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("Port")
    private int Port;

    @JsonProperty("SMTPURL")
    private String SMTPURL;

    @JsonProperty("UseSSL")
    private Boolean UseSSL;

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
    public int getPort() {
        return Port;
    }
    @JsonIgnore
    public void setPort(int port) {
        Port = port;
    }
    @JsonIgnore
    public Integer getRuid() {
        return Ruid;
    }
    @JsonIgnore
    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }
}
