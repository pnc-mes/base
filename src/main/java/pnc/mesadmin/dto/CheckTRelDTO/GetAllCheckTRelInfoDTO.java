package pnc.mesadmin.dto.CheckTRelDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-25
 **/
public class GetAllCheckTRelInfoDTO {
    @JsonProperty("CTRelRd")
    private Integer CTRelRd;
    @JsonProperty("TempRelName")
    private String TempRelName;

    @JsonIgnore
    public Integer getCTRelRd() {
        return CTRelRd;
    }

    @JsonIgnore
    public void setCTRelRd(Integer CTRelRd) {
        this.CTRelRd = CTRelRd;
    }

    @JsonIgnore
    public String getTempRelName() {
        return TempRelName;
    }

    @JsonIgnore
    public void setTempRelName(String tempRelName) {
        TempRelName = tempRelName;
    }
}
