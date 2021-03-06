package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/1.
 */
public class SaveMaInfoReqBD02MaPropertyInfo implements Serializable{
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

    public String getVal() {
        return Val;
    }

    public void setVal(String val) {
        Val = val;
    }
}
