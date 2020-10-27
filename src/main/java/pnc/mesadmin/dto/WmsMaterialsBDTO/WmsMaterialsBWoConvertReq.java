package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：组件工单转换DTO
 * 创建人：潘俊峰
 * 创建时间：2020-03-21
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBWoConvertReq {
    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("WoConvertDtl")
    private List<WoConvertDtl> WoConvertDtl;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public List<WmsMaterialsBWoConvertReq.WoConvertDtl> getWoConvertDtl() {
        return WoConvertDtl;
    }

    @JsonIgnore
    public void setWoConvertDtl(List<WmsMaterialsBWoConvertReq.WoConvertDtl> woConvertDtl) {
        WoConvertDtl = woConvertDtl;
    }

    public static class WoConvertDtl {
        @JsonProperty("Batch")
        private String Batch;

        @JsonProperty("WoCode")
        private String WoCode;

        @JsonIgnore
        public String getBatch() {
            return Batch;
        }

        @JsonIgnore
        public void setBatch(String batch) {
            Batch = batch;
        }

        @JsonIgnore
        public String getWoCode() {
            return WoCode;
        }

        @JsonIgnore
        public void setWoCode(String woCode) {
            WoCode = woCode;
        }
    }
}
