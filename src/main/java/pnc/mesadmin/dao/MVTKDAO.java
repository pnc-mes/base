package pnc.mesadmin.dao;

import pnc.mesadmin.entity.MVTKInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public interface MVTKDAO {
    List<MVTKInfo> SelectMVTkInfo();

    MVTKInfo SelectMVTkInfoByRuid(int argRuid);

    int InsertMVKTInfo(MVTKInfo mvtkInfo);

    MVTKInfo SelectByGuid(String guid);

    int UpdateMVKTInfo(MVTKInfo update_mvtkInfo);

}
