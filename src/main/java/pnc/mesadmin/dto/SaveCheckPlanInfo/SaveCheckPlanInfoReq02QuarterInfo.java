package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq02QuarterInfo implements Serializable {
    @JsonProperty("QuarterContent")
    private String QuarterContent;
    @JsonIgnore
    public String getQuarterContent() {
        return QuarterContent;
    }
    @JsonIgnore
    public void setQuarterContent(String quarterContent) {
        QuarterContent = quarterContent;
    }
}
