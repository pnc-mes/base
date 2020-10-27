package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoResB;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoResD;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResB;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoReq00;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoReq02;
import pnc.mesadmin.dto.SavePackSpecificationInfo.SavePackSpecificationInfoResB;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：ZC
 * 创建时间：2017-08-04
 * 修改人：
 * 修改时间：
 */
public interface PackSpecificationIService {

    GetAllPackSPInfoResB GetAllMPKInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    GetPackSpecificationInfoResB GetMPKInfoByRuid(int mpRd);

    SavePackSpecificationInfoResB AddPackSpecification(SavePackSpecificationInfoReq00 busData00);

    SavePackSpecificationInfoResB RmPackSpecification(String mpRd);

    SavePackSpecificationInfoResB ModPackSpecification(SavePackSpecificationInfoReq02 busData02);

    /**
     * 获取包装规则列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllPackSPInfoResD> GetAllMPKNew(BaseRequest req);
}
