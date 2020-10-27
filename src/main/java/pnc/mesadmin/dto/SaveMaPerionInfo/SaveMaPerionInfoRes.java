package pnc.mesadmin.dto.SaveMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/6 16:13
 * @Description:
 */
public class SaveMaPerionInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMaPerionInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMaPerionInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMaPerionInfoResB body) {
        Body = body;
    }
}
