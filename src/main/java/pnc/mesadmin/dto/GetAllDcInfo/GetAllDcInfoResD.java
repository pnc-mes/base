package pnc.mesadmin.dto.GetAllDcInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息DTO返回数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetAllDcInfoResD implements Serializable{

    @JsonProperty("DcRd")
    private int DcRd;

    @JsonProperty("DcName")
    private String DcName;

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("Version")
    private String Version;

    public int getDCVerRd() {
        return DCVerRd;
    }

    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public int getDcRd() {
        return DcRd;
    }

    @JsonIgnore
    public void setDcRd(int dcRd) {
        DcRd = dcRd;
    }

    @JsonIgnore
    public String getDcName() {
        return DcName;
    }

    @JsonIgnore
    public void setDcName(String dcName) {
        DcName = dcName;
    }
}
