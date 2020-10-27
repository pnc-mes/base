package pnc.mesadmin.entity;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/25.
 */
public class OrderLineinfo implements Serializable {
    private Integer ruid;
    private String woGd;
    private String LineGd;

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getWoGd() {
        return woGd;
    }

    public void setWoGd(String woGd) {
        this.woGd = woGd;
    }

    public String getLineGd() {
        return LineGd;
    }

    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }
}
