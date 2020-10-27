package pnc.mesadmin.dto.SaveLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: SaveLInfoRes 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:27:19
 */
public class SaveLInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status; //状态

    @JsonProperty("Body")
    private SaveLInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveLInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveLInfoResB body) {
        Body = body;
    }
}
