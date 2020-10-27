package pnc.mesadmin.dto.SaveWCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：复制工作中心
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class SaveWCInfoReqBD03 implements Serializable{

    @JsonProperty("WCRd")
    private int WCRd;

    @JsonIgnore
    public int getWCRd() {
        return WCRd;
    }

    @JsonIgnore
    public void setWCRd(int WCRd) {
        this.WCRd = WCRd;
    }
}
