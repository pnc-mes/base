package pnc.mesadmin.dao;

import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpReq;
import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpResponse;
import pnc.mesadmin.entity.BCirExcpInfo;
import pnc.mesadmin.entity.DevExpectLogInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 17:45
 * @Description:
 */
public interface DevExpectLogDAO {
    void InsertDevExpectLogInfo(DevExpectLogInfo devExpectLogInfo);

    void InsertBCirExcpInfo(BCirExcpInfo bCirExcpInfo);

    List<BCirExcpInfo> SelectAllBCirExcpInfo(String batch);

    List<GetAllBcirExcpResponse> SelectAllBCirExcpInfoByReq(GetAllBcirExcpReq requset);
}
