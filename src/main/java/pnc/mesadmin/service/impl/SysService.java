package pnc.mesadmin.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoResB;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoResD;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoReq;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoRes;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoResB;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoResD;
import pnc.mesadmin.dto.GetSysInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.SysIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by xfxi on 2017/5/11.
 */
@Transactional
@Service
public class SysService implements SysIService {

    @Resource
    private UserDAO userInfoMapper;

    @Resource
    private UserRoleDAO userRoleInfoMapper;

    @Resource
    private RolePmDAO rolePmDAO;

    @Resource
    private ResDAO resDAO;

    @Resource
    private ModuleDAO moduleDAO;

    @Resource
    private DicDAO dicDAO;

    @Resource
    private AppSetDao appSetDao;

    @Resource
    private CSConfigInfoDAO csConfigInfoDAO;

    @Resource
    private CSCSearchRelInfoDAO cscSearchRelInfoDAO;

    @Resource
    private CSCTableRelInfoDAO cscTableRelInfoDAO;

    @Resource
    private CSCTotalRelInfoDAO cscTotalRelInfoDAO;

    @Resource
    private CSSearchInfoDAO csSearchInfoDAO;

    @Resource
    private CSSearchFieldsInfoDAO csSearchFieldsInfoDAO;

    @Resource
    private CSSearchDDInfoDAO csSearchDDInfoDAO;

    @Resource
    private CSTableDAO csTableDAO;

    @Resource
    private CSTableColumnsDAO csTableColumnsDAO;

    @Resource
    private CSTableSearchDAO csTableSearchDAO;

    @Resource
    private CSTotalDAO csTotalDAO;

    @Resource
    private CSTotalColumnsDAO csTotalColumnsDAO;

    @Resource
    private CSTotalSearchDAO csTotalSearchDAO;

    @Resource
    private SysVerDao sysVerDao;


    //获取系统信息
    public GetSysInfoRes getSysInfo(GetSysInfoReq getSysInfoReq) throws SystemException {
        GetSysInfoRes objEGetSysInfoRes = new GetSysInfoRes();
        GetSysInfoResB objEGetSysInfoRB = new GetSysInfoResB();
        GetSysInfoResD objEGetSysInfoRBDT = new GetSysInfoResD();
        List<GetSysInfoResDMenuInfo> objEGetSysInfoRBDTMList = new ArrayList<GetSysInfoResDMenuInfo>();

        //获取当前系统激活版本
        List<SysVerInfo> sysVerInfos = sysVerDao.SelectAllByStatus("00");
        if (sysVerInfos.size() != 1) {
            throw new SystemException("EEEE", "请检查当前系统版本激活状态！");
        }

        //获取所有
        //当前用户
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        if ("admin".equals("1") || "system".equals("system")) {
            //查询所有一级模块
            List<ModuleInfo> objEModuleInfos = moduleDAO.SelectAllChildModuleAdminBySysVerGd("/", sysVerInfos.get(0).getGuid());
            if (objEModuleInfos != null && objEModuleInfos.size() > 0) {

                objEGetSysInfoRBDTMList = getMenuInfo(objEModuleInfos, "system");
            }
        } else {
            //根据UserRd查用户
            UserInfo userInfo = userInfoMapper.SelectUserInfoByruid(getSysInfoReq.getUserRd());

            //根据用户查询角色
            List<UserRoleInfo> userRoleInfos = userRoleInfoMapper.SelectUserRoleInfoByuserGd(userInfo.getGuid());

            if (userRoleInfos != null || userRoleInfos.size() > 0) {

                //查询所有一级模块
                List<ModuleInfo> objEModuleInfos = moduleDAO.SelectAllChildModule("/", "01", sysVerInfos.get(0).getGuid());
                if (objEModuleInfos != null && objEModuleInfos.size() > 0) {

                    objEGetSysInfoRBDTMList = getMenuInfo(userRoleInfos, objEModuleInfos);
                }
            }
        }

        //TODO AppSetInfo
        AppSetInfo appSetInfo = appSetDao.SelectOneByAppSetInfo(1);
        if (appSetInfo != null) {
            objEGetSysInfoRBDT.setAppLogo(appSetInfo.getAppLogo());
            objEGetSysInfoRBDT.setAppName(appSetInfo.getAppName());
        }

        objEGetSysInfoRBDT.setMenuInfo(objEGetSysInfoRBDTMList);
        objEGetSysInfoRB.setData(objEGetSysInfoRBDT);
        objEGetSysInfoRes.setBody(objEGetSysInfoRB);

        return objEGetSysInfoRes;
    }

