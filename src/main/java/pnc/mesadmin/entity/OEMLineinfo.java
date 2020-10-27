package pnc.mesadmin.entity;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/26.
 */
public class OEMLineinfo implements Serializable {
    private Integer ruid;
    private String LineGD;
    private String OEMLineGD;

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getLineGD() {
        return LineGD;
    }

    public void setLineGD(String lineGD) {
        LineGD = lineGD;
    }

    public String getOEMLineGD() {
        return OEMLineGD;
    }

    public void setOEMLineGD(String OEMLineGD) {
        this.OEMLineGD = OEMLineGD;
    }
}
