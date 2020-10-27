package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetLPInfo.GetLPInfoResB;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoReq;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoRes;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoReq;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：系统管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
public interface SysIService {

    //获取菜单信息
    GetMenuInfoRes getMenuInfo(GetMenuInfoReq getMenuInfoReq) throws SystemException;

    //获取系统信息
    GetSysInfoRes getSysInfo(GetSysInfoReq getSysInfoReq) throws SystemException;

    //切换语言包
    GetLPInfoResB GetLPInfo(String lanCode);

    //获取渲染数据
    String getModel(HttpServletRequest request, String id, Map<String, String> map);

    //导出
    ByteArrayOutputStream getAllExcel(Map<String, String> id)throws javax.transaction.SystemException;

    //表格渲染
    MBaseRes GetTable(Map<String, String> map);
}
