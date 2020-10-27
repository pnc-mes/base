package pnc.mesadmin.dto.GetAllExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:40
 * @Description:
 */
public class GetAllExpandInfoRes  implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllExpandInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllExpandInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllExpandInfoResB body) {
        Body = body;
    }
}
