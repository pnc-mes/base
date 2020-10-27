package pnc.mesadmin.dto.GetSNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：根据SNRd 查询序号管理信息、
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public class GetSNInfoReqBD00 implements Serializable{

    @JsonProperty("SNRd")
    private int SNRd;

    @JsonIgnore
    public int getSNRd() {
        return SNRd;
    }

    @JsonIgnore
    public void setSNRd(int SNRd) {
        this.SNRd = SNRd;
    }
}
