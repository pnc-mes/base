package pnc.mesadmin.dto.SaveDExLogInfo;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/24 17:51
 * @Description:
 */
public class SaveDExLogInfoReq01 implements Serializable {
    //dto严格遵守驼峰命名法
    private String batch;

    private String lineGd;

    private String specVerGd;

    private String stationGd;

    private String msg;

    private String execor;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getLineGd() {
        return lineGd;
    }

    public void setLineGd(String lineGd) {
        this.lineGd = lineGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getStationGd() {
        return stationGd;
    }

    public void setStationGd(String stationGd) {
        this.stationGd = stationGd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExecor() {
        return execor;
    }

    public void setExecor(String execor) {
        this.execor = execor;
    }
}
