package pnc.mesadmin.dto.GetPdfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * Created by xfxi on 2017/5/26.
 */
public class GetPdfInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPdfInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPdfInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPdfInfoResB body) {
        Body = body;
    }
}
