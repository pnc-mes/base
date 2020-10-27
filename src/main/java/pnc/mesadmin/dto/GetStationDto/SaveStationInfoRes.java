package pnc.mesadmin.dto.GetStationDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveStationInfoRes implements Serializable {

    @JsonProperty("StationRd")
    private Integer StationRd;
    @JsonProperty("StationName")
    private String StationName;
    @JsonProperty("AssDevInfo")
    private List<AssDevInfo> AssDevInfo;
    @JsonProperty("LineRd")
    private Integer LineRd;
    @JsonProperty("SpecRd")
    private Integer SpecRd;
    @JsonProperty("PrintRd")
    private Integer PrintRd;
    @JsonProperty("PrintTRd")
    private Integer PrintTRd;
    @JsonProperty("PrintCopy")
    private Integer PrintCopy;
    @JsonProperty("ExecType")
    private String ExecType;
    @JsonProperty("ExDetail")
    private List<ExDetail> ExDetail;
    @JsonProperty("OStation")
    private OStation OStation;
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
    @JsonProperty("Remark")
    private String Remark;

    //保存工位信息参数
    @JsonProperty("SStationRd")
    private Integer SStationRd;
    @JsonProperty("ExexRd")
    private Integer ExexRd;
    @JsonProperty("TStationRd")
    private Integer TStationRd;

    //复制工位信息参数
    @JsonProperty("SLineRd")
    private Integer SLineRd; //拷贝线体
    @JsonProperty("TLineRd")
    private Integer TLineRd; //目标线体

    @JsonIgnore
    public Integer getSLineRd() {
        return SLineRd;
    }

    @JsonIgnore
    public void setSLineRd(Integer SLineRd) {
        this.SLineRd = SLineRd;
    }

    @JsonIgnore
    public Integer getTLineRd() {
        return TLineRd;
    }

    @JsonIgnore
    public void setTLineRd(Integer TLineRd) {
        this.TLineRd = TLineRd;
    }

    @JsonIgnore
    public Integer getSStationRd() {
        return SStationRd;
    }

    @JsonIgnore
    public void setSStationRd(Integer SStationRd) {
        this.SStationRd = SStationRd;
    }

    @JsonIgnore
    public Integer getExexRd() {
        return ExexRd;
    }

    @JsonIgnore
    public void setExexRd(Integer exexRd) {
        ExexRd = exexRd;
    }

    @JsonIgnore
    public Integer getTStationRd() {
        return TStationRd;
    }

    @JsonIgnore
    public void setTStationRd(Integer TStationRd) {
        this.TStationRd = TStationRd;
    }

    @JsonIgnore
    public List<SaveStationInfoRes.StationAPI> getStationAPI() {
        return StationAPI;
    }

    @JsonIgnore
    public void setStationAPI(List<SaveStationInfoRes.StationAPI> stationAPI) {
        StationAPI = stationAPI;
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
    public SaveStationInfoRes.OStation getOStation() {
        return OStation;
    }

    @JsonIgnore
    public void setOStation(SaveStationInfoRes.OStation OStation) {
        this.OStation = OStation;
    }

    @JsonIgnore
    public List<SaveStationInfoRes.UDM> getUDM() {
        return UDM;
    }

    @JsonIgnore
    public void setUDM(List<SaveStationInfoRes.UDM> UDM) {
        this.UDM = UDM;
    }

    @JsonIgnore
    public List<SaveStationInfoRes.Integrated> getIntegrated() {
        return Integrated;
    }

    @JsonIgnore
    public void setIntegrated(List<SaveStationInfoRes.Integrated> integrated) {
        Integrated = integrated;
    }

    @JsonIgnore
    public List<SaveStationInfoRes.Alarm> getAlarm() {
        return Alarm;
    }

    @JsonIgnore
    public void setAlarm(List<SaveStationInfoRes.Alarm> alarm) {
        Alarm = alarm;
    }

    public static class AssDevInfo {
        @JsonProperty("DevRd")
        private Integer DevRd;

        @JsonIgnore
        public Integer getDevRd() {
            return DevRd;
        }

        @JsonIgnore
        public void setDevRd(Integer devRd) {
            DevRd = devRd;
        }
    }

    public static class OStation {


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
        public String getCarrierType() {
            return CarrierType;
        }

        @JsonIgnore
        public void setCarrierType(String carrierType) {
            CarrierType = carrierType;
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


    }

    public static class UDM {
        @JsonProperty("UDMType")
        private String UDMType;
        @JsonProperty("UDMCarrierType")
        private String UDMCarrierType;
        @JsonProperty("DMArea")
        private String DMArea;

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
    }

    public static class Integrated {

        @JsonProperty("DevRd")
        private Integer DevRd;
        @JsonProperty("DevMapRd")
        private Integer DevMapRd;
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
        public Integer getDevMapRd() {
            return DevMapRd;
        }

        @JsonIgnore
        public void setDevMapRd(Integer devMapRd) {
            DevMapRd = devMapRd;
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
        public List<SaveStationInfoRes.AlarmRevDtl> getAlarmRevDtl() {
            return AlarmRevDtl;
        }

        @JsonIgnore
        public void setAlarmRevDtl(List<SaveStationInfoRes.AlarmRevDtl> alarmRevDtl) {
            AlarmRevDtl = alarmRevDtl;
        }
    }

    public static class AlarmRevDtl {
        @JsonProperty("UserRd")
        private Integer UserRd;

        @JsonIgnore
        public Integer getUserRd() {
            return UserRd;
        }

        @JsonIgnore
        public void setUserRd(Integer userRd) {
            UserRd = userRd;
        }
    }

    public static class ExDetail {
        @JsonProperty("ExecRd")
        private Integer ExecRd;
        @JsonProperty("ExecGRd")
        private Integer ExecGRd;

        @JsonIgnore
        public Integer getExecRd() {
            return ExecRd;
        }

        @JsonIgnore
        public void setExecRd(Integer execRd) {
            ExecRd = execRd;
        }

        @JsonIgnore
        public Integer getExecGRd() {
            return ExecGRd;
        }

        @JsonIgnore
        public void setExecGRd(Integer execGRd) {
            ExecGRd = execGRd;
        }
    }

    public static class StationAPI {
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public Integer getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(Integer specRd) {
        SpecRd = specRd;
    }

    @JsonIgnore
    public Integer getPrintRd() {
        return PrintRd;
    }

    @JsonIgnore
    public void setPrintRd(Integer printRd) {
        PrintRd = printRd;
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
    public List<SaveStationInfoRes.ExDetail> getExDetail() {
        return ExDetail;
    }

    @JsonIgnore
    public void setExDetail(List<SaveStationInfoRes.ExDetail> exDetail) {
        ExDetail = exDetail;
    }


    @JsonIgnore
    public Integer getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(Integer printTRd) {
        PrintTRd = printTRd;
    }

    @JsonIgnore
    public List<SaveStationInfoRes.AssDevInfo> getAssDevInfo() {
        return AssDevInfo;
    }

    @JsonIgnore
    public void setAssDevInfo(List<SaveStationInfoRes.AssDevInfo> assDevInfo) {
        AssDevInfo = assDevInfo;
    }
}
