package pnc.mesadmin.dto.GetDVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集版本列表信息DTO，返回版本列表的业务实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDVTreeInfoResD implements Serializable{

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }

    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }
}
