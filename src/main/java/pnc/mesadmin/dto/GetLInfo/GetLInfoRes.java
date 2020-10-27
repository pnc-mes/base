package pnc.mesadmin.dto.GetLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetAllLInfoRes 
 * @Description: TODO 库位列表响应消息体
 * @author: linyichun
 * @date: 2017-6-8 下午6:08:32
 */
public class GetLInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetLInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetLInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetLInfoResB body) {
        Body = body;
    }
}
