package pnc.mesadmin.dao;

import pnc.mesadmin.entity.vo.OnlineAfterVo;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/1/18 08:57
 * @Description:
 */
public interface OnlineAfterVoDAO {
    //查询前el的数据
    OnlineAfterVo selectOnlineAfterVoPELDAOByBatch(String batch);

    //压后目检的数据
    OnlineAfterVo selectOnlineAfterVoYHCheckDAOByBatch(String batch);

    //查询后el的数据
    OnlineAfterVo selectOnlineAfterVoTELDAOByBatch(String batch);

    //终检的数据
    OnlineAfterVo selectOnlineAfterVoZJCheckDAOByBatch(String batch);
}
