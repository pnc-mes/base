package pnc.mesadmin.dto.SavePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存打印机信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class SavePrInfoReqBD00 implements Serializable{

    @JsonProperty("PrName")
    private String PrName;

    @JsonProperty("PrSer")
    private String PrSer;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getPrName() {
        return PrName;
    }

    @JsonIgnore
    public void setPrName(String prName) {
        PrName = prName;
    }

    @JsonIgnore
    public String getPrSer() {
        return PrSer;
    }

    @JsonIgnore
    public void setPrSer(String prSer) {
        PrSer = prSer;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
