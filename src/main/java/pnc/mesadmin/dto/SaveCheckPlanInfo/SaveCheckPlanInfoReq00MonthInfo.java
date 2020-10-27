package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq00MonthInfo implements Serializable {
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
