package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoRes;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoRes;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResD;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoReqBD00;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveToolInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具信息Service接口
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
public interface ToolIService {

    //dto查询工具信息列表
    GetAllToolInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto 查询工具信息列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllToolInfoResD> QALLGetAllToolsNewRes(BaseRequest req);

    //dto查询工具信息，根据ToolRd(工具ID)
    GetToolInfoRes GetGetToolInfoRes(GetToolInfoReqBD00 argGetToolInfoReqBD00);


    //dto新增工具信息
    SaveToolInfoRes AddGetToolInfoRes(SaveToolInfoReqBD00 argSaveToolInfoReqBD00);

    //dto删除工具信息 根据传过来的工具id
    SaveToolInfoRes RmSaveToolInfoRes(SaveToolInfoReqBD01 argSaveToolInfoReqBD01);

    //tdo更新工具信息
    SaveToolInfoRes ModSaveToolInfoRes(SaveToolInfoReqBD02 argSaveToolInfoReqBD02);

    //复制更新工具信息
    SaveToolInfoRes AddSaveToolInfoRes(SaveToolInfoReqBD03 argSaveToolInfoReqBD03);

}
