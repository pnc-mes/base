package pnc.mesadmin.dto.GetAllProdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/18.
 */
public class GetAllProdInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllProdInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllProdInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllProdInfoResB body) {
        Body = body;
    }
}
