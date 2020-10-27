package pnc.mesadmin.dto.GetAllSpecInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:工序管理列表返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class GetAllSpecInfoResD implements Serializable{

    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }
    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }
    @JsonIgnore
    public String getVersion() {
        return Version;
    }
    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public int getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
    }

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

}
