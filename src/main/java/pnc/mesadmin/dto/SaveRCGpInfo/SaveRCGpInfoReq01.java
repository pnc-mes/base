package pnc.mesadmin.dto.SaveRCGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 */
public class SaveRCGpInfoReq01 implements Serializable{
    @JsonProperty("RCGRd")
    private int RCGRd;
    @JsonIgnore
    public int getRCGRd() {
        return RCGRd;
    }
    @JsonIgnore
    public void setRCGRd(int RCGRd) {
        this.RCGRd = RCGRd;
    }
}
