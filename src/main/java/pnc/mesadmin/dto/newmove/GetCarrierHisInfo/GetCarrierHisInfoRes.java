package pnc.mesadmin.dto.newmove.GetCarrierHisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018-10-24 10:02:21
 * @Description:
 */
public class GetCarrierHisInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCarrierHisInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCarrierHisInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCarrierHisInfoResB body) {
        Body = body;
    }
}
