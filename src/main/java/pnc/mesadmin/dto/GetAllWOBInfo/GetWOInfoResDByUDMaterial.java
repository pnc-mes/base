package pnc.mesadmin.dto.GetAllWOBInfo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWFInfo;
import pnc.mesadmin.dto.GetWOInfo.*;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单列表返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetWOInfoResDByUDMaterial implements Serializable{

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("OrderCode")
    private String OrderCode;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("JStartDate")
    private String JStartDate;

    @JsonProperty("JFinishDate")
    private String JFinishDate;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("FinishNum")
    private Float FinishNum;

    @JsonProperty("UnCBatNum")
    private Float UnCBatNum;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("BoxPackInfo")
    private GetWOInfoResBDBoxPack BoxPackInfo;
    @JsonProperty("WFInfo")
    private GetCMWInfoResDWFInfo WFInfo;
    @JsonProperty("LineInfo")
    private List<GetWOInfoResBDOrderLine> LineInfo;
    @JsonProperty("TrayPackInfo")
    private GetWOInfoResBDTrayPack TrayPackInfo;
    @JsonProperty("WOTInfo")
    private GetWOInfoResBDWOT WOTInfo;
    @JsonProperty("UrcyInfo")
    private GetWOInfoResBDUrcy UrcyInfo;


    @JsonIgnore
    public String getDSource() {
        return DSource;
    }

    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    @JsonIgnore
    public GetWOInfoResBDWOT getWOTInfo() {
        return WOTInfo;
    }
    @JsonIgnore
    public void setWOTInfo(GetWOInfoResBDWOT WOTInfo) {
        this.WOTInfo = WOTInfo;
    }

    @JsonIgnore
    public GetWOInfoResBDBoxPack getBoxPackInfo() {
        return BoxPackInfo;
    }
    @JsonIgnore
    public void setBoxPackInfo(GetWOInfoResBDBoxPack boxPackInfo) {
        BoxPackInfo = boxPackInfo;
    }
    @JsonIgnore
    public GetWOInfoResBDTrayPack getTrayPackInfo() {
        return TrayPackInfo;
    }
    @JsonIgnore
    public void setTrayPackInfo(GetWOInfoResBDTrayPack trayPackInfo) {
        TrayPackInfo = trayPackInfo;
    }

    @JsonIgnore
    public Float getFinishNum() {
        return FinishNum;
    }

    @JsonIgnore
    public void setFinishNum(Float finishNum) {
        FinishNum = finishNum;
    }

    @JsonIgnore
    public Float getUnCBatNum() {
        return UnCBatNum;
    }

    @JsonIgnore
    public void setUnCBatNum(Float unCBatNum) {
        UnCBatNum = unCBatNum;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonProperty("Status")
    private String Status;



    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("MaInfo")
    private GetWOInfoResBDMa MaInfo;

    @JsonProperty("UnitInfo")
    private GetWOInfoResBDUnit UnitInfo;


    @JsonProperty("SpecVerInfo")
    private GetWOInfoResBDSpecVer SpecVerInfo;
    @JsonIgnore
    public int getWoRd() {
        return WoRd;
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
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
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
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public GetWOInfoResBDMa getMaInfo() {
        return MaInfo;
    }

    @JsonIgnore
    public void setMaInfo(GetWOInfoResBDMa maInfo) {
        MaInfo = maInfo;
    }

    @JsonIgnore
    public GetWOInfoResBDUnit getUnitInfo() {
        return UnitInfo;
    }

    @JsonIgnore
    public void setUnitInfo(GetWOInfoResBDUnit unitInfo) {
        UnitInfo = unitInfo;
    }
    @JsonIgnore
    public GetWOInfoResBDUrcy getUrcyInfo() {
        return UrcyInfo;
    }
    @JsonIgnore
    public void setUrcyInfo(GetWOInfoResBDUrcy urcyInfo) {
        UrcyInfo = urcyInfo;
    }
    @JsonIgnore
    public List<GetWOInfoResBDOrderLine> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<GetWOInfoResBDOrderLine> lineInfo) {
        LineInfo = lineInfo;
    }

    @JsonIgnore
    public GetWOInfoResBDSpecVer getSpecVerInfo() {
        return SpecVerInfo;
    }
    @JsonIgnore
    public void setSpecVerInfo(GetWOInfoResBDSpecVer specVerInfo) {
        SpecVerInfo = specVerInfo;
    }

    @JsonIgnore
    public GetCMWInfoResDWFInfo getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(GetCMWInfoResDWFInfo WFInfo) {
        this.WFInfo = WFInfo;
    }
}

