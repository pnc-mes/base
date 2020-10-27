package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class GetOTInfoResD implements Serializable{

    @JsonProperty("AffairInfo")
    private List<GetOTInfoResBDAffair> AffairInfo;

    @JsonProperty("DoMaInfo")
    private List<GetOTInfoResBDDoMa> DoMaInfo;

    @JsonProperty("DoDCInfo")
    private List<GetOTInfoResBDDoDC> DoDCInfo;

    @JsonProperty("DoRCInfo")
    private List<GetOTInfoResBDDoRC> DoRCInfo;

    @JsonProperty("BaseInfo")
    private GetOTInfoResBDBase BaseInfo;

    @JsonProperty("DoExcpInfo")
    private List<GetOTInfoResBDDoExcp>  DoExcpInfo;

    @JsonProperty("EndData")
    private List<GetOTInfoResBDEndData> EndData;

    @JsonIgnore
    public List<GetOTInfoResBDAffair> getAffairInfo() {
        return AffairInfo;
    }

    @JsonIgnore
    public void setAffairInfo(List<GetOTInfoResBDAffair> affairInfo) {
        AffairInfo = affairInfo;
    }

    @JsonIgnore
    public List<GetOTInfoResBDDoMa> getDoMaInfo() {
        return DoMaInfo;
    }

    @JsonIgnore
    public void setDoMaInfo(List<GetOTInfoResBDDoMa> doMaInfo) {
        DoMaInfo = doMaInfo;
    }

    @JsonIgnore
    public List<GetOTInfoResBDDoDC> getDoDCInfo() {
        return DoDCInfo;
    }

    @JsonIgnore
    public void setDoDCInfo(List<GetOTInfoResBDDoDC> doDCInfo) {
        DoDCInfo = doDCInfo;
    }

    @JsonIgnore
    public List<GetOTInfoResBDDoRC> getDoRCInfo() {
        return DoRCInfo;
    }

    @JsonIgnore
    public void setDoRCInfo(List<GetOTInfoResBDDoRC> doRCInfo) {
        DoRCInfo = doRCInfo;
    }

    @JsonIgnore
    public GetOTInfoResBDBase getBaseInfo() {
        return BaseInfo;
    }

    @JsonIgnore
    public void setBaseInfo(GetOTInfoResBDBase baseInfo) {
        BaseInfo = baseInfo;
    }
    @JsonIgnore
    public List<GetOTInfoResBDDoExcp> getDoExcpInfo() {
        return DoExcpInfo;
    }
    @JsonIgnore
    public void setDoExcpInfo(List<GetOTInfoResBDDoExcp> doExcpInfo) {
        DoExcpInfo = doExcpInfo;
    }
    @JsonIgnore
    public List<GetOTInfoResBDEndData> getEndData() {
        return EndData;
    }
    @JsonIgnore
    public void setEndData(List<GetOTInfoResBDEndData> endData) {
        EndData = endData;
    }
}
