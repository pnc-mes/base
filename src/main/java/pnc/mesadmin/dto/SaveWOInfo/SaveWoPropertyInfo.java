package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by LENOVO on 2018/11/22.
 * haozan
 */
public class SaveWoPropertyInfo implements Serializable {
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
