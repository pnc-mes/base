package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料工艺流程工序信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public class GetMWFInfoReqBD00 implements Serializable{

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("WFVerRd")
    private int WFVerRd;

    @JsonIgnore
    public int getWFVerRd() {
        return WFVerRd;
    }

    @JsonIgnore
    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
    }

    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int MaRd) {
        this.MaRd = MaRd;
    }
}
