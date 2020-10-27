package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料无源批次创建DTO
 * 创建人：潘俊峰
 * 创建时间：2020-04-29
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBWAddReq {
    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("SplitBCount")
    private int SplitBCount;

    @JsonProperty("BCount")
    private float BCount;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("BatchNo")
    private String BatchNo;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("F1")
    private String F1; //供应商批号

    @JsonIgnore
    public int getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
    }

    @JsonIgnore
    public int getPrintRd() {
        return PrintRd;
    }

    @JsonIgnore
    public void setPrintRd(int printRd) {
        PrintRd = printRd;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public int getSplitBCount() {
        return SplitBCount;
    }

    @JsonIgnore
    public void setSplitBCount(int splitBCount) {
        SplitBCount = splitBCount;
    }

    @JsonIgnore
    public float getBCount() {
        return BCount;
    }

    @JsonIgnore
    public void setBCount(float BCount) {
        this.BCount = BCount;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
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
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public String getF1() {
        return F1;
    }

    @JsonIgnore
    public void setF1(String f1) {
        F1 = f1;
    }
}
