package pnc.mesadmin.entity;

import java.io.Serializable;

public class DevBZInfo implements Serializable {


    private int ruid;
    private String guid;
    private String DevGd;
    private String Reference;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}
