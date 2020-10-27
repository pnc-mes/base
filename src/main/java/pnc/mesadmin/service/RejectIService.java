package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoRes;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResD1;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoReqBD00;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoRes;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码Service层接口
 * 创建人：郝赞
 * 创建时间：2018-8-6
 * 修改人：
 * 修改时间：
 */
public interface RejectIService {

    //dto查询不良标记信息列表
    GetAllRejectInfoRes GetAllRejectInfoRes(GetAllRejectInfoResD1 getAllRejectInfoResD1);

    //新增不良标记记录
    SaveRejectInfoRes AddReject(SaveRejectInfoReqBD00[] saveRejectInfoReqBD00);
}
