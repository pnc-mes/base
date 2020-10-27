package pnc.mesadmin.dto.GetDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息DTO请求业务数据实体类Req
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDicInfoReqBD00 implements Serializable{

    @JsonProperty("DicRd")
    private String DicRd;

    @JsonIgnore
    public String getDicRd() {
        return DicRd;
    }

    @JsonIgnore
    public void setDicRd(String dicRd) {
        DicRd = dicRd;
    }

}
