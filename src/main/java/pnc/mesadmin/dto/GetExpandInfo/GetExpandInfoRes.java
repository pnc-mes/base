package pnc.mesadmin.dto.GetExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 17:31
 * @Description:
 */
public class GetExpandInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetExpandInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetExpandInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetExpandInfoResB body) {
        Body = body;
    }
}
