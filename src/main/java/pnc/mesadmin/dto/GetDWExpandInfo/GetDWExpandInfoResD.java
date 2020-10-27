package pnc.mesadmin.dto.GetDWExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoResDExpandDtl;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: 郝赞
 * @Date: 2018/9/5 17:32
 * @Description:
 */
public class GetDWExpandInfoResD implements Serializable {


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
    public List<GetDWExpandInfoResD.Option> getOption() {
        return Option;
    }

    @JsonIgnore
    public void setOption(List<GetDWExpandInfoResD.Option> option) {
        Option = option;
    }

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
}
