package pnc.mesadmin.dto.SaveImportInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/2.
 */
public class SaveImportInfoReqBD00 implements Serializable {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("LCode")
    private String LCode;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("Num")
    private Float Num;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    @JsonIgnore
    public String getLCode() {
        return LCode;
    }

    @JsonIgnore
    public void setLCode(String LCode) {
        this.LCode = LCode;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
    }
}
