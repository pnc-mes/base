package pnc.mesadmin.dto.GetAllLinefo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;

import java.io.Serializable;

/**
 *
 * @ClassName: GetAllLInfoRes
 * @Description: TODO 库位列表响应消息体
 * @author: haozan
 * @date: 2018-6-7
 */
public class GetAllLinefoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllLinefoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllLinefoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllLinefoResB body) {
        Body = body;
    }
}

