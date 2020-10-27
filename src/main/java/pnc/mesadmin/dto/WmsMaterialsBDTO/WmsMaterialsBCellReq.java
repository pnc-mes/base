package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：电池片解析DTO
 * 创建人：潘俊峰
 * 创建时间：2020-03-12
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBCellReq {
    @JsonProperty("IchCode")
    private String IchCode;

    @JsonProperty("CellDtl")
    private List<WmsMaterialsBCellRes> CellDtl;

    @JsonIgnore
    public String getIchCode() {
        return IchCode;
    }

    @JsonIgnore
    public void setIchCode(String ichCode) {
        IchCode = ichCode;
    }

    @JsonIgnore
    public List<WmsMaterialsBCellRes> getCellDtl() {
        return CellDtl;
    }

    @JsonIgnore
    public void setCellDtl(List<WmsMaterialsBCellRes> cellDtl) {
        CellDtl = cellDtl;
    }
}
