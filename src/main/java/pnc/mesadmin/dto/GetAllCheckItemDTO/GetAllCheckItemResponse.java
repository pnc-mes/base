package pnc.mesadmin.dto.GetAllCheckItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class GetAllCheckItemResponse {
    @JsonProperty(value = "CIRd")
    private Integer CIRd;
    @JsonProperty(value = "CheckItemCode")
    private String CheckItemCode;
    @JsonProperty(value = "CheckItemName")
    private String CheckItemName;

    @JsonIgnore
    public Integer getCIRd() {
        return CIRd;
    }

    @JsonIgnore
    public void setCIRd(Integer CIRd) {
        this.CIRd = CIRd;
    }

    @JsonIgnore
    public String getCheckItemCode() {
        return CheckItemCode;
    }

    @JsonIgnore
    public void setCheckItemCode(String checkItemCode) {
        CheckItemCode = checkItemCode;
    }

    @JsonIgnore
    public String getCheckItemName() {
        return CheckItemName;
    }

    @JsonIgnore
    public void setCheckItemName(String checkItemName) {
        CheckItemName = checkItemName;
    }
}
