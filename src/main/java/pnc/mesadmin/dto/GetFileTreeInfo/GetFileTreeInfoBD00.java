package pnc.mesadmin.dto.GetFileTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件版本列表信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-7-21
 * 修改人：
 * 修改时间：
 */
public class GetFileTreeInfoBD00 implements Serializable {

    @JsonProperty("FileRd")
    private int FileRd;

    @JsonIgnore
    public int getFileRd() {
        return FileRd;
    }

    @JsonIgnore
    public void setFileRd(int fileRd) {
        FileRd = fileRd;
    }
}
