package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/28.
 */
public class SaveDevPropertyInfo {
    @JsonProperty("FieldName")
    private String FieldName;
    @JsonProperty("Val")
    private String Val;

    @JsonIgnore
    public String getFieldName() {
        return FieldName;
    }
    @JsonIgnore
    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }
    @JsonIgnore
    public String getVal() {
        return Val;
    }
    @JsonIgnore
    public void setVal(String val) {
        Val = val;
    }
}
