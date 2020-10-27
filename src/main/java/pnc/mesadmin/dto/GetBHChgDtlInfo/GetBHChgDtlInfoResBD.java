package pnc.mesadmin.dto.GetBHChgDtlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetBHChgDtlInfoResBD {

    @JsonProperty("OptType")
    private String OptType;
    @JsonProperty("QChg")
    private GetBHChgDtlInfoResBDQChg QChg;
    @JsonProperty("HChg")
    private GetBHChgDtlInfoResBDHChg HChg;

    @JsonIgnore
    public String getOptType() {
        return OptType;
    }
    @JsonIgnore
    public void setOptType(String optType) {
        OptType = optType;
    }
    @JsonIgnore
    public GetBHChgDtlInfoResBDQChg getQChg() {
        return QChg;
    }
    @JsonIgnore
    public void setQChg(GetBHChgDtlInfoResBDQChg QChg) {
        this.QChg = QChg;
    }
    @JsonIgnore
    public GetBHChgDtlInfoResBDHChg getHChg() {
        return HChg;
    }
    @JsonIgnore
    public void setHChg(GetBHChgDtlInfoResBDHChg HChg) {
        this.HChg = HChg;
    }
}
