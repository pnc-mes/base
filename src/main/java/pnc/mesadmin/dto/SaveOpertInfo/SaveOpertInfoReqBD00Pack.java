package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业信息DTO请求业务数据实体类SaveOpertInfoResBD00Pack
 * 创建人：王怀龙
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class SaveOpertInfoReqBD00Pack implements Serializable{
    @JsonProperty("PackOpt")
    private String PackOpt;
    @JsonProperty("CkWeight")
    private String CkWeight;
    @JsonProperty("PackType")
    private String PackType;

    @JsonIgnore
    public String getPackOpt() {
        return PackOpt;
    }
    @JsonIgnore
    public void setPackOpt(String packOpt) {
        PackOpt = packOpt;
    }
    @JsonIgnore
    public String getCkWeight() {
        return CkWeight;
    }
    @JsonIgnore
    public void setCkWeight(String ckWeight) {
        CkWeight = ckWeight;
    }
    @JsonIgnore
    public String getPackType() {
        return PackType;
    }
    @JsonIgnore
    public void setPackType(String packType) {
        PackType = packType;
    }
}
