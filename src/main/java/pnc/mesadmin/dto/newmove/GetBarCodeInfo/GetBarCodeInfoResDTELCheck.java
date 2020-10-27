package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetBarCodeInfoResDTELCheck {
    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("BVBadInfo")
    private List<GetBarCodeInfoResDTBVBad> BVBadInfo;

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public List<GetBarCodeInfoResDTBVBad> getBVBadInfo() {
        return BVBadInfo;
    }

    @JsonIgnore
    public void setBVBadInfo(List<GetBarCodeInfoResDTBVBad> BVBadInfo) {
        this.BVBadInfo = BVBadInfo;
    }
}
