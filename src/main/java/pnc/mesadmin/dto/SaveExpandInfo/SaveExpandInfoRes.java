package pnc.mesadmin.dto.SaveExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveFaInfo.SaveFaInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 16:10
 * @Description:
 */
public class SaveExpandInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveExpandInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveExpandInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveExpandInfoResB body) {
        Body = body;
    }
}
