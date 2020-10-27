package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpReq;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2018-12-12
 **/
public interface BcirExcpService {
    BaseRes GetAllBcirExcp(GetAllBcirExcpReq request);
}
