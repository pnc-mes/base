package pnc.mesadmin.entity.common;

/**
 * 点检记录查询返回的实体类
 */
public class DJSelectInfo {
    private String TaskItemName;
    private String CheckObjType;
    private String CheckObjGd;
    private String LineName;
    private String ShiftName;
    private String Status;
    private String MaTime;
    private String CreateTime;
    private String ExecResult02;
    private String OptName;
    private String LastOptTime;
    private String SureMan;
    private String LastSureTime;

    public String getTaskItemName() {
        return TaskItemName;
    }

    public void setTaskItemName(String taskItemName) {
        TaskItemName = taskItemName;
    }

    public String getCheckObjType() {
        return CheckObjType;
    }

    public void setCheckObjType(String checkObjType) {
        CheckObjType = checkObjType;
    }

    public String getCheckObjGd() {
        return CheckObjGd;
    }

    public void setCheckObjGd(String checkObjGd) {
        CheckObjGd = checkObjGd;
    }

    public String getLineName() {
        return LineName;
    }

    public void setLineName(String lineName) {
        LineName = lineName;
    }

    public String getShiftName() {
        return ShiftName;
    }

    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMaTime() {
        return MaTime;
    }

    public void setMaTime(String maTime) {
        MaTime = maTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getExecResult02() {
        return ExecResult02;
    }

    public void setExecResult02(String execResult02) {
        ExecResult02 = execResult02;
    }

    public String getOptName() {
        return OptName;
    }

    public void setOptName(String optName) {
        OptName = optName;
    }

    public String getLastOptTime() {
        return LastOptTime;
    }

    public void setLastOptTime(String lastOptTime) {
        LastOptTime = lastOptTime;
    }

    public String getSureMan() {
        return SureMan;
    }

    public void setSureMan(String sureMan) {
        SureMan = sureMan;
    }

    public String getLastSureTime() {
        return LastSureTime;
    }

    public void setLastSureTime(String lastSureTime) {
        LastSureTime = lastSureTime;
    }
}
