package pnc.mesadmin.dao;

import pnc.mesadmin.entity.RePrintLogInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：重打记录信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-07-14
 * 修改人：
 * 修改时间：
 */
public interface RePrintLogDAO {

    //新增重打记录信息  (By-pjf)
    int InsertRePrintLog(RePrintLogInfo argRePrintLogInfo);
}
