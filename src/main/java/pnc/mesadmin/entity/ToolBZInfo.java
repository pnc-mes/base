package pnc.mesadmin.entity;

import java.io.Serializable;

public class ToolBZInfo implements Serializable {
    private int ruid;
    private String guid;
    private String ToolGd;
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

    public String getToolGd() {
        return ToolGd;
    }

    public void setToolGd(String toolGd) {
        ToolGd = toolGd;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}
