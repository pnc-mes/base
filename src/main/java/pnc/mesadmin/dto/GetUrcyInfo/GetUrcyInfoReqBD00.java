package pnc.mesadmin.dto.GetUrcyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
public class GetUrcyInfoReqBD00 implements Serializable {

    @JsonProperty("UrcyRd")
    private int UrcyRd;

    @JsonIgnore
    public int getUrcyRd() {
        return UrcyRd;
    }

    @JsonIgnore
    public void setUrcyRd(int urcyRd) {
        UrcyRd = urcyRd;
    }
}
