package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoRes;
import pnc.mesadmin.dto.GetMVMaInfo.GetMVMaInfoReq00;
import pnc.mesadmin.dto.GetMVMaInfo.GetMVMaInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public interface MVIService {
    //查询调拨列表信息
    GetAllMVMaInfoRes QALLGetAllMVMaInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询调拨信息
    GetMVMaInfoRes GetGetMVMaInfoRes(GetMVMaInfoReq00 argGetMVMaInfoReq00);
}
