package pnc.mesadmin.dto.GetAllStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetAllStoreInfoRes 
 * @Description: TODO 仓库列表响应消息体
 * @author: linyichun
 * @date: 2017-6-8 下午6:08:32
 */
public class GetAllStoreInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllStoreInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllStoreInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllStoreInfoResB body) {
        Body = body;
    }
}
