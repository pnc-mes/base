package pnc.mesadmin.dto.SaveStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: SaveStoreInfoRes 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:27:19
 */
public class SaveStoreInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status; //状态

    @JsonProperty("Body")
    private SaveStoreInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveStoreInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveStoreInfoResB body) {
        Body = body;
    }
}
