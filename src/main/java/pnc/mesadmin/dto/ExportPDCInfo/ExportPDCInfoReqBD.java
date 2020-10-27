package pnc.mesadmin.dto.ExportPDCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导出盘点单信息DTO请求业务数据实体类
 * 创建人：王怀龙
 * 创建时间：2017-9-18
 * 修改人：
 * 修改时间：
 */
public class ExportPDCInfoReqBD implements Serializable{

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
