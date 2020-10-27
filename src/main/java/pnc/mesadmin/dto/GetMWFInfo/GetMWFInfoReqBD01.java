package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料工艺流程工序信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-7-17
 * 修改人：
 * 修改时间：
 */
public class GetMWFInfoReqBD01 {
    @JsonProperty("MVerRd")
    private int MVerRd;
    @JsonProperty("WFVerRd")
    private int WFVerRd;

    public int getWFVerRd() {
        return WFVerRd;
    }

    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
    }

    @JsonIgnore
    public int getMVerRd() {
        return MVerRd;
    }

    @JsonIgnore
    public void setMVerRd(int MVerRd) {
        this.MVerRd = MVerRd;
    }
}
