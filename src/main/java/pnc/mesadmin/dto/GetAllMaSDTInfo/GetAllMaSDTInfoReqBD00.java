package pnc.mesadmin.dto.GetAllMaSDTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取物料库存明细信息DTO业务请求数据实体类ReqBD00
 * 创建人：刘福志
 * 创建时间：2017-06-16
 * 修改人：
 * 修改时间：
 */
public class GetAllMaSDTInfoReqBD00 implements Serializable{

    @JsonProperty("InsRd")
    private int InsRd;

    @JsonIgnore
    public int getInsRd() {
        return InsRd;
    }

    @JsonIgnore
    public void setInsRd(int insRd) {
        InsRd = insRd;
    }
}
