package pnc.mesadmin.entity;

import java.io.Serializable;

public class CarrierBZDtlInfo implements Serializable {

    private int ruid;
    private String CarrierBZGd;
    private String KeyName;
    private String KeyValue;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getCarrierBZGd() {
        return CarrierBZGd;
    }

    public void setCarrierBZGd(String carrierBZGd) {
        CarrierBZGd = carrierBZGd;
    }

    public String getKeyName() {
        return KeyName;
    }

    public void setKeyName(String keyName) {
        KeyName = keyName;
    }

    public String getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }
}
