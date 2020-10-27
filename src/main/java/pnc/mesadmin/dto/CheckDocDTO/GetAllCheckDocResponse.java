package pnc.mesadmin.dto.CheckDocDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class GetAllCheckDocResponse {
    @JsonProperty(value = "CDOCRd")
    private Integer CDOCRd;
    @JsonProperty(value = "CheckDocName")
    private String CheckDocName;

    @JsonIgnore
    public Integer getCDOCRd() {
        return CDOCRd;
    }

    @JsonIgnore
    public void setCDOCRd(Integer CDOCRd) {
        this.CDOCRd = CDOCRd;
    }

    @JsonIgnore
    public String getCheckDocName() {
        return CheckDocName;
    }

    @JsonIgnore
    public void setCheckDocName(String checkDocName) {
        CheckDocName = checkDocName;
    }
}
