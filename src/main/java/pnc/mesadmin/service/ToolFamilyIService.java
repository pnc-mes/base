package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoRes;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoResD;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoReqBD00;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveToolFamilyInfo.*;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具家族信息Service接口
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public interface ToolFamilyIService {

    //dto查询工具家族信息列表
    GetAllToolFamilyInfoRes QALLGetAllFaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * dto查询工具家族信息列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllToolFamilyInfoResD> QALLGetAllToolFamilyNewRes(BaseRequest req);


    //dto查询工具信息，根据ToolRd(工具ID)
    GetToolFamilyInfoRes GetGetToolFamilyInfoRes(GetToolFamilyInfoReqBD00 argGetToolFamilyInfoReqBD00);


    //dto新增工具信息
    SaveToolFamilyInfoRes AddGetToolFamilyInfoRes(SaveToolFamilyInfoReqBD00 argSaveToolFamilyInfoReqBD00);

    //dto删除工具信息 根据传过来的工具id
    SaveToolFamilyInfoRes RmSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD01 argSaveToolFamilyInfoReqBD01);

    //tdo更新工具信息
    SaveToolFamilyInfoRes ModSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD02 argSaveToolFamilyInfoReqBD02);

    //复制更新工具信息
    SaveToolFamilyInfoRes AddSaveToolFamilyInfoRes(SaveToolFamilyInfoReqBD03 argSaveToolFamilyInfoReqBD03);

}
