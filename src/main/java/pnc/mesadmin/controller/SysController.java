package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoReq;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoRes;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoResB;
import pnc.mesadmin.dto.GetLPInfo.GetLPInfoResD;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoReq;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoRes;
import pnc.mesadmin.dto.GetMenuInfo.GetMenuInfoResB;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoReq;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoRes;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.SysIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：系统管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/Sys")
public class SysController {

    @Resource
    private SysIService sysIService;

    //通用查询
    @RequestMapping(value = "/tycxzxPage", method = RequestMethod.GET)
    public String TycxzxPage(HttpServletRequest request, HttpServletResponse response, String id, Model model) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }

        System.out.println("进入接口" + new Date());
        String ss = sysIService.getModel(request, id, map);
        System.out.println("出接口" + new Date());

        model.addAttribute("newsId", ss);
        return "/sys/tycxzx/tycxzxInfo";
    }

    //通用查询渲染表单
    @RequestMapping(value = "/tycxzxTable", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes TycxzxTable(HttpServletRequest request) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResponseB = new MBaseResB();
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
        try {
            System.out.println("进入接口" + new Date());
            baseResponse = sysIService.GetTable(map);
            System.out.println("出接口" + new Date());

        } catch (SystemException e) {
            baseResponseB.setMsgCode(e.getMsgCode());
            baseResponseB.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResponseB);
        }


        baseResponse.setStatus("00");
        return baseResponse;
    }


    @RequestMapping(value = "/printTInfo")
    public String PrintTInfo() {
        return "/base/printT/PrintTInfo";
    }

    //获取打印模版模态框中新增页面信息
    @RequestMapping(value = "/printTFAdd")
    public String PrintTInfoAdd() {
        return "/base/printT/PrintTFAdd";
    }

    //获取打印模版模态框中编辑页面信息
    @RequestMapping(value = "/printTFEdit")
    public String PrintTInfoEdit() {
        return "/base/printT/PrintTFEdit";
    }


    //获取菜单信息
    @RequestMapping(value = "/GetMenuInfo")
    @ResponseBody
    public GetMenuInfoRes GetMenuInfo(GetAllReq getAllReq) {

        GetMenuInfoRes objEGetMenuInfoRes = new GetMenuInfoRes();

        String busData = getAllReq.getBusData();

        try {
            JSONObject jsonObject = JSONObject.fromObject(busData);
            JsonConfig config = new JsonConfig();
            config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {

                @Override
                public String transformToJavaIdentifier(String str) {
                    char[] chars = str.toCharArray();
                    chars[0] = Character.toLowerCase(chars[0]);
                    return new String(chars);
                }
            });
            config.setRootClass(GetMenuInfoReq.class);

            GetMenuInfoReq getMenuInfoReq = (GetMenuInfoReq) JSONObject.toBean(jsonObject, config);
            objEGetMenuInfoRes = sysIService.getMenuInfo(getMenuInfoReq);
            objEGetMenuInfoRes.getBody().setMesCode("0x00000");
            objEGetMenuInfoRes.getBody().setMsgDes("成功");
        } catch (SystemException e) {
            GetMenuInfoResB objEGetMenuInfoRB = new GetMenuInfoResB();
            objEGetMenuInfoRB.setMesCode(e.getMsgCode());
            objEGetMenuInfoRB.setMsgDes(e.getMsgDes());
            objEGetMenuInfoRes.setBody(objEGetMenuInfoRB);
        }

        objEGetMenuInfoRes.setStatus("00");
        return objEGetMenuInfoRes;
    }

    //获取系统信息
 /*   @RequestMapping(value = "/GetSysInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSysInfoRes GetSysInfo(GetAllReq getAllReq) {

        GetSysInfoRes objEGetSysInfoRes = new GetSysInfoRes();

        String busData = getAllReq.getBusData();

        try {
            JSONObject jsonObject = JSONObject.fromObject(busData);
            JsonConfig config = new JsonConfig();
            config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {

                @Override
                public String transformToJavaIdentifier(String str) {
                    char[] chars = str.toCharArray();
                    chars[0] = Character.toLowerCase(chars[0]);
                    return new String(chars);
                }
            });
            config.setRootClass(GetSysInfoReq.class);

            GetSysInfoReq getSysInfoReq = (GetSysInfoReq) JSONObject.toBean(jsonObject, config);
            objEGetSysInfoRes = sysIService.getSysInfo(getSysInfoReq);
            objEGetSysInfoRes.getBody().setMsgCode("0x00000");
            objEGetSysInfoRes.getBody().setMsgDes("成功");
        } catch (SystemException e) {
            GetSysInfoResB objEGetSysInfoRB = new GetSysInfoResB();
            objEGetSysInfoRB.setMsgCode(e.getMsgCode());
            objEGetSysInfoRB.setMsgDes(e.getMsgDes());
            objEGetSysInfoRes.setBody(objEGetSysInfoRB);
        }

        objEGetSysInfoRes.setStatus("00");

        return objEGetSysInfoRes;
    }*/

    //切换语言包
    @ResponseBody
    @RequestMapping(value = "/GetLPInfo", method = RequestMethod.POST)
    public GetLPInfoRes getLPInfo(GetAllReq argGetAllReq) {
        GetLPInfoRes objGetLPInfoRes = new GetLPInfoRes();
        GetLPInfoResB objGetLPInfoResB = new GetLPInfoResB();
        GetLPInfoReq objGetLPInfoReq = new GetLPInfoReq();
        if ("00".equals(argGetAllReq.getExecType())) {
            if (!"".equals(argGetAllReq.getBusData()) && argGetAllReq.getBusData() != null) {
                objGetLPInfoReq = CommonUtils.switchClass(GetLPInfoReq.class, argGetAllReq.getBusData());
                objGetLPInfoResB = sysIService.GetLPInfo(objGetLPInfoReq.getLanCode());
            } else {
                objGetLPInfoResB.setMsgCode("0x00002");
                objGetLPInfoResB.setMsgDes("02_非法请求");
                objGetLPInfoResB.setData(new ArrayList<GetLPInfoResD>(Collections.<GetLPInfoResD>emptyList()));
            }
        } else {
            objGetLPInfoResB.setMsgCode("0x00001");
            objGetLPInfoResB.setMsgDes("01_非法请求");
            objGetLPInfoResB.setData(new ArrayList<GetLPInfoResD>(Collections.<GetLPInfoResD>emptyList()));
        }
        objGetLPInfoRes.setStatus("00");
        objGetLPInfoRes.setBody(objGetLPInfoResB);
        return objGetLPInfoRes;
    }


    //导出查询数据
    @RequestMapping(value = "getExcel", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    public String getExcel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
            System.out.println(paraName + ": " + request.getParameter(paraName));

        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                os = sysIService.getAllExcel(map);
            } catch (SystemException ee) {
                ee.getMessage();
                return "<p>" + ee.getMsgDes() + "</p><p>刷新页面 重新选择查询时间</p>";
            }

            //设置response参数,可以打开下载页面
            String exported = "导出" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
            exported = new String(exported.getBytes("gbk"), "iso8859-1");
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + exported + ".xls");
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                ServletOutputStream out = response.getOutputStream();

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                    }
                }
            }
        } catch (javax.transaction.SystemException e) {
            return e.getMessage();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";

    }

}
