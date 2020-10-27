package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDQuarter implements Serializable {
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