    @Override
    public GetLPInfoResB GetLPInfo(String lanCode) {
        GetLPInfoResB objGetLPInfoResB = new GetLPInfoResB();
        List<GetLPInfoResD> objGetLPInfoResDList = new ArrayList<GetLPInfoResD>(Collections.<GetLPInfoResD>emptyList());
        GetLPInfoResD objGetLPInfoResD = null;
        List<DicInfo> dicInfoList = new ArrayList<DicInfo>(Collections.<DicInfo>emptyList());
        dicInfoList = dicDAO.getDicInfoListByLanCode(lanCode);
        for (DicInfo dicInfo : dicInfoList) {
            objGetLPInfoResD = new GetLPInfoResD();
            objGetLPInfoResD.setLabelID(dicInfo.getLabelID());
            objGetLPInfoResD.setLabelDes(dicInfo.getLabelDes());
            objGetLPInfoResDList.add(objGetLPInfoResD);
        }
        objGetLPInfoResB.setMsgCode("0x00000");
        objGetLPInfoResB.setMsgDes("成功");
        objGetLPInfoResB.setData(objGetLPInfoResDList);
        return objGetLPInfoResB;
    }

    //获取渲染数据
    @Override
    public String getModel(HttpServletRequest request, String id, Map<String, String> map) {

        //拼接表格table
        StringBuffer model = new StringBuffer("");
        //根据传入的ID查询配置信息表
        if (id == null) {
            throw new SystemException("", "请传入ID");
        }
        //查询配置信息
        CSConfigInfo csConfigInfo = csConfigInfoDAO.SelectCsConfigByRd(Integer.parseInt(id));


        //拼接条件
        StringBuffer inputs = new StringBuffer();
        //设置成影藏的div
        StringBuffer divs = new StringBuffer();
        divs.append("<div  style='width:100%;display: none;margin-top: 10px'id='open' >");

        inputs.append("<div id='xlh'><div  style='width:1200;' ><input value=" + id + " id='id' name='id' style='display: none;' >");
        //配置Guid 查询 设置关联GUid
        CSCSearchRelInfo cscSearchRelInfo = cscSearchRelInfoDAO.SelectcsConfigInfo(csConfigInfo.getGuid());
        //查询设置信息
        CSSearchInfo csSearchInfo = csSearchInfoDAO.SelectCSSearchInfo(cscSearchRelInfo.getCSSearchGd());

        //查询设置条件字段
        List<CSSearchFieldsInfo> csSearchFieldsInfo = csSearchFieldsInfoDAO.SelectCSSearchFieldsInfo(cscSearchRelInfo.getCSSearchGd());


        //一行的数量
        int colNum = csSearchInfo.getColNum();

        //开始隐藏的行数
        int rowNum = csSearchInfo.getRowNum();
        //记录添加的条件数
        int number = 0;
        //记录需要挂时间控件
        StringBuffer time = new StringBuffer();
        //记录日期段

        StringBuffer date = new StringBuffer();

        System.out.println("开始写查询模块:" + new Date());
        for (CSSearchFieldsInfo cs : csSearchFieldsInfo) {
            //查询

            if (number != 0 && number % colNum == 0 && number > colNum * rowNum) {
                //往隐藏的div添加换行
                divs.append("</br>");
            } else if (number != 0 && number % colNum == 0 && number < colNum * rowNum) {
                //往显示的div添加换行
                inputs.append("</br>");
            }
            if (number < colNum * rowNum) {
                //往显示的区域加入条件
                inputs.append("<label for='" + cs.getCdName() + "'>" + cs.getAliasName() + "</label>&emsp;");
                //00#下拉框
                if (cs.getCdType().equals("00")) {
                    //查询下拉框
                    //根据设置条件字段标识查询下拉框
                    CSSearchDDInfo csSearchDDInfo = csSearchDDInfoDAO.SelectCSSearchDDInfo(cs.getGuid());

                    //查询下拉框信息
                    String sql = csSearchDDInfo.getSqlConfig();

                    List<Map<String, String>> Selects = csSearchDDInfoDAO.Select(sql);
                    StringBuffer select = new StringBuffer();
                    select.append("<select id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input'>");
                    for (Map<String, String> m : Selects) {
                        //添加下拉框option
                        select.append("<option value=" + m.get(csSearchDDInfo.getValFiledName()) + ">" + m.get(csSearchDDInfo.getDisFieldName()) + "</option>");
                    }
                    //下拉框添加结束
                    select.append("</select>&emsp;");

                    inputs.append(select);


                }
                //01#文本框
                if (cs.getCdType().equals("01")) {
                    //添加文本
                    inputs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input'>&emsp;");

                }
                //02#日期段
                if (cs.getCdType().equals("02")) {
                    inputs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input' >&emsp;");
                    date.append(cs.getCdName() + "|");
                }
                //03#时间段
                if (cs.getCdType().equals("03")) {
                    inputs.append("<input style='width:276px;' id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input' >&emsp;");
                    time.append(cs.getCdName() + "|");
                }
                //04#多行文本
                if (cs.getCdType().equals("04")) {
                    inputs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='display:none;' value=\"'~'\" /><input class='inputs' style='margin-top: 10px;' type='button' value='请批量输入' />&emsp;");
                }

            }
            if (number == (csSearchFieldsInfo.size() - 1) && csSearchFieldsInfo.size() <= colNum * rowNum) {
                //给显示的div加结尾
                inputs.append("</div>");
            }
            if (number >= colNum * rowNum) {
                //往隐藏的区域加入条件
                divs.append("<label for='" + cs.getCdName() + "'>" + cs.getAliasName() + "</label>&emsp;");
                //00#下拉框
                if (cs.getCdType().equals("00")) {
                    //查询下拉框
                    //根据设置条件字段标识查询下拉框
                    CSSearchDDInfo csSearchDDInfo = csSearchDDInfoDAO.SelectCSSearchDDInfo(cs.getGuid());

                    //查询下拉框信息
                    String sql = csSearchDDInfo.getSqlConfig();

                    List<Map<String, String>> Selects = csSearchDDInfoDAO.Select(sql);
                    StringBuffer select = new StringBuffer();
                    select.append("<select id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input'>");
                    for (Map<String, String> m : Selects) {
                        //添加下拉框option
                        select.append("<option value=" + m.get(csSearchDDInfo.getValFiledName()) + ">" + m.get(csSearchDDInfo.getDisFieldName()) + "</option>");
                    }
                    //下拉框添加结束
                    select.append("</select>");
                    divs.append(select);

                }
                //01#文本框
                if (cs.getCdType().equals("01")) {
                    //添加文本
                    divs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input'>&emsp;");
                }
                //02#日期段
                if (cs.getCdType().equals("02")) {
                    divs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input' >&emsp;");
                    date.append(cs.getCdName() + "|");
                }
                //03#时间段
                if (cs.getCdType().equals("03")) {
                    divs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='margin-top: 10px;' class='layui-input' >&emsp;");
                    time.append(cs.getCdName() + "|");
                }
                //04#多行文本
                if (cs.getCdType().equals("04")) {
                    divs.append("<input id='" + cs.getCdName() + "' name='" + cs.getCdName() + "' style='display:none;' value=\"'~'\" /><input class='inputs' style='margin-top: 10px;' type='button' value='请批量输入' />&emsp;");
                }
            } else if (number == colNum * rowNum - 1 && csSearchFieldsInfo.size() > colNum) {
                inputs.append("<span><button class='btn btn-primary cSplit'  type='button'  id='dk'  >打开</button><button class='btn btn-primary cSplit'  type='button' style='margin-left:20px;' id='yc'>隐藏</button></span></div> ");
            }

            number++;
        }

