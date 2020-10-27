package pnc.mesadmin.dto.GetSysInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetSysInfoResDMResInfo implements Serializable{

    @JsonProperty("ResRd")
    private int ResRd;

    @JsonProperty("ResName")
    private String ResName;

    @JsonProperty("ResIcon")
    private String ResIcon;

    @JsonProperty("ResUrl")
    private String ResUrl;

    @JsonProperty("OpenType")
    private String OpenType;

    @JsonProperty("Pos")
    private Integer Pos;

    @JsonIgnore
    public Integer getPos() {
        return Pos;
    }

    @JsonIgnore
    public void setPos(Integer pos) {
        Pos = pos;
    }

    @JsonIgnore
    public int getResRd() {
        return ResRd;
    }

    @JsonIgnore
    public void setResRd(int resRd) {
        ResRd = resRd;
    }

    @JsonIgnore
    public String getResName() {
        return ResName;
    }

    @JsonIgnore
    public void setResName(String resName) {
        ResName = resName;
    }

    @JsonIgnore
    public String getResIcon() {
        return ResIcon;
    }

    @JsonIgnore
    public void setResIcon(String resIcon) {
        ResIcon = resIcon;
    }

    @JsonIgnore
    public String getResUrl() {
        return ResUrl;
    }

    @JsonIgnore
    public void setResUrl(String resUrl) {
        ResUrl = resUrl;
    }

    @JsonIgnore
    public String getOpenType() {
        return OpenType;
    }

    @JsonIgnore
    public void setOpenType(String openType) {
        OpenType = openType;
    }
}
