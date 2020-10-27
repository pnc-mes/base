package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/15.
 */
public class GetOTInfoResBDDoRC {
    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("DevName")
    private String DevName;

    @JsonProperty("RCContent")
    private String RCContent;

    @JsonProperty("Optor")
    private String Optor;
    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
    @JsonIgnore
    public String getDevName() {
        return DevName;
    }
    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }
    @JsonIgnore
    public String getRCContent() {
        return RCContent;
    }
    @JsonIgnore
    public void setRCContent(String RCContent) {
        this.RCContent = RCContent;
    }
    @JsonIgnore
    public String getOptor() {
        return Optor;
    }
    @JsonIgnore
    public void setOptor(String optor) {
        Optor = optor;
    }
}
