package pnc.mesadmin.dto.SaveDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存字典信息DTO请求业务数据实体类BD01
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveDicInfoReqBD01 implements Serializable{

    @JsonProperty("DicRd")
    private int DicRd;
    @JsonIgnore
    public void setDicRd(int dicRd) {
        DicRd = dicRd;
    }
    @JsonIgnore
    public int getDicRd() {
        return DicRd;
    }
}
