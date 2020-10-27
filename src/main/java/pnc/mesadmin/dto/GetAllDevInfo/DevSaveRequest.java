package pnc.mesadmin.dto.GetAllDevInfo;

import pnc.mesadmin.entity.DevInfo;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
public class DevSaveRequest extends DevInfo {
    private String lineName;
    private String typeName;
    private String statusName;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
