package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoRes;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetIQCBInfo.GetIQCBInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveIQCBInfo.SaveIQCBInfoRes;
import pnc.mesadmin.dto.SaveReq;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2017/6/10.
 */
public interface IQCIService {
    GetAllIQCBInfoRes QAllIQCBInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    GetIQCBInfoRes QIQCBInfo(HttpServletRequest request, GetAllReq getAllReq);

    SaveIQCBInfoRes SaveIQCBInfo(HttpServletRequest request, SaveReq saveReq);

}
