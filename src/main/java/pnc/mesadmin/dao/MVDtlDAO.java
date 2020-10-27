package pnc.mesadmin.dao;

import pnc.mesadmin.entity.MVDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public interface MVDtlDAO {
    List<MVDtlInfo> SelectMVDtlInfoBymVTkGd(String argmVTkGd);
    int InsertMVDtlInfo(MVDtlInfo mvDtlInfo);
}
