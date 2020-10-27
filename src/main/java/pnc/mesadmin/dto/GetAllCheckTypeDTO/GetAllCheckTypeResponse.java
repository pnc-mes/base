package pnc.mesadmin.dto.GetAllCheckTypeDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class GetAllCheckTypeResponse {
    @JsonProperty(value = "CTRd")
    private Integer CTRd;
    @JsonProperty(value = "CheckTypeCode")
    private String CheckTypeCode;
    @JsonProperty(value = "CheckTypeName")
    private String CheckTypeName;

    @JsonIgnore
    public Integer getCTRd() {
        return CTRd;
    }

    @JsonIgnore
    public void setCTRd(Integer CTRd) {
        this.CTRd = CTRd;
    }

    @JsonIgnore
    public String getCheckTypeCode() {
        return CheckTypeCode;
    }

    @JsonIgnore
    public void setCheckTypeCode(String checkTypeCode) {
        CheckTypeCode = checkTypeCode;
    }

    @JsonIgnore
    public String getCheckTypeName() {
        return CheckTypeName;
    }

    @JsonIgnore
    public void setCheckTypeName(String checkTypeName) {
        CheckTypeName = checkTypeName;
    }
}
