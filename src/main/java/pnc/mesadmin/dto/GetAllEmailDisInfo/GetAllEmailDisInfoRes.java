package pnc.mesadmin.dto.GetAllEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:17
 * @Description:
 */
public class GetAllEmailDisInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllEmailDisInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllEmailDisInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllEmailDisInfoResB body) {
        Body = body;
    }
}
