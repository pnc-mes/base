package pnc.mesadmin.dto.SaveCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 11:19
 * @Description:
 */
public class SaveCDataInfoReq00CusDataDtl implements Serializable {
    @JsonProperty("DisplayName")
    private String DisplayName;

    @JsonProperty("Val")
    private String Val;
    @JsonIgnore
    public String getDisplayName() {
        return DisplayName;
    }
    @JsonIgnore
    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
    @JsonIgnore
    public String getVal() {
        return Val;
    }
    @JsonIgnore
    public void setVal(String val) {
        Val = val;
    }
}
