package pnc.mesadmin.dto.GetAllCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:11
 * @Description:
 */
public class GetAllCDataInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCDataInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllCDataInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllCDataInfoResB body) {
        Body = body;
    }
}
