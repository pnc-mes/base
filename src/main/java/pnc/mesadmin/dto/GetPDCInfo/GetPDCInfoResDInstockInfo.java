package pnc.mesadmin.dto.GetPDCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点差异信息DTO返回业务数据实体类data InstockInfo
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public class GetPDCInfoResDInstockInfo implements Serializable{

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("LName")
    private String LName;

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
    public String getMaDes() {
        return MaDes;
    }

    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
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
    public String getLName() {
        return LName;
    }

    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
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
