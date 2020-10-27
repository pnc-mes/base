package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：电池片解析DTO
 * 创建人：潘俊峰
 * 创建时间：2020-03-12
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBCellRes {
    @JsonProperty("MaCode")
    private String MaCode; //物料代码

    @JsonProperty("MaName")
    private String MaName; //物料名称

    @JsonProperty("GradeName")
    private String GradeName; //等级

    @JsonProperty("MashCode")
    private String MashCode; //番号

    @JsonProperty("BatchNo")
    private String BatchNo; //批号

    @JsonProperty("Num")
    private BigDecimal Num; //数量

    @JsonProperty("CellCode")
    private String CellCode; //电池片二维码代码

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public String getMashCode() {
        return MashCode;
    }

    @JsonIgnore
    public void setMashCode(String mashCode) {
        MashCode = mashCode;
    }

    @JsonIgnore
    public String getBatchNo() {
        return BatchNo;
    }

    @JsonIgnore
    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    @JsonIgnore
    public BigDecimal getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(BigDecimal num) {
        Num = num;
    }

    @JsonIgnore
    public String getCellCode() {
        return CellCode;
    }

    @JsonIgnore
    public void setCellCode(String cellCode) {
        CellCode = cellCode;
    }
}
