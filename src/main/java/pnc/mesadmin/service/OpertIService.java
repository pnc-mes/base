package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoRes;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResD;
import pnc.mesadmin.dto.GetOpertInfo.GetOpertInfoReqBD00;
import pnc.mesadmin.dto.GetOpertInfo.GetOpertInfoRes;
import pnc.mesadmin.dto.SaveOpertInfo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业管理Service接口
 * 创建人：王怀龙
 * 创建时间：2017-06-01
 * 修改人：
 * 修改时间：
 */
public interface OpertIService {
    //获取作业列表信息
    GetAllOpertInfoRes QAllOpertInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    /**
     * 获取作业列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllOpertInfoResD> QALLGetAllOpertNew(BaseRequest req);

    //获取作业信息
    GetOpertInfoRes QOpertInfo(GetOpertInfoReqBD00 argGetOpertInfoReqBD00);
    //新增作业信息
    SaveOpertInfoRes SaveOpertInfo(SaveOpertInfoReqBD00 argSaveOpertInfoReqBD00);
    //删除作业信息
    SaveOpertInfoRes RmSaveOpertInfo(SaveOpertInfoReqBD01 argSaveOpertInfoReqBD01);
    //编辑作业信息
    SaveOpertInfoRes ModSaveOpertInfo(SaveOpertInfoReqBD02 saveOpertInfoReqBD02);
    //复制作业信息
    SaveOpertInfoRes SaveOpertInfoCopy(SaveOpertInfoReqBD03 saveOpertInfoReqBD03);

}
