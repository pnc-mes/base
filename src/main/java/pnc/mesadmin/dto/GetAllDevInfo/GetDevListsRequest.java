package pnc.mesadmin.dto.GetAllDevInfo;

import pnc.mesadmin.utils.PageUtil;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
public class GetDevListsRequest extends PageUtil {
    private Integer devRd;
    //设备名称
    private String devName;
    //设备编号
    private String devCode;
    //设备类型
    private Integer devTypeRd;
    //设备状态
    private String devStatus;
    //线别id
    private Integer lineRd;
    //时间段
    private String startTime;

    private String endTime;

    public Integer getDevRd() {
        return devRd;
    }

    public void setDevRd(Integer devRd) {
        this.devRd = devRd;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public Integer getDevTypeRd() {
        return devTypeRd;
    }

    public void setDevTypeRd(Integer devTypeRd) {
        this.devTypeRd = devTypeRd;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getLineRd() {
        return lineRd;
    }

    public void setLineRd(Integer lineRd) {
        this.lineRd = lineRd;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
