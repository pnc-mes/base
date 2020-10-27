package pnc.mesadmin.service;


import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResB;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResD;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResB;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveRCGpInfo.SaveRCGpInfoReq00;
import pnc.mesadmin.dto.SaveRCGpInfo.SaveRCGpInfoReq01;
import pnc.mesadmin.dto.SaveRCGpInfo.SaveRCGpInfoReq02;
import pnc.mesadmin.dto.SaveRCGpInfo.SaveRCGpInfoResB;

import java.util.List;

public interface ReasongIService {
    PageResult<GetAllRCGInfoResD> QALLReaInfoNew(BaseRequest request);

    GetAllRCGInfoResB GetAllRCGInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    GetRCGInfoResB GetRCGInfoByRuid(int argRuid);

    SaveRCGpInfoResB AddRCGpInfo(SaveRCGpInfoReq00 busData00);

    SaveRCGpInfoResB RmRCGpInfo(SaveRCGpInfoReq01 busData01);

    SaveRCGpInfoResB ModRCGpInfo(SaveRCGpInfoReq02 busData02);
}
