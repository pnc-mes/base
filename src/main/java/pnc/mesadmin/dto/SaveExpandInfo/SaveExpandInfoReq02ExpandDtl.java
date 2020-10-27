package pnc.mesadmin.dto.SaveExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 16:11
 * @Description:
 */
public class SaveExpandInfoReq02ExpandDtl implements Serializable {
    @JsonProperty("FieldName")
    private String FieldName;

    @JsonProperty("ExpandDtlRd")
    private String ExpandDtlRd;

    @JsonProperty("FiledType")
    private String FiledType;

    @JsonProperty("DisplayName")
    private String DisplayName;

    @JsonProperty("CusData")
    private String CusData;
    @JsonIgnore
    public String getExpandDtlRd() {
        return ExpandDtlRd;
    }
    @JsonIgnore
    public void setExpandDtlRd(String expandDtlRd) {
        ExpandDtlRd = expandDtlRd;
    }

    @JsonIgnore
    public String getFieldName() {
        return FieldName;
    }
    @JsonIgnore
    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }
    @JsonIgnore
    public String getFiledType() {
        return FiledType;
    }
    @JsonIgnore
    public void setFiledType(String filedType) {
        FiledType = filedType;
    }
    @JsonIgnore
    public String getDisplayName() {
        return DisplayName;
    }
    @JsonIgnore
    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
    @JsonIgnore
    public String getCusData() {
        return CusData;
    }
    @JsonIgnore
    public void setCusData(String cusData) {
        CusData = cusData;
    }
}
