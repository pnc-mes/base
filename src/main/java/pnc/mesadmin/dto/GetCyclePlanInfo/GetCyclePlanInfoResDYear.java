package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDYear implements Serializable {
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
