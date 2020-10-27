package pnc.mesadmin.dto.GetExpandFieldInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/11 18:22
 * @Description:
 */
public class GetExpandFieldInfoResD {
    @JsonProperty("FieldName")
    private String FieldName;
    @JsonIgnore
    public String getFieldName() {
        return FieldName;
    }
    @JsonIgnore
    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }
}
