package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoRes;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoResD;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoRes;
import pnc.mesadmin.dto.GetPtInfo.GetPtInfoReqBD00;
import pnc.mesadmin.dto.GetPtInfo.GetPtInfoRes;
import pnc.mesadmin.dto.SavePtInfo.SavePtInfoReqBD00;
import pnc.mesadmin.dto.SavePtInfo.SavePtInfoReqBD01;
import pnc.mesadmin.dto.SavePtInfo.SavePtInfoReqBD02;
import pnc.mesadmin.dto.SavePtInfo.SavePtInfoRes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：模板管理信息业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface PrintTIService {

    GetPtBFInfoRes QALLPrintTFInfo() throws SystemException;

    //获取全部打印模板信息
    GetAllPtInfoRes QALLPrintTInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //获取打印模板信息
    GetPtInfoRes GetPrintTInfo(GetPtInfoReqBD00 argBD00) throws SystemException;

    //新增打印模板信息
    SavePtInfoRes AddPrintTInfo(HttpServletRequest request, SavePtInfoReqBD00 argBD00) throws SystemException, IOException;

    //删除打印模板信息
    SavePtInfoRes RmPrintTInfo(SavePtInfoReqBD01 argBD01) throws SystemException;

    //编辑打印模板信息
    SavePtInfoRes ModPrintTInfo(HttpServletRequest request, SavePtInfoReqBD02 argBD02) throws SystemException, IOException;

    /**
     * 获取打印模板列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllPtInfoResD> QALLPrintTNew(BaseRequest req);
}
