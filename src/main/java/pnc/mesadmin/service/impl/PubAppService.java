package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.AppUpDAO;
import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoRes;
import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoResB;
import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoResD;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoReqBD02;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.AppUpInfo;
import pnc.mesadmin.service.PubAppIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.FastfdsUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：发布App业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-09-06
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class PubAppService implements PubAppIService {

    @Resource
    private AppUpDAO appUpDAO;

    //获取发布App信息
    @Override
    public GetPAppInfoRes GetPApp() {

        GetPAppInfoRes objEGetPAppInfoRes = new GetPAppInfoRes();
        GetPAppInfoResB objEGetPAppInfoResB = new GetPAppInfoResB();

        //查询App信息
        List<AppUpInfo> objEAppUpInfos = appUpDAO.SeectAllAppUp();
        if(objEAppUpInfos != null && objEAppUpInfos.size() > 0){
            GetPAppInfoResD objEGetPAppInfoResD = new GetPAppInfoResD();
            AppUpInfo objEAppUpInfo = objEAppUpInfos.get(0);
            objEGetPAppInfoResD.setAppID(objEAppUpInfo.getAppID());
            objEGetPAppInfoResD.setAppName(objEAppUpInfo.getAppName());
            objEGetPAppInfoResD.setSAppVer(objEAppUpInfo.getsAppVer());
            objEGetPAppInfoResD.setAppVer(objEAppUpInfo.getAppVer());
            objEGetPAppInfoResD.setForUpApp(objEAppUpInfo.getForUpApp());
            objEGetPAppInfoResD.setUpDes(objEAppUpInfo.getUpDes());
            objEGetPAppInfoResB.setData(objEGetPAppInfoResD);
        }

        objEGetPAppInfoRes.setBody(objEGetPAppInfoResB);

        return objEGetPAppInfoRes;
    }

    //保存发布App信息
    @Override
    public SavePAppInfoRes AddPApp(HttpServletRequest request, SavePAppInfoReqBD02 argBD02) {

        SavePAppInfoRes objESavePAppInfoRes = new SavePAppInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        String url = "";
        //上传文件
        Map<String, String> map = FastfdsUtils.upload(request, "apk");
        for(String s : map.values()){
            url = s;
            break;
        }

        AppUpInfo objEAppUpInfo = new AppUpInfo();

        //查询旧记录
        List<AppUpInfo> objEAppUpInfos = appUpDAO.SeectAllAppUp();
        if(objEAppUpInfos != null && objEAppUpInfos.size() > 0){
            objEAppUpInfo = objEAppUpInfos.get(0);
            //删除旧数据
            FastfdsUtils.delete(objEAppUpInfo.getAppUrl());

            if(appUpDAO.DeleteByGuid(objEAppUpInfo.getGuid()) <= 0){
                throw new SystemException("", "App信息修改失败");
            }
        }

        //新增App信息
        objEAppUpInfo.setGuid(CommonUtils.getRandomNumber());
        objEAppUpInfo.setAppID(argBD02.getAppID());
        objEAppUpInfo.setAppName(argBD02.getAppName());
        objEAppUpInfo.setsAppVer(argBD02.getSAppVer());
        objEAppUpInfo.setAppVer(argBD02.getAppVer());
        objEAppUpInfo.setProvider("");
        objEAppUpInfo.setAppUrl(FastfdsUtils.readProps() + url);
        objEAppUpInfo.setForUpApp(argBD02.getForUpApp());
        objEAppUpInfo.setUpDes(argBD02.getUpDes());
        objEAppUpInfo.setCreator(userName);
        objEAppUpInfo.setCreateTime(date);
        appUpDAO.InsertAppUp(objEAppUpInfo);

        return objESavePAppInfoRes;
    }
}
