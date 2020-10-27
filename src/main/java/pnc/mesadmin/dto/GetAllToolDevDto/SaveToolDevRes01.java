package pnc.mesadmin.dto.GetAllToolDevDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveToolDevRes01 implements Serializable {
    @JsonProperty("ToolRd")
    private Integer ToolRd;

    @JsonIgnore
    public Integer getToolRd() {
        return ToolRd;
    }

    @JsonIgnore
    public void setToolRd(Integer toolRd) {
        ToolRd = toolRd;
    }
}
