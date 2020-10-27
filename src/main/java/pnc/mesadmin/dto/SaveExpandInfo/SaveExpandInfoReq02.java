package pnc.mesadmin.dto.SaveExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 16:10
 * @Description:
 */
public class SaveExpandInfoReq02 implements Serializable {
    @JsonProperty("ExpandType")
    private String ExpandType;

    @JsonProperty("ExpandName")
    private String ExpandName;

    @JsonProperty("IsSettleObj")
    private String IsSettleObj;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonProperty("ExpandDtl")
    private List<SaveExpandInfoReq02ExpandDtl> ExpandDtl;

    @JsonIgnore
    public String getExpandType() {
        return ExpandType;
    }
    @JsonIgnore
    public void setExpandType(String expandType) {
        ExpandType = expandType;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public List<SaveExpandInfoReq02ExpandDtl> getExpandDtl() {
        return ExpandDtl;
    }
    @JsonIgnore
    public void setExpandDtl(List<SaveExpandInfoReq02ExpandDtl> expandDtl) {
        ExpandDtl = expandDtl;
    }
    @JsonIgnore
    public int getExpandRd() {
        return ExpandRd;
    }
    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }
    @JsonIgnore
    public String getExpandName() {
        return ExpandName;
    }
    @JsonIgnore
    public void setExpandName(String expandName) {
        ExpandName = expandName;
    }
    @JsonIgnore
    public String getIsSettleObj() {
        return IsSettleObj;
    }
    @JsonIgnore
    public void setIsSettleObj(String isSettleObj) {
        IsSettleObj = isSettleObj;
    }
}
