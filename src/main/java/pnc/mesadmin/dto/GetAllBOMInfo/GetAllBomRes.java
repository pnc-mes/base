package pnc.mesadmin.dto.GetAllBOMInfo;

import pnc.mesadmin.utils.PageUtil;

/**
 * @Description 分页请求条件
 * @Author yin.yang
 * @Date 2020/9/16
 * @Param
 * @Return
 * @Exception
 */
public class GetAllBomRes extends PageUtil {

    private String bomName;
    private String bomCode;
    private String status;

    public String getBomName() {
        return bomName;
    }

    public void setBomName(String bomName) {
        this.bomName = bomName;
    }

    public String getBomCode() {
        return bomCode;
    }

    public void setBomCode(String bomCode) {
        this.bomCode = bomCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
