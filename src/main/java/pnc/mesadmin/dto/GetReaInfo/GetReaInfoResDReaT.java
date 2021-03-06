package pnc.mesadmin.dto.GetReaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：根据传过来的rReaRd,获取资源信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class GetReaInfoResDReaT implements Serializable{

    @JsonProperty("ReaTID")
    private String ReaTID;
    @JsonProperty("ReaTDes")
    private  String ReaTDes;

    @JsonIgnore
    public String getReaTID() {
        return ReaTID;
    }
    @JsonIgnore
    public void setReaTID(String reaTID) {
        ReaTID = reaTID;
    }
    @JsonIgnore
    public String getReaTDes() {
        return ReaTDes;
    }
    @JsonIgnore
    public void setReaTDes(String reaTDes) {
        ReaTDes = reaTDes;
    }

}
