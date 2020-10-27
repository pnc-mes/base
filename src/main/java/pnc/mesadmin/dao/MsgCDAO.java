package pnc.mesadmin.dao;

import pnc.mesadmin.entity.MsgCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：消息配置信息DAO
 * 创建人：刘福志
 * 创建时间：2017-08-25
 * 修改人：
 * 修改时间：
 */
public interface MsgCDAO {
    //查询消息配置所有信息
    List<MsgCInfo> SelectAllMsgCInfo();

    //查询消息配置信息
    MsgCInfo  SelectmsgName(String msgName);

    //保存消息配置信息
    int InsertMsgCInfo(MsgCInfo msgCInfo);

    //更新消息配置信息
    int UpdateMsgCInfo(MsgCInfo msgCInfo);
}