        //查询条件隐藏模块加结尾
        divs.append("</div></div>");
        StringBuffer tj = new StringBuffer("<div id='tjnum'>");
        tj.append("<span style='margin-top: 10px;'>当前记录条数&ensp;<label style='font-weight: 600'>【暂无数据】</label></span>");

        //统计模块
        List<CSCTotalRelInfo> cscTotalRelInfo = cscTotalRelInfoDAO.SelectCSCTotalRelInfo(csConfigInfo.getGuid());

        for (CSCTotalRelInfo cto : cscTotalRelInfo) {
            //查询统计设置
            CSTotalInfo csTotalInfo = csTotalDAO.SelectCSTotalInfo(cto.getCSTotalGd());
            if (csTotalInfo != null) {
                tj.append("<span style='margin-top: 10px;'>" + csTotalInfo.getConfigName() + "&ensp;<label style='font-weight: 600'>【暂无数据】</label></span>");
            }
        }

        tj.append("</div>");
        divs.append(tj);
        System.out.println("查询模块结束:" + new Date());

        //表格抬头
        System.out.println("表格头模块开始:" + new Date());


        //配置Guid 查询 表格设置关联GUid
        CSCTableRelInfo cscTableRelInfo = cscTableRelInfoDAO.SelectcscTableRelInfo(csConfigInfo.getGuid());

        //根据表格设置标识查询表格设置信息
        CSTableInfo csTableInfo = csTableDAO.SelectCSTableInfo(cscTableRelInfo.getCSTableGd());


        StringBuffer tables = new StringBuffer();
        tables.append("</td><div  style='overflow:auto;'>  <table border='1'  style='border:#c6d0de;margin-top:10px;width:max-content;font-size: 15;'><thead id='tableHead'><tr style='height:0px;background-color:#eee;'>");

        //根据表格设置标查询 设置输出字段
        List<CSTableColumnsInfo> csTableColumnsInfo = csTableColumnsDAO.SelectCSTableColumnsInfo(csTableInfo.getGuid());
        for (CSTableColumnsInfo table : csTableColumnsInfo) {

            //00显示
            if (table.getIsDisplay().equals("00")) {

                tables.append("<th style='text-align:center;'>&emsp;" + table.getAliasName() + "&emsp;</th>");


            }
            //01隐藏
            if (table.getIsDisplay().equals("01")) {

                tables.append("<th style='text-align:center;display:none;'>&emsp;" + table.getAliasName() + "&emsp;</th>");

            }


        }
        //表头结束添加结尾符
        tables.append("</tr></thead><tbody id='tableBody'></tbody>");
        System.out.println("表格头模块结束:" + new Date());

        //将显示查询框 隐藏查询框 表格抬头拼接起来
        inputs.append(divs);
        inputs.append(tables);
        //将需要加载时间的样式绑定到session
        HttpSession session = request.getSession();
        //时间段
        session.setAttribute("time", time);
        //日期段
        session.setAttribute("date", date);

