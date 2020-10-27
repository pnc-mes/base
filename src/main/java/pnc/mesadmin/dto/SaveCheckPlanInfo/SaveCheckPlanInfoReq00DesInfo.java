package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq00DesInfo implements Serializable {
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
