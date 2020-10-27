package pnc.mesadmin.entity;

import java.util.Date;

//工位信息表(tpm_StationInfo)
public class StationInfo {
    private Integer Ruid;
    private String Guid;
    private String StationName;
    private String AssDevGd;
    private String LineGd;
    private String SpecVerGd;
    private String PinterGd;
    private String PrintTGd;
    private Integer PrintCopy;
    private String ExecType;
    private String Status;
    private String Start;
    private String Up;
    private String Down;
    private String End;
   /* private String UpMa;
    private String DownMa;*/
    private String CarrierUnBind;
    private String PrintLabel;
    private String CarrierType;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public Integer getPrintCopy() {
        return PrintCopy;
    }

    public void setPrintCopy(Integer printCopy) {
        PrintCopy = printCopy;
    }

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getAssDevGd() {
        return AssDevGd;
    }

    public void setAssDevGd(String assDevGd) {
        AssDevGd = assDevGd;
    }

    public String getLineGd() {
        return LineGd;
    }

    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }

    public String getSpecVerGd() {
        return SpecVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        SpecVerGd = specVerGd;
    }

    public String getPinterGd() {
        return PinterGd;
    }

    public void setPinterGd(String pinterGd) {
        PinterGd = pinterGd;
    }

    public String getExecType() {
        return ExecType;
    }

    public void setExecType(String execType) {
        ExecType = execType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getUp() {
        return Up;
    }

    public void setUp(String up) {
        Up = up;
    }

    public String getDown() {
        return Down;
    }

    public void setDown(String down) {
        Down = down;
    }

    public String getCarrierUnBind() {
        return CarrierUnBind;
    }

    public void setCarrierUnBind(String carrierUnBind) {
        CarrierUnBind = carrierUnBind;
    }

    public String getPrintLabel() {
        return PrintLabel;
    }

    public void setPrintLabel(String printLabel) {
        PrintLabel = printLabel;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getPrintTGd() {
        return PrintTGd;
    }

    public void setPrintTGd(String printTGd) {
        PrintTGd = printTGd;
    }

    /*public String getUpMa() {
        return UpMa;
    }

    public void setUpMa(String upMa) {
        UpMa = upMa;
    }

    public String getDownMa() {
        return DownMa;
    }

    public void setDownMa(String downMa) {
        DownMa = downMa;
    }*/

    public String getCarrierType() {
        return CarrierType;
    }

    public void setCarrierType(String carrierType) {
        CarrierType = carrierType;
    }
}
