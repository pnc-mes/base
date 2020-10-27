package pnc.mesadmin.dto.GetExpandFieldInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/11 18:22
 * @Description:
 */
public class GetExpandFieldInfoReq00 {
    @JsonProperty("ExpandType")
    private String ExpandType;


    @JsonIgnore

    public String getExpandType() {
        return ExpandType;
    }
    @JsonIgnore
    public void setExpandType(String expandType) {
        ExpandType = expandType;
    }
}
