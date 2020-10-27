package pnc.mesadmin.dto;


import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import pnc.mesadmin.utils.DateUtil;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：自定义异常
 * 创建人：赵超
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SystemException extends RuntimeException {

    protected final Log log = LogFactory.getLog(getClass());

    //消息代码
    private String MsgCode;

    //消息描述
    private String MsgDes;

    public SystemException(String MsgCode,String MsgDes) {
        this.MsgCode = MsgCode;
        this.MsgDes = MsgDes;
        log.warn(/*Thread.currentThread().getName() +*/ ":2  "
                + "消息代码:" + MsgCode + "消息描述:" + MsgDes + " --- "
                /*+ DateUtil.format(new Date())*/
                );
    }

    public String getMsgCode() { return MsgCode; }

    public void setMsgCode(String msgCode) {
        MsgCode = msgCode;
    }

    public String getMsgDes() {
        return MsgDes;
    }

    public void setMsgDes(String msgDes) {
        MsgDes = msgDes;
    }
}
