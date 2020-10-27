package pnc.mesadmin.dto.GetGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/5.
 */
public class GetGCInfoResDCodeRule implements Serializable {
    @JsonProperty("CodeType")
    private String CodeType;

    @JsonProperty("Prefix")
    private String Prefix;

    @JsonProperty("DtFormat")
    private String DtFormat;

    @JsonProperty("Suffix")
    private String Suffix;

    @JsonProperty("SNLength")
    private int SNLength;

    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public String getCodeType() {
        return CodeType;
    }

    @JsonIgnore
    public void setCodeType(String codeType) {
        CodeType = codeType;
    }

    @JsonIgnore
    public String getPrefix() {
        return Prefix;
    }

    @JsonIgnore
    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    @JsonIgnore
    public String getDtFormat() {
        return DtFormat;
    }

    @JsonIgnore
    public void setDtFormat(String dtFormat) {
        DtFormat = dtFormat;
    }

    @JsonIgnore
    public String getSuffix() {
        return Suffix;
    }

    @JsonIgnore
    public void setSuffix(String suffix) {
        Suffix = suffix;
    }

    @JsonIgnore
    public int getSNLength() {
        return SNLength;
    }

    @JsonIgnore
    public void setSNLength(int SNLength) {
        this.SNLength = SNLength;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}
