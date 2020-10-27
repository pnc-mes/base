package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetBarCodeInfoResDYHCheck {
    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("BVBadInfo")
    private List<GetBarCodeInfoResDYBVBad> BVBadInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public List<GetBarCodeInfoResDYBVBad> getBVBadInfo() {
        return BVBadInfo;
    }

    @JsonIgnore
    public void setBVBadInfo(List<GetBarCodeInfoResDYBVBad> BVBadInfo) {
        this.BVBadInfo = BVBadInfo;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
