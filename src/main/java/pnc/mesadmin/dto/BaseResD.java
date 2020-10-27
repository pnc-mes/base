package pnc.mesadmin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2020/9/9 09:12
 * @Description:
 */
@Data
public class BaseResD implements Serializable {
    private String creator;
    private String createTime;
    private String lastModifyTime;
    private String lastModifyMan;
    private String remark;
}
