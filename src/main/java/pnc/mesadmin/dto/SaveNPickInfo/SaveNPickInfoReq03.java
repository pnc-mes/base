
package pnc.mesadmin.dto.SaveNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：下达
 * 创建人：张亮亮
 * 创建时间：2017-01-30
 * 修改人：
 * 修改时间：
 */
public class SaveNPickInfoReq03 {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }
    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }
}
