package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetMODevInfo.GetMODevInfoReqBD00;
import pnc.mesadmin.dto.GetMODevInfo.GetMODevInfoRes;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoReqBD00;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoRes;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：装料管理逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-08-29
 * 修改人：
 * 修改时间：
 */
public interface MODevIService {

    //获取装料信息
    GetMODevInfoRes GetMODev(GetMODevInfoReqBD00 argBD00);

    //装料操作信息
    SaveMODevInfoRes AddMODev(SaveMODevInfoReqBD00 argBD00);
}
