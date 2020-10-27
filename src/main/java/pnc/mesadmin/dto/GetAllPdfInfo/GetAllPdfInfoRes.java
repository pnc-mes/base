package pnc.mesadmin.dto.GetAllPdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/26.
 */
public class GetAllPdfInfoRes implements Serializable {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Body")
    private GetAllPdfInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public GetAllPdfInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPdfInfoResB body) {
        Body = body;
    }
}
