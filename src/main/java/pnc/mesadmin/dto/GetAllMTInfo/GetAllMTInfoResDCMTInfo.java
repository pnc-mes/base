package pnc.mesadmin.dto.GetAllMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-21
 * 修改人：
 * 修改时间：
 */
public class GetAllMTInfoResDCMTInfo implements Serializable {
    @JsonProperty("MTRd")
    private int MTRd;

    @JsonProperty("MTGd")
    private String MTGd;

    @JsonProperty("MTName")
    private String MTName;

    @JsonProperty("MTCode")
    private String MTCode;

    @JsonProperty("MaType")
    private String MaType;

    @JsonProperty("CMTInfo")
    private List<GetAllMTInfoResDCMTInfo> CMTInfo;

    @JsonIgnore
    public List<GetAllMTInfoResDCMTInfo> getCMTInfo() {
        return CMTInfo;
    }

    @JsonIgnore
    public void setCMTInfo(List<GetAllMTInfoResDCMTInfo> CMTInfo) {
        this.CMTInfo = CMTInfo;
    }

    @JsonIgnore
    public int getMTRd() {
        return MTRd;
    }

    @JsonIgnore
    public void setMTRd(int MTRd) {
        this.MTRd = MTRd;
    }

    @JsonIgnore
    public String getMTGd() {
        return MTGd;
    }

    @JsonIgnore
    public void setMTGd(String MTGd) {
        this.MTGd = MTGd;
    }

    @JsonIgnore
    public String getMTName() {
        return MTName;
    }

    @JsonIgnore
    public void setMTName(String MTName) {
        this.MTName = MTName;
    }

    @JsonIgnore
    public String getMTCode() {
        return MTCode;
    }

    @JsonIgnore
    public void setMTCode(String MTCode) {
        this.MTCode = MTCode;
    }

    @JsonIgnore
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }
}
