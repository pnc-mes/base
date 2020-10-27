package pnc.mesadmin.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.GetAllNRetMaInfo.GetAllNRetMaInfoResB;
import pnc.mesadmin.dto.GetAllRetMaInfo.GetAllRetMaInfoResB;
import pnc.mesadmin.dto.GetNRMInfo.GetNRMInfoResB;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoReqBD00;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoResB;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoReqBD00;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoResB;
import pnc.mesadmin.dto.GetRetMaInfo.GetRetMaInfoResB;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveNRMInfo.SaveNRMInfoReqBD00;
import pnc.mesadmin.dto.SaveNRMInfo.SaveNRMInfoResB;
import pnc.mesadmin.dto.SaveRMInfoRes.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by test on 2017/9/21.
 */
public interface RetMaInfoIService {
    GetAllRetMaInfoResB GetAllRetMaInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo);

    GetRetMaInfoResB GetRetMaInfoRes(int retRd);

    GetRMTotalInfoResB GetRMTotalInfo(GetRMTotalInfoReqBD00 busData00);

    SaveRMInfoResB AddRMInfo(SaveRMInfoReqBD00 busData00);

    SaveRMInfoResB RmRMInfo(SaveRMInfoReqBD01 busData01);

    SaveRMInfoResB ModRMInfo(SaveRMInfoReqBD02 busData02);

    //下达
    SaveRMInfoResB ModRMInfo03(SaveRMInfoReqBD03 busData03);

    //取消
    SaveRMInfoResB ModRMInfo04(SaveRMInfoReqBD04 busData04);

    //快速退料
    GetAllNRetMaInfoResB GetAllNRetMaInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo);

    //快速退料
    SaveNRMInfoResB AddNRMInfo(SaveNRMInfoReqBD00 busData00);

    //快速退料
    GetNRMInfoResB GetNRetMaInfo(int retRd);

    //快速退料
    GetNRMTotalInfoResB GetNRMTotalInfo(GetNRMTotalInfoReqBD00 busData00, List<InitDataField> initDataFieldList);


    SaveImportResB AddImport(CommonsMultipartFile file);

    ByteArrayOutputStream exportExcel(Integer busData);
}
