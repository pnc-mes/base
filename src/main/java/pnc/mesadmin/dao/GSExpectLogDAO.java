package pnc.mesadmin.dao;

import pnc.mesadmin.entity.GSExpectLoginfo;

/**
 * @program: mesadmin
 * @description: 过站异常信息记录DAO
 * @author: yin.yang
 * @create: 2018-10-23
 **/
public interface GSExpectLogDAO {
    //新增
    int InsertGSExpectLogInfo(GSExpectLoginfo gsExpectLoginfo);
}
