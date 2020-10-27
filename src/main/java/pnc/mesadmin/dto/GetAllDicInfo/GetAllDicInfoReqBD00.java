package pnc.mesadmin.dto.GetAllDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息列表DTO业务请求数据实体类ReqBD00
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetAllDicInfoReqBD00 implements Serializable{

    @JsonProperty("LanCode")
    private String LanCode;

    @JsonIgnore
    public String getLanCode() {
        return LanCode;
    }

    @JsonIgnore
    public void setLanCode(String lanCode) {
        LanCode = lanCode;
    }
}
