package pnc.mesadmin.entity;

/**
 * Created by test on 2019/4/25.
 */
public class PackSpPropertyInfo {
    private int ruid;
    private String guid;
    private String packSpGd;
    private String propertyGd;
    private String propertyType;

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

    public String getPackSpGd() {
        return packSpGd;
    }

    public void setPackSpGd(String packSpGd) {
        this.packSpGd = packSpGd;
    }

    public String getPropertyGd() {
        return propertyGd;
    }

    public void setPropertyGd(String propertyGd) {
        this.propertyGd = propertyGd;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
