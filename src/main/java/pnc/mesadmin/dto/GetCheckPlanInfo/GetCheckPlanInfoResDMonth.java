package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCheckPlanInfoResDMonth implements Serializable {
    @JsonProperty("MonthContent")
    private int MonthContent;
    @JsonIgnore
    public int getMonthContent() {
        return MonthContent;
    }
    @JsonIgnore
    public void setMonthContent(int monthContent) {
        MonthContent = monthContent;
    }
}
