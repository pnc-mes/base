package pnc.mesadmin.dto.ChangePwdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/22.
 */
public class ChangePwdInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private ChangePwdInfoResBody Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public ChangePwdInfoResBody getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(ChangePwdInfoResBody body) {
        Body = body;
    }
}
