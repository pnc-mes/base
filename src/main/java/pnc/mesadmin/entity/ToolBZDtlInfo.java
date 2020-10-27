package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName(value ="tpm_toolbzdtlinfo")
public class ToolBZDtlInfo implements Serializable {
    private int ruid;
    private String ToolBZGd;
    private String KeyName;
    private String KeyValue;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getToolBZGd() {
        return ToolBZGd;
    }

    public void setToolBZGd(String toolBZGd) {
        ToolBZGd = toolBZGd;
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
