package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDDes implements Serializable {
    @JsonProperty("DesContent")
    private String DesContent;
    @JsonIgnore
    public String getDesContent() {
        return DesContent;
    }
    @JsonIgnore
    public void setDesContent(String desContent) {
        DesContent = desContent;
    }
}
