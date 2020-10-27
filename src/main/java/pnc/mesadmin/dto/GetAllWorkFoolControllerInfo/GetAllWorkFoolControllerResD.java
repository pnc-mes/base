package pnc.mesadmin.dto.GetAllWorkFoolControllerInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2020/9/22 11:18
 * @Description:
 */
@Data
public class GetAllWorkFoolControllerResD implements Serializable {
    private String materialGd;
    private int materialRd; //版本
    private String materialName;
    private String materialCode;
    private String materialDes;
    private int workFoolRd;//版本
    private String workFoolGd;
    private String workFoolName;
    private int maRd;
    private int wfRd;
    private String Remark;
    private String Creator;
    private String LastModifyMan;
    private String LastModifyTime;
    private String CreateTime;

}
