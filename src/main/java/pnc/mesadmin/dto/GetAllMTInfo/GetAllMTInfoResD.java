package pnc.mesadmin.dto.GetAllMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-21
 * 修改人：
 * 修改时间：
 */
public class GetAllMTInfoResD implements Serializable {

    @JsonProperty("CMTInfo")
    private List<GetAllMTInfoResDCMTInfo> CMTInfo;

    @JsonIgnore
    public List<GetAllMTInfoResDCMTInfo> getCMTInfo() {
        return CMTInfo;
    }

    @JsonIgnore
    public void setCMTInfo(List<GetAllMTInfoResDCMTInfo> CMTInfo) {
        this.CMTInfo = CMTInfo;
    }
}
