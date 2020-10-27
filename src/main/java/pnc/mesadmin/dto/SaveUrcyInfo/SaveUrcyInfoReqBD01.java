package pnc.mesadmin.dto.SaveUrcyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存紧急度代码信息DTO请求业务数据实体类BD01：删除
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
public class SaveUrcyInfoReqBD01 implements Serializable {

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