        return inputs.toString();


    }

    //表格渲染
    @Override
    public MBaseRes GetTable(Map<String, String> map) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        //根据传入的ID查询配置信息表
        String id = map.get("id");
        if (id == null || "".equals(id)) {
            throw new SystemException("", "请传入ID");
        }
        //查询配置信息
        CSConfigInfo csConfigInfo = csConfigInfoDAO.SelectCsConfigByRd(Integer.parseInt(id));

        //配置Guid 查询 表格设置关联GUid
        CSCTableRelInfo cscTableRelInfo = cscTableRelInfoDAO.SelectcscTableRelInfo(csConfigInfo.getGuid());

        //根据表格设置标识查询表格设置信息
        CSTableInfo csTableInfo = csTableDAO.SelectCSTableInfo(cscTableRelInfo.getCSTableGd());


        //根据表格设置标识 查询条件配置 sql的条件
        List<CSTableSearchInfo> csTableSearchInfo = csTableSearchDAO.SelectCSTableSearchInfo(csTableInfo.getGuid());

        //根据表格设置标查询 设置输出字段
        List<CSTableColumnsInfo> csTableColumnsInfo = csTableColumnsDAO.SelectCSTableColumnsInfo(csTableInfo.getGuid());
        //配置Guid 查询 设置关联GUid
        CSCSearchRelInfo cscSearchRelInfo = cscSearchRelInfoDAO.SelectcsConfigInfo(csConfigInfo.getGuid());
        //查询设置条件字段
        List<CSSearchFieldsInfo> csSearchFieldsInfo = csSearchFieldsInfoDAO.SelectCSSearchFieldsInfo(cscSearchRelInfo.getCSSearchGd());

        //用于存储查询后的信息
        List<Map<String, String>> listmaps = new ArrayList<>();
        //sql 替换查询信息
        if (map.size() > 1) {
            //查询表格的sql
            String tableSql = csTableInfo.getSqlConfig();

            //循环取出KEY
            String[] sqlArr = tableSql.split("WHERE");
            if (sqlArr.length != 2) {
                sqlArr = tableSql.split("where");
            }
            if (sqlArr.length > 1) {
                for (CSSearchFieldsInfo cs : csSearchFieldsInfo) {
                    String key = cs.getCdName();
                    String th = map.get(key);
                    if (th == null || th.equals("")) {
                        if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                            throw new SystemException("", "请选择时间!");
                        } else {
                            if (!sqlArr[1].contains("like") && !sqlArr[1].contains("LIKE")) {
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key, "1");
                                sqlArr[1] = sqlArr[1].replaceAll(key, "1");
                            } else {
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key, "");
                            }
                        }

                    } else {
                        if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                            String[] tharr = th.split(" ");
                            if ("02".equals(cs.getCdType())) {
                                //日期段
                                String th1 = tharr[0] + " 00:00:00";
                                String th2 = tharr[tharr.length - 1] + " 23:59:59";
                                String date1 = tharr[0].replace("-", "/");
                                String date2 = tharr[tharr.length - 1].replace("-", "/");

                                long num = (new Date(date2).getTime() - new Date(date1).getTime());//(1000*3600*24));//求出两个时间的时间差，这个是天数
                                double s = num / (1000 * 3600 * 24);
                                /*if(s>7){
                                    throw new SystemException("","查询时间请勿超出一周,周期较长建议分批查询或分批导出!");
                                }*/
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_start", th1);
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_end", th2);

                            } else {
                                //时间段
                                String th1 = tharr[0] + " " + tharr[1];
                                String th2 = tharr[tharr.length - 2] + " " + tharr[tharr.length - 1];
                                String date1 = th1.replace("-", "/");
                                String date2 = th2.replace("-", "/");

                                long num = (new Date(date2).getTime() - new Date(date1).getTime());//(1000*3600*24));//求出两个时间的时间差，这个是天数
                                double s = num / (1000 * 3600 * 24);
                                /*if(s>7){
                                    throw new SystemException("","查询时间请勿超出一周,周期较长建议分批查询或分批导出!");
                                }*/

                                //遍历字符串数组
                                for(int i=0;i<sqlArr.length;i++){
                                    if(sqlArr[i].contains("@" + key + "_start")){
                                        sqlArr[i] = sqlArr[i].replaceAll("@" + key + "_start", th1);
                                        sqlArr[i] = sqlArr[i].replaceAll("@" + key + "_end", th2);
                                    }
                            }
                            }


                        } else {
                            sqlArr[1] = sqlArr[1].replaceAll("@" + key, th);
                        }
                    }

                }
                tableSql = sqlArr[0] + " where " + sqlArr[1];

            }

            //判断sql语句是否还存在@
            if (tableSql.contains("@")) {
                throw new SystemException("", "请检查查询条件是否完整!");
            }
            //执行sql语句
            listmaps = csTableDAO.SelectSQL(tableSql);


        }


        //用于存储查询统计的信息
        List<Map<String, Integer>> listmaptj = new ArrayList<Map<String, Integer>>();
        //配置与统计设置关系关联GUid
        List<CSCTotalRelInfo> cscTotalRelInfo = cscTotalRelInfoDAO.SelectCSCTotalRelInfo(csConfigInfo.getGuid());

        for (CSCTotalRelInfo cto : cscTotalRelInfo) {
            //查询统计设置
            CSTotalInfo csTotalInfo = csTotalDAO.SelectCSTotalInfo(cto.getCSTotalGd());
            if (csTotalInfo != null) {
                //替换sql
                String tableSql = csTotalInfo.getSqlConfig();

                //循环取出KEY
                String[] sqlArr = tableSql.split("WHERE");
                if (sqlArr.length != 2) {
                    sqlArr = tableSql.split("where");
                }
                if (sqlArr.length > 1) {
                    for (CSSearchFieldsInfo cs : csSearchFieldsInfo) {
                        String key = cs.getCdName();
                        String th = map.get(key);
                        if (th == null || th.equals("")) {
                            if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                                throw new SystemException("", "请选择时间!");
                            } else {
                                if (!sqlArr[1].contains("like") && !sqlArr[1].contains("LIKE")) {
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key, "1");
                                    sqlArr[1] = sqlArr[1].replaceAll(key, "1");
                                } else {
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key, "");
                                }
                            }

                        } else {
                            if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                                String[] tharr = th.split(" ");
                                if ("02".equals(cs.getCdType())) {
                                    //日期段
                                    String th1 = tharr[0] + " 00:00:00";
                                    String th2 = tharr[tharr.length - 1] + " 23:59:59";
                                    String date1 = tharr[0].replace("-", "/");
                                    String date2 = tharr[tharr.length - 1].replace("-", "/");

                                    long num = (new Date(date2).getTime() - new Date(date1).getTime());//(1000*3600*24));//求出两个时间的时间差，这个是天数
                                    double s = num / (1000 * 3600 * 24);
                                    /*if(s>7){
                                        throw new SystemException("","查询时间请勿超出一周,周期较长建议分批查询或分批导出!");
                                    }*/
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_start", th1);
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_end", th2);

                                } else {
                                    //时间段
                                    String th1 = tharr[0] + " " + tharr[1];
                                    String th2 = tharr[tharr.length - 2] + " " + tharr[tharr.length - 1];
                                    String date1 = th1.replace("-", "/");
                                    String date2 = th2.replace("-", "/");

                                    long num = (new Date(date2).getTime() - new Date(date1).getTime());//(1000*3600*24));//求出两个时间的时间差，这个是天数
                                    double s = num / (1000 * 3600 * 24);
                                    /*if(s>7){
                                        throw new SystemException("","查询时间请勿超出一周,周期较长建议分批查询或分批导出!");
                                    }*/
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_start", th1);
                                    sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_end", th2);
                                }


                            } else {
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key, th);
                            }
                        }

                    }
                    tableSql = sqlArr[0] + " where " + sqlArr[1];

                }

                //判断sql语句是否还存在@
                if (tableSql.contains("@")) {
                    throw new SystemException("", "请检查查询条件是否完整!");
                }
                //执行sql语句
                Map<String, Integer> maps = csTableDAO.SelectSQL1(tableSql);
                Map<String, Integer> mapa = new HashMap<>();
                for (String key : maps.keySet()) {
                    mapa.put(csTotalInfo.getConfigName(), maps.get(key));
                }

                listmaptj.add(mapa);

            }

        }


        //统计设置查询条件配置
        // List<CSTotalSearchInfo> csTotalSearchInfo=csTotalSearchDAO.SelectCSTotalSearchInfo(csTotalInfo.getGuid());


        List<Map<String, String>> listmaps1 = new ArrayList<>();
        Map<String, String> maps = new HashedMap();
        for (int m = 0; m < listmaps.size(); m++) {
            maps = listmaps.get(m);
            if (maps.size() > 0) {
                Map<String, String> maps1 = new HashedMap();
                //循环列的list
                for (CSTableColumnsInfo s : csTableColumnsInfo) {
                    Object o = maps.get(s.getColumnName());
                    String value = "";
                    if (o != null && o instanceof Date) {
                        Date d = (Date) o;
                        value = CommonUtils.getFormat(d);
                    } else {
                        Object object = maps.get(s.getColumnName());
                        if (object == null)
                            value = "";
                        else
                            value = object.toString();
                        //todo 这种方法Intger类型会报错
                        //value = maps.get(s.getColumnName()) == null ? "" : maps.get(s.getColumnName()).toString();

                    }
                    maps1.put(s.getColumnName(), value);
                }
                listmaps1.add(maps1);
            }

        }


        Map<String, Object> m = new HashedMap();
        //表格内容的样式及显示内容
        m.put("tableType", csTableColumnsInfo);
        m.put("tableBody", listmaps1);
        m.put("tableTop", listmaptj);
        baseResBody.setData(m);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }


    //导出
    @Override
    public ByteArrayOutputStream getAllExcel(Map<String, String> map) throws SystemException {
//根据传入的ID查询配置信息表
        String id = map.get("id");
        if (id == null || "".equals(id)) {
            throw new SystemException("", "请传入ID");
        }
        //查询配置信息
        CSConfigInfo csConfigInfo = csConfigInfoDAO.SelectCsConfigByRd(Integer.parseInt(id));

        //配置Guid 查询 表格设置关联GUid
        CSCTableRelInfo cscTableRelInfo = cscTableRelInfoDAO.SelectcscTableRelInfo(csConfigInfo.getGuid());

        //根据表格设置标识查询表格设置信息
        CSTableInfo csTableInfo = csTableDAO.SelectCSTableInfo(cscTableRelInfo.getCSTableGd());


        //根据表格设置标识 查询条件配置 sql的条件
        List<CSTableSearchInfo> csTableSearchInfo = csTableSearchDAO.SelectCSTableSearchInfo(csTableInfo.getGuid());

        //根据表格设置标查询 设置输出字段
        List<CSTableColumnsInfo> csTableColumnsInfo = csTableColumnsDAO.SelectCSTableColumnsInfo(csTableInfo.getGuid());
        //配置Guid 查询 设置关联GUid
        CSCSearchRelInfo cscSearchRelInfo = cscSearchRelInfoDAO.SelectcsConfigInfo(csConfigInfo.getGuid());
        //查询设置条件字段
        List<CSSearchFieldsInfo> csSearchFieldsInfo = csSearchFieldsInfoDAO.SelectCSSearchFieldsInfo(cscSearchRelInfo.getCSSearchGd());

        //查询表格的sql
        String tableSql = csTableInfo.getSqlConfig();
        //sql 替换查询信息
        if (map.size() > 1) {


            //循环取出KEY
            String[] sqlArr = tableSql.split("WHERE");
            if (sqlArr.length != 2) {
                sqlArr = tableSql.split("where");
            }
            if (sqlArr.length > 1) {
                for (CSSearchFieldsInfo cs : csSearchFieldsInfo) {
                    String key = cs.getCdName();
                    String th = map.get(key);
                    if (th == null || th.equals("")) {
                        if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                            throw new SystemException("", "请选择时间!");
                        } else {
                            if (!sqlArr[1].contains("like") && !sqlArr[1].contains("LIKE")) {
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key, "1");
                                sqlArr[1] = sqlArr[1].replaceAll(key, "1");
                            } else {
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key, "");
                            }
                        }

                    } else {
                        if ("02".equals(cs.getCdType()) || "03".equals(cs.getCdType())) {
                            String[] tharr = th.split(" ");
                            if ("02".equals(cs.getCdType())) {
                                //日期段
                                String th1 = tharr[0];
                                String th2 = tharr[tharr.length - 1];
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_start", th1);
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_end", th2);

                            } else {
                                //时间段
                               /* String th1 = tharr[0] + " " + tharr[1];
                                String th2 = tharr[tharr.length - 2] + " " + tharr[tharr.length - 1];
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_start", th1);
                                sqlArr[1] = sqlArr[1].replaceAll("@" + key + "_end", th2);*/
                                String th1 = tharr[0] + " " + tharr[1];
                                String th2 = tharr[tharr.length - 2] + " " + tharr[tharr.length - 1];
                                for(int i=0;i<sqlArr.length;i++){
                                    if(sqlArr[i].contains("@" + key + "_start")){
                                        sqlArr[i] = sqlArr[i].replaceAll("@" + key + "_start", th1);
                                        sqlArr[i] = sqlArr[i].replaceAll("@" + key + "_end", th2);
                                    }
                                }

                            }

                        } else {
                            sqlArr[1] = sqlArr[1].replaceAll("@" + key, th);
                        }
                    }

                }
                tableSql = sqlArr[0] + " where " + sqlArr[1];

            }
            //判断sql语句是否还存在@
            if (tableSql.contains("@")) {
                throw new SystemException("", "请检查查询条件是否完整!");
            }


        }


        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //查询导出信息
        List<Map<String, String>> listmaps = csTableDAO.SelectSQL(tableSql);
        ;

        /*if(listmaps.size()>10000){
            throw new SystemException("","已选"+listmaps.size()+"条数据,导出数据量过大,请勿超出10000条请分批导出!");

        }*/
        if (listmaps == null || listmaps.size() <= 0) {
            throw new SystemException("", "无信息");
        }
        try {


            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet1 = wb.createSheet("数据");
            //默认15个字节宽度
            sheet1.setDefaultColumnWidth((short) 20);
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);
            // 生成一个样式
            HSSFCellStyle style = wb.createCellStyle();
            // 设置这些样式
            //居中
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            //  style.setFillForegroundColor((short) 2);// 设置背景色

            // 生成一个字体
            HSSFFont font = wb.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 12);
            // 把字体应用到当前的样式
            style.setFont(font);

            //表头数据
            //  String[] s = {};
            HSSFRow row1;
            HSSFCell cell1;
            int cl = 0;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行
                for (int j = 0; j < csTableColumnsInfo.size(); j++) {
                    if (csTableColumnsInfo.get(j).getIsDisplay().equals("00")) {
                        cell1 = row1.createCell(cl);//根据表格行创建单元格
                        cell1.setCellStyle(style);
                        cell1.setCellValue(String.valueOf(csTableColumnsInfo.get(j).getAliasName()));
                        cl++;
                    }
                }
            }
            /**********************************写入Excel************************************/
            HSSFSheet sheet4 = wb.getSheet("数据");
            HSSFRow row4;
            HSSFCell cell4;
            // 生成一个样式
            HSSFCellStyle style2 = wb.createCellStyle();
            // 设置这些样式
            //居中
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //建表

            int i = 1;
            String value = "";
            for (Map<String, String> maps : listmaps) {
                row4 = sheet4.createRow(i++);//创建表格行
                int cls = 0;
                for (int j = 0; j < csTableColumnsInfo.size(); j++) {
                    if (csTableColumnsInfo.get(j).getIsDisplay().equals("00")) {
                        cell4 = row4.createCell(cls);//根据表格行创建单元格
                        cell4.setCellStyle(style2);

                        Object o = maps.get(csTableColumnsInfo.get(j).getColumnName());

                        if (o != null && o instanceof Date) {
                            Date d = (Date) o;
                            value = CommonUtils.getFormat(d);

                        } else {
                            Object object = maps.get(csTableColumnsInfo.get(j).getColumnName());
                            if (object == null)
                                value = "";
                            else
                                value = object.toString();
                            //todo 这种方法Intger类型会报错
                            //value = maps.get(csTableColumnsInfo.get(j).getColumnName()) == null ? "" : maps.get(csTableColumnsInfo.get(j).getColumnName()).toString();

                        }
                        cls++;

                        cell4.setCellValue(value);

                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;
        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("", "导出失败！");
            }
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    //搜索菜单信息
    public GetMenuInfoRes getMenuInfo(GetMenuInfoReq getMenuInfoReq) throws SystemException {
        GetMenuInfoRes objEGetMenuInfoRes = new GetMenuInfoRes();
        GetMenuInfoResB objEGetMenuInfoRB = new GetMenuInfoResB();
        List<GetMenuInfoResD> objEGetMenuInfoRBTList = new ArrayList<GetMenuInfoResD>();

        List<ResInfo> resInfoList = resDAO.SelectResInfoName(getMenuInfoReq.getResName());
        if (resInfoList.size() <= 0 || resInfoList == null) {
            throw new SystemException("MG000001", "没有该资源");
        }

        for (ResInfo resInfo : resInfoList) {
            GetMenuInfoResD data = new GetMenuInfoResD();
            data.setResRd(resInfo.getGuid());
            data.setResName(resInfo.getResName());
            objEGetMenuInfoRBTList.add(data);
        }

        objEGetMenuInfoRB.setData(objEGetMenuInfoRBTList);
        objEGetMenuInfoRes.setBody(objEGetMenuInfoRB);

        return objEGetMenuInfoRes;
    }

    //获取菜单
    public List<GetSysInfoResDMenuInfo> getMenuInfo(List<UserRoleInfo> userRoleInfos, List<ModuleInfo> moduleInfos) {

        List<GetSysInfoResDMenuInfo> objEGetSysInfoResDMenuInfos = new ArrayList<GetSysInfoResDMenuInfo>();//dto
        GetSysInfoResDMenuInfo objEGetSysInfoResDMenuInfo = null;//一级模块

        List<GetSysInfoResDMResInfo> objEGetSysInfoResDMResInfos = new ArrayList<GetSysInfoResDMResInfo>();
        GetSysInfoResDMResInfo objEGetSysInfoResDMResInfo = null;

        List<RolePmInfo> objERolePmInfos = new ArrayList<RolePmInfo>();

        for (int i = 0, length = moduleInfos.size(); i < length; i++) {

            objEGetSysInfoResDMenuInfo = new GetSysInfoResDMenuInfo();
            objEGetSysInfoResDMResInfos = new ArrayList<GetSysInfoResDMResInfo>();

            //查询该模块下的资源
            List<ResInfo> objEResInfos = resDAO.SelectAllModule(moduleInfos.get(i).getGuid(), "01");
            for (int j = 0, length_j = objEResInfos.size(); j < length_j; j++) { //遍历资源

                //判断该角色是否拥有该资源
                for (int k = 0, length_k = userRoleInfos.size(); k < length_k; k++) {

                    objERolePmInfos = rolePmDAO.SelectAllRolePmInfoRes(userRoleInfos.get(k).getRoleGd(), moduleInfos.get(i).getGuid(), objEResInfos.get(j).getGuid());
                    if (objERolePmInfos != null && objERolePmInfos.size() > 0) {

                        objEGetSysInfoResDMResInfo = new GetSysInfoResDMResInfo();

                        //查询资源信息
                        ResInfo resInfo = objEResInfos.get(j);//resDAO.SelectByGuid(objERolePmInfos.get(0).getResGd());
                        if (resInfo != null) {
                            objEGetSysInfoResDMResInfo.setResRd(resInfo.getRuid());
                            objEGetSysInfoResDMResInfo.setResName(resInfo.getResName());
                            objEGetSysInfoResDMResInfo.setResUrl(resInfo.getResUrl());
                            objEGetSysInfoResDMResInfo.setOpenType("iframe-tab");
                            objEGetSysInfoResDMResInfo.setPos(resInfo.getPos());
                            objEGetSysInfoResDMResInfos.add(objEGetSysInfoResDMResInfo);
                        }

                        break;
                    }
                }
            }

            //判断是否存在子模块
            //if("/".equals(moduleInfos.get(i).getParentGd())) {
            //获取当前系统激活版本
            List<SysVerInfo> sysVerInfos = sysVerDao.SelectAllByStatus("00");
            if (sysVerInfos.size() != 1) {
                throw new SystemException("EEEE", "请检查当前系统版本激活状态！");
            }
            List<ModuleInfo> objEChildModules = moduleDAO.SelectAllChildModule(moduleInfos.get(i).getGuid(), "01", sysVerInfos.get(0).getGuid());
            if (objEChildModules != null || objEChildModules.size() > 0) {  //存在子模块

                objEGetSysInfoResDMenuInfo.setMenuInfo(getMenuInfo(userRoleInfos, objEChildModules));
            }
            //}

            objEGetSysInfoResDMenuInfo.setResInfo(objEGetSysInfoResDMResInfos);

            if (objEGetSysInfoResDMResInfos != null && objEGetSysInfoResDMResInfos.size() > 0
                    || objEGetSysInfoResDMenuInfo.getMenuInfo() != null && objEGetSysInfoResDMenuInfo.getMenuInfo().size() > 0) {
                objEGetSysInfoResDMenuInfo.setModuleRd(moduleInfos.get(i).getRuid());
                objEGetSysInfoResDMenuInfo.setModuleName(moduleInfos.get(i).getModuleName());
                objEGetSysInfoResDMenuInfo.setPos(moduleInfos.get(i).getPos());
                objEGetSysInfoResDMenuInfos.add(objEGetSysInfoResDMenuInfo);
            }
        }

        return objEGetSysInfoResDMenuInfos;
    }

    //获取所有菜单
    public List<GetSysInfoResDMenuInfo> getMenuInfo(List<ModuleInfo> moduleInfos, String userName) {
        List<GetSysInfoResDMenuInfo> objEGetSysInfoResDMenuInfos = new ArrayList<GetSysInfoResDMenuInfo>();//dto
        GetSysInfoResDMenuInfo objEGetSysInfoResDMenuInfo = null;//一级模块

        List<GetSysInfoResDMResInfo> objEGetSysInfoResDMResInfos = null;
        GetSysInfoResDMResInfo objEGetSysInfoResDMResInfo = null;

        for (int i = 0, length = moduleInfos.size(); i < length; i++) {

            objEGetSysInfoResDMenuInfo = new GetSysInfoResDMenuInfo();
            objEGetSysInfoResDMResInfos = new ArrayList<GetSysInfoResDMResInfo>();

            //查询该模块下的资源
            List<ResInfo> objEResInfos = resDAO.SelectAllModuleAdmin(moduleInfos.get(i).getGuid());
            for (int j = 0, length_j = objEResInfos.size(); j < length_j; j++) { //遍历资源
                if ("admin".equals(userName) && "00".equals(objEResInfos.get(j).getIsClose())) {
                    continue;
                }
                objEGetSysInfoResDMResInfo = new GetSysInfoResDMResInfo();

                //查询资源信息
                ResInfo resInfo = objEResInfos.get(j);
                objEGetSysInfoResDMResInfo.setResRd(resInfo.getRuid());
                objEGetSysInfoResDMResInfo.setResName(resInfo.getResName());
                objEGetSysInfoResDMResInfo.setResUrl(resInfo.getResUrl());
                objEGetSysInfoResDMResInfo.setOpenType("iframe-tab");
                objEGetSysInfoResDMResInfo.setPos(resInfo.getPos());
                objEGetSysInfoResDMResInfos.add(objEGetSysInfoResDMResInfo);
            }

            objEGetSysInfoResDMenuInfo.setResInfo(objEGetSysInfoResDMResInfos);

            //判断是否存在子模块
            List<ModuleInfo> objEChildModules = moduleDAO.SelectAllChildModuleAdmin(moduleInfos.get(i).getGuid());
            if (objEChildModules != null || objEChildModules.size() > 0) {  //存在子模块

                objEGetSysInfoResDMenuInfo.setMenuInfo(getMenuInfo(objEChildModules, userName));
            }

            if (objEGetSysInfoResDMResInfos != null && objEGetSysInfoResDMResInfos.size() > 0
                    || objEGetSysInfoResDMenuInfo.getMenuInfo() != null && objEGetSysInfoResDMenuInfo.getMenuInfo().size() > 0) {
                objEGetSysInfoResDMenuInfo.setModuleRd(moduleInfos.get(i).getRuid());
                objEGetSysInfoResDMenuInfo.setModuleName(moduleInfos.get(i).getModuleName());
                objEGetSysInfoResDMenuInfo.setResUrl(moduleInfos.get(i).getResUrl());
                objEGetSysInfoResDMenuInfo.setOpenType(moduleInfos.get(i).getLinkType());
                objEGetSysInfoResDMenuInfo.setPos(moduleInfos.get(i).getPos());
                objEGetSysInfoResDMenuInfos.add(objEGetSysInfoResDMenuInfo);
            }
        }

        return objEGetSysInfoResDMenuInfos;
    }
}
