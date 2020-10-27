package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/4.
 */
public class GetQCMInfoResQCMaInfoIVData implements Serializable {
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("Eta")
    private String Eta;
    @JsonProperty("Isc")
    private String Isc;
    @JsonProperty("Uoc")
    private String UocEta;
    @JsonProperty("Impp")
    private String Impp;
    @JsonProperty("Umpp")
    private String Umpp;
    @JsonProperty("Pmpp")
    private String Pmpp;
    @JsonProperty("FF")
    private String FF;
    @JsonProperty("Tcell")
    private String Tcell;
    @JsonProperty("Tmonicell")
    private String Tmonicell;
    @JsonProperty("Rser")
    private String Rser;
    @JsonProperty("Rshunt")
    private String Rshunt;
    @JsonProperty("Insol")
    private String Insol;
    @JsonIgnore
    public String getEta() {
        return Eta;
    }
    @JsonIgnore
    public void setEta(String eta) {
        Eta = eta;
    }
    @JsonIgnore
    public String getIsc() {
        return Isc;
    }
    @JsonIgnore
    public void setIsc(String isc) {
        Isc = isc;
    }
    @JsonIgnore
    public String getUocEta() {
        return UocEta;
    }
    @JsonIgnore
    public void setUocEta(String uocEta) {
        UocEta = uocEta;
    }
    @JsonIgnore
    public String getImpp() {
        return Impp;
    }
    @JsonIgnore
    public void setImpp(String impp) {
        Impp = impp;
    }
    @JsonIgnore
    public String getUmpp() {
        return Umpp;
    }
    @JsonIgnore
    public void setUmpp(String umpp) {
        Umpp = umpp;
    }
    @JsonIgnore
    public String getPmpp() {
        return Pmpp;
    }
    @JsonIgnore
    public void setPmpp(String pmpp) {
        Pmpp = pmpp;
    }
    @JsonIgnore
    public String getFF() {
        return FF;
    }
    @JsonIgnore
    public void setFF(String FF) {
        this.FF = FF;
    }
    @JsonIgnore
    public String getTcell() {
        return Tcell;
    }
    @JsonIgnore
    public void setTcell(String tcell) {
        Tcell = tcell;
    }
    @JsonIgnore
    public String getTmonicell() {
        return Tmonicell;
    }
    @JsonIgnore
    public void setTmonicell(String tmonicell) {
        Tmonicell = tmonicell;
    }
    @JsonIgnore
    public String getRser() {
        return Rser;
    }
    @JsonIgnore
    public void setRser(String rser) {
        Rser = rser;
    }
    @JsonIgnore
    public String getRshunt() {
        return Rshunt;
    }
    @JsonIgnore
    public void setRshunt(String rshunt) {
        Rshunt = rshunt;
    }
    @JsonIgnore
    public String getInsol() {
        return Insol;
    }
    @JsonIgnore
    public void setInsol(String insol) {
        Insol = insol;
    }
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}
