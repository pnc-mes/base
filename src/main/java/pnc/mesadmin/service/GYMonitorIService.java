package pnc.mesadmin.service;

import org.omg.CORBA.SystemException;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJReqBD00;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJRes;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
public interface GYMonitorIService {
    /**
     * 工艺参数预警
     * @param bd00
     * @return
     */
    SaveGYYJRes SaveGYYJ(SaveGYYJReqBD00 bd00) throws SystemException;
}
