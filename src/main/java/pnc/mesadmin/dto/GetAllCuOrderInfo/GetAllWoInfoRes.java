package pnc.mesadmin.dto.GetAllCuOrderInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllWoInfoRes {
    @JsonProperty("WoRd")
    private Integer WoRd;
    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("MaVerRd")
    private Integer MaVerRd;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaterialDes")
    private String MaterialDes;
    @JsonProperty("Num")
    private Float Num;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("JFDate")
    private String JFDate;
    @JsonProperty("SFDate")
    private String SFDate;
    @JsonProperty("Status")
    private String Status;

    public Integer getWoRd() {
        return WoRd;
    }

    public void setWoRd(Integer woRd) {
        WoRd = woRd;
    }

    public String getWoCode() {
        return WoCode;
    }

    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    public Integer getMaVerRd() {
        return MaVerRd;
    }

    public void setMaVerRd(Integer maVerRd) {
        MaVerRd = maVerRd;
    }

    public String getMaName() {
        return MaName;
    }

    public void setMaName(String maName) {
        MaName = maName;
    }

    public String getMaterialDes() {
        return MaterialDes;
    }

    public void setMaterialDes(String materialDes) {
        MaterialDes = materialDes;
    }

    public Float getNum() {
        return Num;
    }

    public void setNum(Float num) {
        Num = num;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getJFDate() {
        return JFDate;
    }

    public void setJFDate(String JFDate) {
        this.JFDate = JFDate;
    }

    public String getSFDate() {
        return SFDate;
    }

    public void setSFDate(String SFDate) {
        this.SFDate = SFDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
