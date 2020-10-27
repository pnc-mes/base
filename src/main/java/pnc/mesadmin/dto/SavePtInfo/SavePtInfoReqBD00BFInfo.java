package pnc.mesadmin.dto.SavePtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/31.
 */
public class SavePtInfoReqBD00BFInfo implements Serializable {

    @JsonProperty("BFCode")
    private String BFCode;

    @JsonProperty("BFName")
    private String BFName;

    @JsonProperty("BFSource")
    private String BFSource;

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

    @JsonIgnore
    public String getBFSource() {
        return BFSource;
    }

    @JsonIgnore
    public void setBFSource(String BFSource) {
        this.BFSource = BFSource;
    }
}
