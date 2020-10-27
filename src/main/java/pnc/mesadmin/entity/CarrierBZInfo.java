package pnc.mesadmin.entity;

import java.io.Serializable;

public class CarrierBZInfo implements Serializable {
    private int ruid;
    private String guid;
    private String CarrierGd;
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

    public String getCarrierGd() {
        return CarrierGd;
    }

    public void setCarrierGd(String carrierGd) {
        CarrierGd = carrierGd;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}
