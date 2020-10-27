package pnc.mesadmin.service;

import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoReqBD00;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoRes;
import pnc.mesadmin.dto.SystemException;

import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导入库存信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-9-2
 * 修改人：
 * 修改时间：
 */
public interface ToolsIService {
    //导入库存数据
    SaveImportInfoRes AddSaveImportInfoRes(SaveImportInfoReqBD00[] argBD00) throws SystemException;
}
