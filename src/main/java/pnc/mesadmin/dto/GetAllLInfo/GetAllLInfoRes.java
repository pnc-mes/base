package pnc.mesadmin.dto.GetAllLInfo;

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
public class GetAllLInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllLInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllLInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllLInfoResB body) {
        Body = body;
    }
}
