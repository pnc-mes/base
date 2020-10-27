package pnc.mesadmin.dto.SaveCusInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存客户信息DTO请求业务数据实体类BD03：复制
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class SaveCusInfoReqBD03 implements Serializable{

    @JsonProperty("CusRd")
    private int CusRd;

    @JsonIgnore
    public int getCusRd() {
        return CusRd;
    }

    @JsonIgnore
    public void setCusRd(int cusRd) {
        CusRd = cusRd;
    }
}
