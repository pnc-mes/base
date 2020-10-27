package pnc.mesadmin.dto.GetDVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集版本详细信息DTO
 * 创建人：赵超
 * 创建时间：2017-5-25
 * 修改人：
 * 修改时间：
 */
public class GetDVInfoReqBD00 implements Serializable{

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }

    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }
}
