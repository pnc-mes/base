package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevExpectLogDAO;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpReq;
import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpResponse;
import pnc.mesadmin.service.BcirExcpService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2018-12-12
 **/
@Service
@Transactional
public class BcirExcpServiceImpl implements BcirExcpService {

    @Resource
    private DevExpectLogDAO devExpectLogDAO;

    @Override
    public BaseRes GetAllBcirExcp(GetAllBcirExcpReq request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllBcirExcpResponse> list = devExpectLogDAO.SelectAllBCirExcpInfoByReq(request);
        baseResBody.setData(list);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }
}
