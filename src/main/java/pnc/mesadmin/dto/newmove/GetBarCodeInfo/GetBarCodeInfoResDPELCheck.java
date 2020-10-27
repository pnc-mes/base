package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetBarCodeInfoResDPELCheck {
    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("BVBadInfo")
    private List<GetBarCodeInfoResDPBVBad> BVBadInfo;

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public List<GetBarCodeInfoResDPBVBad> getBVBadInfo() {
        return BVBadInfo;
    }

    @JsonIgnore
    public void setBVBadInfo(List<GetBarCodeInfoResDPBVBad> BVBadInfo) {
        this.BVBadInfo = BVBadInfo;
    }
}
