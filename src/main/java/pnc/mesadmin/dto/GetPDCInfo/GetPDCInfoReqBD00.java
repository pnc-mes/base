package pnc.mesadmin.dto.GetPDCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点差异信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public class GetPDCInfoReqBD00 implements Serializable{

    @JsonProperty("PDRd")
    private int PDRd;

    @JsonIgnore
    public int getPDRd() {
        return PDRd;
    }

    @JsonIgnore
    public void setPDRd(int PDRd) {
        this.PDRd = PDRd;
    }
}
