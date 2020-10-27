package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪DTO返回业务数据实体类DoDCInfo
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class GetOTInfoResBDDoDC implements Serializable{

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("DevName")
    private String DevName;

    @JsonProperty("DCContent")
    private String DCContent;

    @JsonProperty("AftDCContent")
    private String AftDCContent;


    @JsonProperty("Optor")
    private String Optor;

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getDevName() {
        return DevName;
    }

    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }

    @JsonIgnore
    public String getDCContent() {
        return DCContent;
    }

    @JsonIgnore
    public void setDCContent(String DCContent) {
        this.DCContent = DCContent;
    }

    @JsonIgnore
    public String getOptor() {
        return Optor;
    }

    @JsonIgnore
    public void setOptor(String optor) {
        Optor = optor;
    }
    @JsonIgnore
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }
    @JsonIgnore
    public String getAftDCContent() {
        return AftDCContent;
    }
    @JsonIgnore
    public void setAftDCContent(String aftDCContent) {
        AftDCContent = aftDCContent;
    }
}
