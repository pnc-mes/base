package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoResD implements Serializable{

    @JsonProperty("MaInfo")
    private GetCMWFInfoResDMaInfo MaInfo;

    @JsonProperty("ReMaInfo")
    private List<GetCMWFInfoResDReMaInfo> ReMaInfo;

    @JsonProperty("WFInfo")
    private List<GetCMWFInfoResDWFInfo> WFInfo;

    @JsonIgnore
    public GetCMWFInfoResDMaInfo getMaInfo() {
        return MaInfo;
    }

    @JsonIgnore
    public void setMaInfo(GetCMWFInfoResDMaInfo maInfo) {
        MaInfo = maInfo;
    }

    @JsonIgnore
    public List<GetCMWFInfoResDReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<GetCMWFInfoResDReMaInfo> reMaInfo) {
        ReMaInfo = reMaInfo;
    }

    @JsonIgnore
    public List<GetCMWFInfoResDWFInfo> getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(List<GetCMWFInfoResDWFInfo> WFInfo) {
        this.WFInfo = WFInfo;
    }
}
