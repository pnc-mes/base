package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CalendarInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:18
 * @Description:
 */
public interface CalendarDAO {
    //查询所有
    List<CalendarInfo> SelectAllCalendarInfo();

    //查询单个
    CalendarInfo SelectCalendarInfoByRuid(int ruid);

    //新增
    void InsertCalendarInfo(CalendarInfo calendarInfo);

    //删除
    int DeleteCalendarInfoByRuid(int ruid);

    //根据name查询单个信息
    CalendarInfo SelectCalendarInfoByCalendarName(String calendarName);

    //更新UpdateCalendarInfo
    int UpdateCalendarInfo(CalendarInfo calendarInfo);
}
