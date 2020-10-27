package pnc.mesadmin.dto.GetPdBOMDIInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPdBOMDIInfoResD implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("BomMaRd")
    private int BomMaRd;

    @JsonProperty("List")
    private List<GetPdBOMDIInfoResDList> List;

    @JsonProperty("Details")
    private List<GetPdBOMDIInfoResDDetails> Details;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public int getBomMaRd() {
        return BomMaRd;
    }

    @JsonIgnore
    public void setBomMaRd(int bomMaRd) {
        BomMaRd = bomMaRd;
    }

    @JsonIgnore
    public java.util.List<GetPdBOMDIInfoResDList> getList() {
        return List;
    }

    @JsonIgnore
    public void setList(java.util.List<GetPdBOMDIInfoResDList> list) {
        List = list;
    }

    @JsonIgnore
    public java.util.List<GetPdBOMDIInfoResDDetails> getDetails() {
        return Details;
    }

    @JsonIgnore
    public void setDetails(java.util.List<GetPdBOMDIInfoResDDetails> details) {
        Details = details;
    }
}
