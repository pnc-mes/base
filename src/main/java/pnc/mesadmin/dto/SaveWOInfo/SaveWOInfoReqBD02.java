package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：编辑工单信息DTO
 * 创建人：张亮亮
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SaveWOInfoReqBD02 implements Serializable{

    @JsonProperty("TrayPSpRd")
    private int TrayPSpRd;

    @JsonProperty("BoxPSpRd")
    private int BoxPSpRd;

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("UrcyRd")
    private int UrcyRd;

    @JsonProperty("WTRd")
    private int WTRd;
    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitRd")
    private int UnitRd;

    @JsonProperty("OrderCode")
    private String OrderCode;

    @JsonProperty("JStartDate")
    private String JStartDate;

    @JsonProperty("JFinishDate")
    private String JFinishDate;

    @JsonProperty("LineInfo")
    private List<SaveOrderLineInfoReq02OrderList> LineInfo;

  /*  @JsonProperty("LineRd")
    private int LineRd;*/

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public List<SaveOrderLineInfoReq02OrderList> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<SaveOrderLineInfoReq02OrderList> lineInfo) {
        LineInfo = lineInfo;
    }





    /*  @JsonIgnore
        public int getLineRd() {
            return LineRd;
        }
        @JsonIgnore
        public void setLineRd(int lineRd) {
            LineRd = lineRd;
        }
    */
    @JsonIgnore
    public int getUrcyRd() {
        return UrcyRd;
    }
    @JsonIgnore
    public void setUrcyRd(int urcyRd) {
        UrcyRd = urcyRd;
    }

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }
    @JsonIgnore
    public int getWTRd() {
        return WTRd;
    }
    @JsonIgnore
    public void setWTRd(int WTRd) {
        this.WTRd = WTRd;
    }
    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
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
    public int getWfVerRd() {
        return WfVerRd;
    }

    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public int getUnitRd() {
        return UnitRd;
    }

    @JsonIgnore
    public void setUnitRd(int unitRd) {
        UnitRd = unitRd;
    }

    @JsonIgnore
    public String getOrderCode() {
        return OrderCode;
    }

    @JsonIgnore
    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    @JsonIgnore
    public String getJStartDate() {
        return JStartDate;
    }

    @JsonIgnore
    public void setJStartDate(String JStartDate) {
        this.JStartDate = JStartDate;
    }

    @JsonIgnore
    public String getJFinishDate() {
        return JFinishDate;
    }

    @JsonIgnore
    public void setJFinishDate(String JFinishDate) {
        this.JFinishDate = JFinishDate;
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
    public int getTrayPSpRd() {
        return TrayPSpRd;
    }

    @JsonIgnore
    public void setTrayPSpRd(int trayPSpRd) {
        TrayPSpRd = trayPSpRd;
    }

    @JsonIgnore
    public int getBoxPSpRd() {
        return BoxPSpRd;
    }

    @JsonIgnore
    public void setBoxPSpRd(int boxPSpRd) {
        BoxPSpRd = boxPSpRd;
    }
}
