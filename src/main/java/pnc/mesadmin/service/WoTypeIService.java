package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoResB;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoResD;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoResB;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD00;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD01;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoReqBD02;
import pnc.mesadmin.dto.SaveWoTypeInfo.SaveWoTypeInfoResB;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM管理业务信息逻辑
 * 创建人：ZC
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public interface WoTypeIService {

    GetAllWoTypeInfoResB GetAllWTInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询工单类型列表
    PageResult<GetAllWoTypeInfoResD> QALLGetAllWTNewRes(BaseRequest req);

    GetWoTypeInfoResB GetWTInfo(int wtRd);

    // 删除
    SaveWoTypeInfoResB RmWoType(SaveWoTypeInfoReqBD01 busData01);

    //新增
    SaveWoTypeInfoResB AddWoType(SaveWoTypeInfoReqBD00 busData00);

    //更新
    SaveWoTypeInfoResB ModWoType(SaveWoTypeInfoReqBD02 busData02);
}
