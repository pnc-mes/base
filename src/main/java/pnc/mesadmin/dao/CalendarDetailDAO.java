package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CalendarDetailInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:18
 * @Description:
 */
public interface CalendarDetailDAO {
    //查询明细所有根据主表guid
    List<CalendarDetailInfo> SelectCalendarDetailDAOByGuid(String guid);

    //删除
    int DeleteCalendarDetailDAOInfoByRuid(int ruid);

    //新增明细
    void InsertCalendarDetailDAO(CalendarDetailInfo calendarDetailInfo);
}
