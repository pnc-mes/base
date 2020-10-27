package pnc.mesadmin.dto.SaveDevMaTainDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveDevMaTainRes01 implements Serializable {
    @JsonProperty("DevRd")
    private Integer DevRd;

    @JsonProperty("SPartRd")
    private Integer SPartRd;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("Remark")
    private String Remark;


    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public Integer getSPartRd() {
        return SPartRd;
    }

    @JsonIgnore
    public void setSPartRd(Integer SPartRd) {
        this.SPartRd = SPartRd;
    }

    @JsonIgnore
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
