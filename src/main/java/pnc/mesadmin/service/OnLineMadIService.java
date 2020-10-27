package pnc.mesadmin.service;

import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoReq;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoResB;

/**
 * Created by zhaochao on 11/6 0006.
 */
public interface OnLineMadIService {

    SaveYMInfoResB AddYMInfo(SaveYMInfoReq objSaveYMInfoReq);
}
