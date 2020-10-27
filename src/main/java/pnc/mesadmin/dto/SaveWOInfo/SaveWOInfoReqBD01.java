package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：删除工单信息DTO
 * 创建人：张亮亮
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SaveWOInfoReqBD01 implements Serializable{

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }
}
