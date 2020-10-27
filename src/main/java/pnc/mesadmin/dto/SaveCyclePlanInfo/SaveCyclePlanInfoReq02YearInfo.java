package pnc.mesadmin.dto.SaveCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCyclePlanInfoReq02YearInfo implements Serializable {
    @JsonProperty("YearContent")
    private String YearContent;
    @JsonIgnore
    public String getYearContent() {
        return YearContent;
    }
    @JsonIgnore
    public void setYearContent(String yearContent) {
        YearContent = yearContent;
    }
}
