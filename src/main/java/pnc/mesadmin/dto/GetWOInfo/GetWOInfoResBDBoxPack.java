package pnc.mesadmin.dto.GetWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单列表返回的BoxPackInfo
 * 创建人：张亮亮
 * 创建时间：2017-08-22
 * 修改人：
 * 修改时间：
 */
public class GetWOInfoResBDBoxPack implements Serializable {
    @JsonProperty("PackSpRd")
    private int PackSpRd;

    @JsonProperty("PackName")
    private String PackName;

    @JsonIgnore
    public int getPackSpRd() {
        return PackSpRd;
    }
    @JsonIgnore
    public void setPackSpRd(int packSpRd) {
        PackSpRd = packSpRd;
    }
    @JsonIgnore
    public String getPackName() {
        return PackName;
    }
    @JsonIgnore
    public void setPackName(String packName) {
        PackName = packName;
    }
}
