package pnc.mesadmin.dto.GetDWExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: 郝赞
 * @Date: 2018/9/5 17:32
 * @Description:
 */
public class GetDWExpandInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetDWExpandInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetDWExpandInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetDWExpandInfoResB body) {
        Body = body;
    }
}
