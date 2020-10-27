package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class DevSMDtlnfo {
    private int ruid;
    private String guid;
    private String devSMGd;
    private String souDSGd;
    private String tarDSGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;

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

    public String getDevSMGd() {
        return devSMGd;
    }

    public void setDevSMGd(String devSMGd) {
        this.devSMGd = devSMGd;
    }

    public String getSouDSGd() {
        return souDSGd;
    }

    public void setSouDSGd(String souDSGd) {
        this.souDSGd = souDSGd;
    }

    public String getTarDSGd() {
        return tarDSGd;
    }

    public void setTarDSGd(String tarDSGd) {
        this.tarDSGd = tarDSGd;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
