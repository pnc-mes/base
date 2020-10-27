package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoRes;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoReqBD02;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoRes;

import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：发布App业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-09-06
 * 修改人：
 * 修改时间：
 */
public interface PubAppIService {

    //获取发布App信息
    GetPAppInfoRes GetPApp();

    //保存发布App信息
    SavePAppInfoRes AddPApp(HttpServletRequest request, SavePAppInfoReqBD02 argBD02);
}
