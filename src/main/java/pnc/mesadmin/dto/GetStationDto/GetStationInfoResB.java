package pnc.mesadmin.dto.GetStationDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetStationInfoResB implements Serializable {

    @JsonProperty("StationRd")
    private Integer StationRd;
    @JsonProperty("StationName")
    private String StationName;

    @JsonProperty("AssDevInfo")
    private List<AssDevInfo> AssDevInfo;
    @JsonProperty("LineInfo")
    private LineInfo LineInfo;
    @JsonProperty("SpecInfo")
    private SpecInfo SpecInfo;
    @JsonProperty("PrinterInfo")
    private PrinterInfo PrinterInfo;
    @JsonProperty("PrintTInfo")
    private PrintTInfo PrintTInfo;
    @JsonProperty("PrintCopy")
    private Integer PrintCopy;

    @JsonProperty("OStation")
    private OStation OStation;
    @JsonProperty("ExecType")
    private String ExecType;

    @JsonProperty("ExDetail")
    private List<ExDetail> ExDetail;

    @JsonProperty("UDM")
    private List<UDM> UDM;

    @JsonProperty("Integrated")
    private List<Integrated> Integrated;

    @JsonProperty("Alarm")
    private List<Alarm> Alarm;
    @JsonProperty("StationAPI")
    private List<StationAPI> StationAPI;

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LastModifyMan")
    private String LastModifyMan;
    @JsonProperty("LastModifyTime")
    private String LastModifyTime;
    @JsonProperty("Remark")
    private String Remark;





    public static class AssDevInfo {
        @JsonProperty("DevRd")
        private Integer DevRd;
        @JsonProperty("DevName")
        private String DevName;

        @JsonIgnore
        public Integer getDevRd() {
            return DevRd;
        }

        @JsonIgnore
        public void setDevRd(Integer devRd) {
            DevRd = devRd;
        }

        @JsonIgnore
        public String getDevName() {
            return DevName;
        }

        @JsonIgnore
        public void setDevName(String devName) {
            DevName = devName;
        }
    }

    public static class LineInfo {
        @JsonProperty("LineRd")
        private Integer LineRd;
        @JsonProperty("LineName")
        private String LineName;

        @JsonIgnore
        public Integer getLineRd() {
            return LineRd;
        }

        @JsonIgnore
        public void setLineRd(Integer lineRd) {
            LineRd = lineRd;
        }

        @JsonIgnore
        public String getLineName() {
            return LineName;
        }

        @JsonIgnore
        public void setLineName(String lineName) {
            LineName = lineName;
        }
    }

    public static class SpecInfo {
        @JsonProperty("SpecRd")
        private Integer SpecRd;
        @JsonProperty("SpecName")
        private String SpecName;

        @JsonIgnore
        public Integer getSpecRd() {
            return SpecRd;
        }

        @JsonIgnore
        public void setSpecRd(Integer specRd) {
            SpecRd = specRd;
        }

        @JsonIgnore
        public String getSpecName() {
            return SpecName;
        }

        @JsonIgnore
        public void setSpecName(String specName) {
            SpecName = specName;
        }
    }

    public static class PrinterInfo {
        @JsonProperty("PrintRd")
        private Integer PrintRd;
        @JsonProperty("PrName")
        private String PrName;

        @JsonIgnore
        public Integer getPrintRd() {
            return PrintRd;
        }

        @JsonIgnore
        public void setPrintRd(Integer printRd) {
            PrintRd = printRd;
        }

        @JsonIgnore
        public String getPrName() {
            return PrName;
        }

        @JsonIgnore
        public void setPrName(String prName) {
            PrName = prName;
        }
    }

    public static class PrintTInfo {
        @JsonProperty("PrintTRd")
        private Integer PrintTRd;
        @JsonProperty("PrintTName")
        private String PrintTName;

        @JsonIgnore
        public Integer getPrintTRd() {
            return PrintTRd;
        }

        @JsonIgnore
        public void setPrintTRd(Integer printTRd) {
            PrintTRd = printTRd;
        }

        @JsonIgnore
        public String getPrintTName() {
            return PrintTName;
        }

        @JsonIgnore
        public void setPrintTName(String printTName) {
            PrintTName = printTName;
        }
    }

    public static class OStation{

        @JsonProperty("Start")
        private String Start;
        @JsonProperty("Up")
        private String Up;
        @JsonProperty("Down")
        private String Down;
        @JsonProperty("End")
        private String End;
        @JsonProperty("CarrierUnBind")
        private String CarrierUnBind;
        @JsonProperty("PrintLabel")
        private String PrintLabel;
        @JsonProperty("CarrierType")
        private String CarrierType;

        @JsonIgnore
        public String getStart() {
            return Start;
        }

        @JsonIgnore
        public void setStart(String start) {
            Start = start;
        }

        @JsonIgnore
        public String getUp() {
            return Up;
        }

        @JsonIgnore
        public void setUp(String up) {
            Up = up;
        }

        @JsonIgnore
        public String getDown() {
            return Down;
        }

        @JsonIgnore
        public void setDown(String down) {
            Down = down;
        }

        @JsonIgnore
        public String getEnd() {
            return End;
        }

        @JsonIgnore
        public void setEnd(String end) {
            End = end;
        }

        @JsonIgnore
        public String getCarrierUnBind() {
            return CarrierUnBind;
        }
        @JsonIgnore
        public void setCarrierUnBind(String carrierUnBind) {
            CarrierUnBind = carrierUnBind;
        }
        @JsonIgnore
        public String getPrintLabel() {
            return PrintLabel;
        }
        @JsonIgnore
        public void setPrintLabel(String printLabel) {
            PrintLabel = printLabel;
        }

        @JsonIgnore
        public String getCarrierType() {
            return CarrierType;
        }

        @JsonIgnore
        public void setCarrierType(String carrierType) {
            CarrierType = carrierType;
        }


    }

    public static class ExDetail {
        @JsonProperty("ExecRd")
        private Integer ExecRd;
        @JsonProperty("ExecName")
        private String ExecName;
        @JsonProperty("ExecGRd")
        private Integer ExecGRd;
        @JsonProperty("ExecGName")
        private String ExecGName;

        @JsonIgnore
        public Integer getExecRd() {
            return ExecRd;
        }

        @JsonIgnore
        public void setExecRd(Integer execRd) {
            ExecRd = execRd;
        }

        @JsonIgnore
        public String getExecName() {
            return ExecName;
        }

        @JsonIgnore
        public void setExecName(String execName) {
            ExecName = execName;
        }

        @JsonIgnore
        public Integer getExecGRd() {
            return ExecGRd;
        }

        @JsonIgnore
        public void setExecGRd(Integer execGRd) {
            ExecGRd = execGRd;
        }

        @JsonIgnore
        public String getExecGName() {
            return ExecGName;
        }

        @JsonIgnore
        public void setExecGName(String execGName) {
            ExecGName = execGName;
        }
    }

    public static class UDM{
        @JsonProperty("UDMType")
        private String UDMType;
        @JsonProperty("UDMCarrierType")
        private String UDMCarrierType;
        @JsonProperty("DMArea")
        private String DMArea;
        @JsonProperty("UDMIndex")
        private Integer UDMIndex;

        @JsonIgnore
        public String getUDMType() {
            return UDMType;
        }
        @JsonIgnore
        public void setUDMType(String UDMType) {
            this.UDMType = UDMType;
        }
        @JsonIgnore
        public String getUDMCarrierType() {
            return UDMCarrierType;
        }
        @JsonIgnore
        public void setUDMCarrierType(String UDMCarrierType) {
            this.UDMCarrierType = UDMCarrierType;
        }
        @JsonIgnore
        public String getDMArea() {
            return DMArea;
        }
        @JsonIgnore
        public void setDMArea(String DMArea) {
            this.DMArea = DMArea;
        }
        @JsonIgnore
        public Integer getUDMIndex() {
            return UDMIndex;
        }
        @JsonIgnore
        public void setUDMIndex(Integer UDMIndex) {
            this.UDMIndex = UDMIndex;
        }
    }
    public static class Integrated {
        @JsonProperty("DevRd")
        private Integer DevRd;
        @JsonProperty("DevName")
        private String DevName;
        @JsonProperty("DevMapRd")
        private Integer DevMapRd;
        @JsonProperty("DevMapName")
        private String DevMapName;
        @JsonProperty("TriggerType")
        private String TriggerType;
        @JsonProperty("CmdText")
        private String CmdText;
        @JsonProperty("CmdType")
        private String CmdType;
        @JsonProperty("CmdVal")
        private String CmdVal;
        @JsonIgnore
        public Integer getDevRd() {
            return DevRd;
        }
        @JsonIgnore
        public void setDevRd(Integer devRd) {
            DevRd = devRd;
        }
        @JsonIgnore
        public String getDevName() {
            return DevName;
        }
        @JsonIgnore
        public void setDevName(String devName) {
            DevName = devName;
        }
        @JsonIgnore
        public Integer getDevMapRd() {
            return DevMapRd;
        }
        @JsonIgnore
        public void setDevMapRd(Integer devMapRd) {
            DevMapRd = devMapRd;
        }
        @JsonIgnore
        public String getDevMapName() {
            return DevMapName;
        }
        @JsonIgnore
        public void setDevMapName(String devMapName) {
            DevMapName = devMapName;
        }
        @JsonIgnore
        public String getTriggerType() {
            return TriggerType;
        }
        @JsonIgnore
        public void setTriggerType(String triggerType) {
            TriggerType = triggerType;
        }
        @JsonIgnore
        public String getCmdText() {
            return CmdText;
        }
        @JsonIgnore
        public void setCmdText(String cmdText) {
            CmdText = cmdText;
        }
        @JsonIgnore
        public String getCmdType() {
            return CmdType;
        }
        @JsonIgnore
        public void setCmdType(String cmdType) {
            CmdType = cmdType;
        }
        @JsonIgnore
        public String getCmdVal() {
            return CmdVal;
        }
        @JsonIgnore
        public void setCmdVal(String cmdVal) {
            CmdVal = cmdVal;
        }
    }

    public static class Alarm {
        @JsonProperty("ExcepType")
        private String ExcepType;
        @JsonProperty("AlarmType")
        private String AlarmType;
        @JsonProperty("AlarmRev")
        private String AlarmRev;
        @JsonProperty("AlarmRevDtl")
        private List<AlarmRevDtl> AlarmRevDtl;


        @JsonIgnore
        public String getExcepType() {
            return ExcepType;
        }
        @JsonIgnore
        public void setExcepType(String excepType) {
            ExcepType = excepType;
        }
        @JsonIgnore
        public String getAlarmType() {
            return AlarmType;
        }
        @JsonIgnore
        public void setAlarmType(String alarmType) {
            AlarmType = alarmType;
        }
        @JsonIgnore
        public String getAlarmRev() {
            return AlarmRev;
        }
        @JsonIgnore
        public void setAlarmRev(String alarmRev) {
            AlarmRev = alarmRev;
        }
        @JsonIgnore
        public List<GetStationInfoResB.AlarmRevDtl> getAlarmRevDtl() {
            return AlarmRevDtl;
        }
        @JsonIgnore
        public void setAlarmRevDtl(List<GetStationInfoResB.AlarmRevDtl> alarmRevDtl) {
            AlarmRevDtl = alarmRevDtl;
        }
    }
    public static class AlarmRevDtl{
        @JsonProperty("UserRd")
        private Integer UserRd;
        @JsonProperty("UserName")
        private String UserName;

        @JsonIgnore
        public Integer getUserRd() {
            return UserRd;
        }
        @JsonIgnore
        public void setUserRd(Integer userRd) {
            UserRd = userRd;
        }
        @JsonIgnore
        public String getUserName() {
            return UserName;
        }
        @JsonIgnore
        public void setUserName(String userName) {
            UserName = userName;
        }
    }

    public static class StationAPI{

        @JsonProperty("APIUrl")
        private String APIUrl;
        @JsonProperty("TriggerType")
        private String TriggerType;
        @JsonProperty("SysVal")
        private String SysVal;
        @JsonIgnore
        public String getAPIUrl() {
            return APIUrl;
        }
        @JsonIgnore
        public void setAPIUrl(String APIUrl) {
            this.APIUrl = APIUrl;
        }
        @JsonIgnore
        public String getTriggerType() {
            return TriggerType;
        }
        @JsonIgnore
        public void setTriggerType(String triggerType) {
            TriggerType = triggerType;
        }
        @JsonIgnore
        public String getSysVal() {
            return SysVal;
        }
        @JsonIgnore
        public void setSysVal(String sysVal) {
            SysVal = sysVal;
        }
    }

    @JsonIgnore
    public Integer getStationRd() {
        return StationRd;
    }

    @JsonIgnore
    public void setStationRd(Integer stationRd) {
        StationRd = stationRd;
    }

    @JsonIgnore
    public String getStationName() {
        return StationName;
    }

    @JsonIgnore
    public void setStationName(String stationName) {
        StationName = stationName;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
    public List<GetStationInfoResB.AssDevInfo> getAssDevInfo() {
        return AssDevInfo;
    }

    @JsonIgnore
    public void setAssDevInfo(List<GetStationInfoResB.AssDevInfo> assDevInfo) {
        AssDevInfo = assDevInfo;
    }

    @JsonIgnore
    public GetStationInfoResB.LineInfo getLineInfo() {
        return LineInfo;
    }

    @JsonIgnore
    public void setLineInfo(GetStationInfoResB.LineInfo lineInfo) {
        LineInfo = lineInfo;
    }

    @JsonIgnore
    public GetStationInfoResB.SpecInfo getSpecInfo() {
        return SpecInfo;
    }

    @JsonIgnore
    public void setSpecInfo(GetStationInfoResB.SpecInfo specInfo) {
        SpecInfo = specInfo;
    }

    @JsonIgnore
    public GetStationInfoResB.PrinterInfo getPrinterInfo() {
        return PrinterInfo;
    }

    @JsonIgnore
    public void setPrinterInfo(GetStationInfoResB.PrinterInfo printerInfo) {
        PrinterInfo = printerInfo;
    }

    @JsonIgnore
    public GetStationInfoResB.PrintTInfo getPrintTInfo() {
        return PrintTInfo;
    }

    @JsonIgnore
    public void setPrintTInfo(GetStationInfoResB.PrintTInfo printTInfo) {
        PrintTInfo = printTInfo;
    }

    @JsonIgnore
    public GetStationInfoResB.OStation getOStation() {
        return OStation;
    }
    @JsonIgnore
    public void setOStation(GetStationInfoResB.OStation OStation) {
        this.OStation = OStation;
    }
    @JsonIgnore
    public List<GetStationInfoResB.UDM> getUDM() {
        return UDM;
    }
    @JsonIgnore
    public void setUDM(List<GetStationInfoResB.UDM> UDM) {
        this.UDM = UDM;
    }
    @JsonIgnore
    public List<GetStationInfoResB.Integrated> getIntegrated() {
        return Integrated;
    }
    @JsonIgnore
    public void setIntegrated(List<GetStationInfoResB.Integrated> integrated) {
        Integrated = integrated;
    }
    @JsonIgnore
    public List<GetStationInfoResB.Alarm> getAlarm() {
        return Alarm;
    }
    @JsonIgnore
    public void setAlarm(List<GetStationInfoResB.Alarm> alarm) {
        Alarm = alarm;
    }
    @JsonIgnore
    public String getExecType() {
        return ExecType;
    }

    @JsonIgnore
    public void setExecType(String execType) {
        ExecType = execType;
    }
    @JsonIgnore
    public List<GetStationInfoResB.ExDetail> getExDetail() {
        return ExDetail;
    }

    @JsonIgnore
    public void setExDetail(List<GetStationInfoResB.ExDetail> exDetail) {
        ExDetail = exDetail;
    }
    @JsonIgnore
    public Integer getPrintCopy() {
        return PrintCopy;
    }
    @JsonIgnore
    public void setPrintCopy(Integer printCopy) {
        PrintCopy = printCopy;
    }
    @JsonIgnore
    public List<GetStationInfoResB.StationAPI> getStationAPI() {
        return StationAPI;
    }
    @JsonIgnore
    public void setStationAPI(List<GetStationInfoResB.StationAPI> stationAPI) {
        StationAPI = stationAPI;
    }
}
