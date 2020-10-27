package pnc.mesadmin.dto.GetPtBFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/31.
 */
public class GetPtBFInfoResD implements Serializable{

    @JsonProperty("BFCode")
    private String BFCode;

    @JsonProperty("BFName")
    private String BFName;

    @JsonIgnore
    public String getBFCode() {
        return BFCode;
    }

    @JsonIgnore
    public void setBFCode(String BFCode) {
        this.BFCode = BFCode;
    }

    @JsonIgnore
    public String getBFName() {
        return BFName;
    }

    @JsonIgnore
    public void setBFName(String BFName) {
        this.BFName = BFName;
    }
}
