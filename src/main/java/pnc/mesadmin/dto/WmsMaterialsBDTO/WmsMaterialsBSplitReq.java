package pnc.mesadmin.dto.WmsMaterialsBDTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次拆分DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBSplitReq {
    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("SplitInfo")
    private List<SplitInfo> SplitInfo;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public List<WmsMaterialsBSplitReq.SplitInfo> getSplitInfo() {
        return SplitInfo;
    }

    @JsonIgnore
    public void setSplitInfo(List<WmsMaterialsBSplitReq.SplitInfo> splitInfo) {
        SplitInfo = splitInfo;
    }

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
    public String getIsPrint() {
        return IsPrint;
    }

    @JsonIgnore
    public void setIsPrint(String isPrint) {
        IsPrint = isPrint;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    public static class SplitInfo {
        @JsonProperty("SplitBatch")
        private String SplitBatch;

        @JsonProperty("BadFlag")
        private String BadFlag;

        @JsonProperty("Num")
        private BigDecimal Num;

        @JsonIgnore
        public String getSplitBatch() {
            return SplitBatch;
        }

        @JsonIgnore
        public void setSplitBatch(String splitBatch) {
            SplitBatch = splitBatch;
        }

        @JsonIgnore
        public String getBadFlag() {
            return BadFlag;
        }

        @JsonIgnore
        public void setBadFlag(String badFlag) {
            BadFlag = badFlag;
        }

        @JsonIgnore
        public BigDecimal getNum() {
            return Num;
        }

        @JsonIgnore
        public void setNum(BigDecimal num) {
            Num = num;
        }
    }
}
