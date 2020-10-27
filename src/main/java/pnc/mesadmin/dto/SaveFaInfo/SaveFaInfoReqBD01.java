package pnc.mesadmin.dto.SaveFaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：删除工厂信息dto
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class SaveFaInfoReqBD01 implements Serializable{

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonIgnore
    public int getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }
}
