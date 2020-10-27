package pnc.mesadmin.dto.GetCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:28
 * @Description:
 */
public class GetCDataInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCDataInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCDataInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCDataInfoResB body) {
        Body = body;
    }
}
