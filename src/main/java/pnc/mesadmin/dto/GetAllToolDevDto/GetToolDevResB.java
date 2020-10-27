package pnc.mesadmin.dto.GetAllToolDevDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetToolDevResB implements Serializable {

    @JsonProperty("DevRd")
    private Integer DevRd;
    @JsonProperty("DevName")
    private String DevName;
    @JsonProperty("ToolRd")
    private Integer ToolRd;
    @JsonProperty("ToolName")
    private String ToolName;
    @JsonProperty("VenderSN")
    private String VenderSN;
    @JsonProperty("UsrNum")
    private Integer UsrNum;

    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public String getDevName() {
        return DevName;
    }

    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }

    @JsonIgnore
    public Integer getToolRd() {
        return ToolRd;
    }

    @JsonIgnore
    public void setToolRd(Integer toolRd) {
        ToolRd = toolRd;
    }

    @JsonIgnore
    public String getToolName() {
        return ToolName;
    }

    @JsonIgnore
    public void setToolName(String toolName) {
        ToolName = toolName;
    }

    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }

    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }

    @JsonIgnore
    public Integer getUsrNum() {
        return UsrNum;
    }

    @JsonIgnore
    public void setUsrNum(Integer usrNum) {
        UsrNum = usrNum;
    }
}
