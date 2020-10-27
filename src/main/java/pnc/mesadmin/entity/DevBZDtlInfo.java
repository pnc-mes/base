package pnc.mesadmin.entity;

import java.io.Serializable;

public class DevBZDtlInfo implements Serializable {
    private int ruid;
    private String DevBZGd;
    private String KeyName;
    private String KeyValue;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getDevBZGd() {
        return DevBZGd;
    }

    public void setDevBZGd(String devBZGd) {
        DevBZGd = devBZGd;
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
