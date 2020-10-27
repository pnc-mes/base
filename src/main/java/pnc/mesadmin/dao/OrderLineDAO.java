package pnc.mesadmin.dao;

import pnc.mesadmin.entity.OrderLineinfo;

import java.util.List;

/**
 * Created by PNC on 2018/7/25.
 */
public interface OrderLineDAO {

    //查询所有工单号
    List<OrderLineinfo> SelectAllWoGdOrderLineinfo();

    List<OrderLineinfo> SelectOrderLineByGuid(String guid);

    OrderLineinfo SelectOLineByGuid(String guid);

    void InsertOrderLineinfo(OrderLineinfo orderLineinfo);

    int UpdateOrderLineinfo(OrderLineinfo orderLineinfo);

    //删除
    int deleteOrderLineinfoByruid(int ruid);
}
