package pnc.mesadmin.dto.GetAllProdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/10/18.
 */
public class GetAllProdInfoResD implements Serializable{

    @JsonProperty("ProdDate")
    private String ProdDate;

    @JsonProperty("SpecPDInfo")
    private List<GetAllProdInfoResDSpecPDInfo> SpecPDInfo;

    @JsonIgnore
    public String getProdDate() {
        return ProdDate;
    }
    @JsonIgnore
    public void setProdDate(String prodDate) {
        ProdDate = prodDate;
    }
    @JsonIgnore
    public List<GetAllProdInfoResDSpecPDInfo> getSpecPDInfo() {
        return SpecPDInfo;
    }
    @JsonIgnore
    public void setSpecPDInfo(List<GetAllProdInfoResDSpecPDInfo> specPDInfo) {
        SpecPDInfo = specPDInfo;
    }
}
