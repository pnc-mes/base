package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetQCMInfoResQCMaInfo implements Serializable {
    @JsonProperty("QCMaRd")
    private Integer QCMaRd;
    @JsonProperty("BarType")
    private String BarType;
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("PowerGear")
    private String PowerGear;
    @JsonProperty("ColorGear")
    private String ColorGear;
    @JsonProperty("ELGear")
    private String ELGear;
    @JsonProperty("GradeName")
    private String GradeName;
    @JsonProperty("ShbCode")
    private String ShbCode;
    @JsonProperty("MixedBag")
    private String MixedBag;
    @JsonProperty("IVData")
    private List<GetQCMInfoResQCMaInfoIVData> IVData;
    @JsonProperty("CellData")
    private List<GetQCMInfoResQCMaInfoCellData> CellData;
    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @JsonIgnore
    public String getMixedBag() {
        return MixedBag;
    }

    @JsonIgnore
    public void setMixedBag(String mixedBag) {
        MixedBag = mixedBag;
    }

    @JsonIgnore
    public String getShbCode() {
        return ShbCode;
    }

    @JsonIgnore
    public void setShbCode(String shbCode) {
        ShbCode = shbCode;
    }

    @JsonIgnore
    public String getPowerGear() {
        return PowerGear;
    }
    @JsonIgnore
    public void setPowerGear(String powerGear) {
        PowerGear = powerGear;
    }
    @JsonIgnore
    public String getColorGear() {
        return ColorGear;
    }
    @JsonIgnore
    public void setColorGear(String colorGear) {
        ColorGear = colorGear;
    }
    @JsonIgnore
    public String getELGear() {
        return ELGear;
    }
    @JsonIgnore
    public void setELGear(String ELGear) {
        this.ELGear = ELGear;
    }
    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }
    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }



    @JsonIgnore

    public List<GetQCMInfoResQCMaInfoIVData> getIVData() {
        return IVData;
    }
    @JsonIgnore
    public void setIVData(List<GetQCMInfoResQCMaInfoIVData> IVData) {
        this.IVData = IVData;
    }
    @JsonIgnore
    public List<GetQCMInfoResQCMaInfoCellData> getCellData() {
        return CellData;
    }
    @JsonIgnore
    public void setCellData(List<GetQCMInfoResQCMaInfoCellData> cellData) {
        CellData = cellData;
    }
    @JsonIgnore
    public Integer getQCMaRd() {
        return QCMaRd;
    }
    @JsonIgnore
    public void setQCMaRd(Integer QCMaRd) {
        this.QCMaRd = QCMaRd;
    }
}
