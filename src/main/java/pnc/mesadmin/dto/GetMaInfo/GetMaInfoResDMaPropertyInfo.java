package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zll on 2018/2/2.
 */
public class GetMaInfoResDMaPropertyInfo implements Serializable{


    @JsonProperty("FieldName")
    private String FieldName;

    @JsonProperty("FiledType")
    private String FiledType;

    @JsonProperty("DisplayName")
    private String DisplayName;

    @JsonProperty("Val")
    private String Val;

    @JsonProperty("Option")
    private List<Option> Option;

    public static class Option{
        @JsonProperty("DisplayName")
        private String DisplayName;

        @JsonProperty("Val")
        private String Val;

        @JsonIgnore
        public String getDisplayName() {
            return DisplayName;
        }

        @JsonIgnore
        public void setDisplayName(String displayName) {
            DisplayName = displayName;
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
    public String getVal() {
        return Val;
    }

    @JsonIgnore
    public void setVal(String val) {
        Val = val;
    }

    @JsonIgnore
    public List<GetMaInfoResDMaPropertyInfo.Option> getOption() {
        return Option;
    }

    @JsonIgnore
    public void setOption(List<GetMaInfoResDMaPropertyInfo.Option> option) {
        Option = option;
    }
}
