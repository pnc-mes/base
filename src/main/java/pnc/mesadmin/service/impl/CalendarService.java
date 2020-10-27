package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CalendarDAO;
import pnc.mesadmin.dao.CalendarDetailDAO;
import pnc.mesadmin.dao.ShiftDAO;
import pnc.mesadmin.dao.TeamDAO;
import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoRes;
import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoResB;
import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoResD;
import pnc.mesadmin.dto.GetCalendarInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCalendarInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CalendarDetailInfo;
import pnc.mesadmin.entity.CalendarInfo;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.TeamInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CalendarIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:27
 * @Description:生成日历管理
 */
@Transactional
@Service
public class CalendarService implements CalendarIService {


    @Resource
    private BaseIService baseIService;

    @Resource
    private CalendarDAO calendarDAO;

    @Resource
    private CalendarDetailDAO calendarDetailDAO;

    @Resource
    private TeamDAO teamDAO;

    @Resource
    private ShiftDAO shiftDAO;


    //查询所有
    @Override
    public GetAllCalendarInfoRes QALLGetAllCalendarInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCalendarInfoRes getAllCalendarInfoRes=null;
        GetAllCalendarInfoResB getAllCalendarInfoResB=null;
        List<GetAllCalendarInfoResD> getAllCalendarInfoResDS=new ArrayList<GetAllCalendarInfoResD>();
        GetAllCalendarInfoResD getAllCalendarInfoResD=null;
        List<CalendarInfo> calendarInfos= baseIService.QALLBaseInfo(CalendarDAO.class, "SelectAllCalendarInfo",
                argInitDataFields, argPageInfo);
        getAllCalendarInfoRes=new GetAllCalendarInfoRes();
        getAllCalendarInfoResB=new GetAllCalendarInfoResB();

        if(calendarInfos!=null&&calendarInfos.size()>0){

            for(CalendarInfo calendarInfo:calendarInfos){
                getAllCalendarInfoResD=new GetAllCalendarInfoResD();
                getAllCalendarInfoResD.setCalendarRd(calendarInfo.getRuid());
                getAllCalendarInfoResD.setCalendarName(calendarInfo.getCalendarName());
                getAllCalendarInfoResDS.add(getAllCalendarInfoResD);
            }
        }
        getAllCalendarInfoResB.setData(getAllCalendarInfoResDS);
        getAllCalendarInfoRes.setBody(getAllCalendarInfoResB);
        return getAllCalendarInfoRes;
    }
    //查询单个
    @Override
    public GetCalendarInfoRes GetGetCalendarInfoRes(GetCalendarInfoReq00 getCalendarInfoReq00) {
        GetCalendarInfoRes getCalendarInfoRes=null;
        GetCalendarInfoResB getCalendarInfoResB=null;
        GetCalendarInfoResD getCalendarInfoResD=null;
        List<GetCalendarInfoResDCalendarDetail> getCalendarInfoResDCalendarDetails=new ArrayList<GetCalendarInfoResDCalendarDetail>();
        if(getCalendarInfoReq00.getCalendarRd()==0||"".equals(getCalendarInfoReq00.getCalendarRd())){
            throw new SystemException("xx","查询生产日历信息不能为空");
        }
        CalendarInfo calendarInfo=calendarDAO.SelectCalendarInfoByRuid(getCalendarInfoReq00.getCalendarRd());
        if(calendarInfo!=null){
            getCalendarInfoRes=new GetCalendarInfoRes();
            getCalendarInfoResB=new GetCalendarInfoResB();
            getCalendarInfoResD=new GetCalendarInfoResD();
            List<CalendarDetailInfo> calendarDetailInfos=calendarDetailDAO.SelectCalendarDetailDAOByGuid(calendarInfo.getGuid());
            if(calendarDetailInfos!=null&&calendarDetailInfos.size()>0){
                for(CalendarDetailInfo calendarDetailInfo:calendarDetailInfos){
                    GetCalendarInfoResDCalendarDetail getCalendarInfoResDCalendarDetail=new GetCalendarInfoResDCalendarDetail();
                    getCalendarInfoResDCalendarDetail.setCalendarDate(DateUtil.formatPattern2(calendarDetailInfo.getCalendarDate()));
                    GetCalendarInfoResDCalendarDetail.TeamInfo teamInfos=new GetCalendarInfoResDCalendarDetail.TeamInfo();
                    TeamInfo teamInfo=teamDAO.SelectTeamInfoByGuid(calendarDetailInfo.getTeamGD());
                    if(teamInfo!=null){
                        teamInfos.setTeamRd(teamInfo.getRuid());
                        teamInfos.setTeamName(teamInfo.getTeamName());
                        getCalendarInfoResDCalendarDetail.setTeamInfo(teamInfos);
                    }
                    ShiftInfo shiftInfo=shiftDAO.SelectByGuid(calendarDetailInfo.getShiftGD());
                    if(shiftInfo!=null){
                        GetCalendarInfoResDCalendarDetail.ShiftInfo shiftInfos=new GetCalendarInfoResDCalendarDetail.ShiftInfo();
                        shiftInfos.setShiftRd(shiftInfos.getShiftRd());
                        shiftInfos.setShiftName(shiftInfos.getShiftName());
                        //getCalendarInfoResDCalendarDetail.setShiftRd(shiftInfo.getRuid());
                        getCalendarInfoResDCalendarDetail.setShiftInfo(shiftInfos);
                    }
                    getCalendarInfoResDCalendarDetails.add(getCalendarInfoResDCalendarDetail);
                }
            }

            getCalendarInfoResD.setCalendarRd(calendarInfo.getRuid());
            getCalendarInfoResD.setCalendarName(calendarInfo.getCalendarName());
            getCalendarInfoResD.setDescription(calendarInfo.getDescription());
            getCalendarInfoResD.setCalendarDetail(getCalendarInfoResDCalendarDetails);
            getCalendarInfoResD.setCreator(calendarInfo.getCreator());
            getCalendarInfoResD.setCreateTime(DateUtil.format(calendarInfo.getCreateTime()));
            getCalendarInfoResD.setLastModifyMan(calendarInfo.getLastModifyMan());
            getCalendarInfoResD.setLastModifyTime(DateUtil.format(calendarInfo.getLastModifyTime()));
            getCalendarInfoResD.setRemark(calendarInfo.getRemark());
        }
        getCalendarInfoResB.setData(getCalendarInfoResD);
        getCalendarInfoRes.setBody(getCalendarInfoResB);
        return getCalendarInfoRes;
    }

    private static boolean isValidDateyyyyMMdd(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
    private static boolean isValidDateyyyy_MM_dd(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    //保存
    @Override
    public SaveCalendarInfoRes AddSaveCalendarInfoReq00(SaveCalendarInfoReq00 saveCalendarInfoReq00) {
        SaveCalendarInfoRes saveCalendarInfoRes=null;
        SaveCalendarInfoResB saveCalendarInfoResB=null;
        if(saveCalendarInfoReq00.getCalendarName()==null||"".equals(saveCalendarInfoReq00.getCalendarName())){
            throw new SystemException("xx","新增失败，生产日历名称不能为空");
        }

        CalendarInfo calendarInfos=calendarDAO.SelectCalendarInfoByCalendarName(saveCalendarInfoReq00.getCalendarName());
        if(calendarInfos!=null){
            throw new SystemException("xx","新增失败，生产日历名称已存在");
        }

        if(saveCalendarInfoReq00.getCalendarDetail().size()<=0||saveCalendarInfoReq00.getCalendarDetail()==null||"".equals(saveCalendarInfoReq00.getCalendarDetail())){
            throw new SystemException("xx","新增失败，生产日历明细不能为空");
        }
        saveCalendarInfoRes=new SaveCalendarInfoRes();
        saveCalendarInfoResB=new SaveCalendarInfoResB();
        CalendarInfo calendarInfo=new CalendarInfo();

        calendarInfo.setGuid(CommonUtils.getRandomNumber());
        int i=1;
        for(SaveCalendarInfoReq00CalendarDetail saveCalendarInfoReq00CalendarDetail:saveCalendarInfoReq00.getCalendarDetail()){
            CalendarDetailInfo calendarDetailInfo=new CalendarDetailInfo();
            calendarDetailInfo.setGuid(CommonUtils.getRandomNumber());
            calendarDetailInfo.setCalendarGD(calendarInfo.getGuid());

            /*boolean a=isValidDateyyyyMMdd(saveCalendarInfoReq00CalendarDetail.getCalendarDate());
            boolean b=isValidDateyyyy_MM_dd(saveCalendarInfoReq00CalendarDetail.getCalendarDate());
            if(a&&!b){
                calendarDetailInfo.setCalendarDate(DateUtil.format3(saveCalendarInfoReq00CalendarDetail.getCalendarDate()));
            }else if(b&&!a){
                calendarDetailInfo.setCalendarDate(DateUtil.format2(saveCalendarInfoReq00CalendarDetail.getCalendarDate()));
            }else {
                throw new SystemException("xxx","保存失败,第:"+i+"的邮件格式不正确，请输入有效邮件");
            }*/



            TeamInfo teamInfo=teamDAO.SelectTeamInfoByRuid(saveCalendarInfoReq00CalendarDetail.getTeamRd());
            if(teamInfo!=null){
                calendarDetailInfo.setTeamGD(teamInfo.getGuid());
            }

           ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(saveCalendarInfoReq00CalendarDetail.getShiftRd());
            if(shiftInfo!=null){
                calendarDetailInfo.setShiftGD(shiftInfo.getGuid());
            }
            calendarDetailDAO.InsertCalendarDetailDAO(calendarDetailInfo);
        i++;
        }

        calendarInfo.setCreateTime(new Date());
        calendarInfo.setCreator(CommonUtils.readUser().getRealName());
        calendarInfo.setDescription(saveCalendarInfoReq00.getDescription());
        calendarInfo.setCalendarName(saveCalendarInfoReq00.getCalendarName());
        calendarInfo.setRemark(saveCalendarInfoReq00.getRemark());
        calendarDAO.InsertCalendarInfo(calendarInfo);

        saveCalendarInfoRes.setBody(saveCalendarInfoResB);
        return saveCalendarInfoRes;
    }
    //删除
    @Override
    public SaveCalendarInfoRes RomSaveCalendarInfoReq01(SaveCalendarInfoReq01 saveCalendarInfoReq01) {
        SaveCalendarInfoRes saveCalendarInfoRes=null;
        SaveCalendarInfoResB saveCalendarInfoResB=null;

        if(saveCalendarInfoReq01.getCalendarRd()<=0||"".equals(saveCalendarInfoReq01.getCalendarRd())){
            throw new SystemException("xx","删除失败，该日历信息不存在");
        }
        CalendarInfo calendarInfo=calendarDAO.SelectCalendarInfoByRuid(saveCalendarInfoReq01.getCalendarRd());
        if(calendarInfo!=null){
            saveCalendarInfoRes=new SaveCalendarInfoRes();
            saveCalendarInfoResB=new SaveCalendarInfoResB();

            List<CalendarDetailInfo> calendarDetailInfos=calendarDetailDAO.SelectCalendarDetailDAOByGuid(calendarInfo.getGuid());
            if(calendarDetailInfos!=null&&calendarDetailInfos.size()>0){
                for(CalendarDetailInfo calendarDetailInfo:calendarDetailInfos){
                    if(calendarDetailDAO.DeleteCalendarDetailDAOInfoByRuid(calendarDetailInfo.getRuid())<=0){
                        throw new SystemException("xx","删除系表信息失败");
                    }
                }
            }
            if(calendarDAO.DeleteCalendarInfoByRuid(calendarInfo.getRuid())<=0){
                throw new SystemException("xx","删除主表信息失败");
            }
        }

        saveCalendarInfoRes.setBody(saveCalendarInfoResB);
        return saveCalendarInfoRes;
    }
    //修改
    @Override
    public SaveCalendarInfoRes ModSaveCalendarInfoReq02(SaveCalendarInfoReq02 saveCalendarInfoReq02) {
        SaveCalendarInfoRes saveCalendarInfoRes=null;
        SaveCalendarInfoResB saveCalendarInfoResB=null;
        if(saveCalendarInfoReq02.getCalendarRd()<=0||"".equals(saveCalendarInfoReq02.getCalendarRd())){
            throw new SystemException("xx","修改失败，生产日历标识不能为空");
        }
        if(saveCalendarInfoReq02.getCalendarName()==null||"".equals(saveCalendarInfoReq02.getCalendarName())){
            throw new SystemException("xx","修改失败，生产日历名称不能为空");
        }
        if(saveCalendarInfoReq02.getCalendarDetail().size()<=0||saveCalendarInfoReq02.getCalendarDetail()==null||"".equals(saveCalendarInfoReq02.getCalendarDetail())){
            throw new SystemException("xx","修改失败，生产日历明细不能为空");
        }
        CalendarInfo calendarInfo1=calendarDAO.SelectCalendarInfoByRuid(saveCalendarInfoReq02.getCalendarRd());
        CalendarInfo calendarInfo=calendarDAO.SelectCalendarInfoByCalendarName(saveCalendarInfoReq02.getCalendarName());
        if(calendarInfo!=null&&!calendarInfo.getCalendarName().equals(calendarInfo1.getCalendarName())){
            throw new SystemException("xx","修改失败，生产日历名称已存在");
        }


        if(calendarInfo1!=null){
            saveCalendarInfoRes=new SaveCalendarInfoRes();
            saveCalendarInfoResB=new SaveCalendarInfoResB();
            List<CalendarDetailInfo> calendarDetailInfos=calendarDetailDAO.SelectCalendarDetailDAOByGuid(calendarInfo1.getGuid());
            if(calendarDetailInfos!=null&&calendarDetailInfos.size()>0){
                for(CalendarDetailInfo calendarDetailInfo:calendarDetailInfos){
                    if(calendarDetailDAO.DeleteCalendarDetailDAOInfoByRuid(calendarDetailInfo.getRuid())<=0){
                        throw new SystemException("xx","删除系表信息失败");
                    }
                }
            }

            int i=1;
            for(SaveCalendarInfoReq02CalendarDetail saveCalendarInfoReq02CalendarDetail:saveCalendarInfoReq02.getCalendarDetail()){
                CalendarDetailInfo calendarDetailInfo=new CalendarDetailInfo();
                calendarDetailInfo.setCalendarGD(calendarInfo1.getGuid());
               /* boolean a=isValidDateyyyyMMdd(saveCalendarInfoReq02CalendarDetail.getCalendarDate());
                boolean b=isValidDateyyyy_MM_dd(saveCalendarInfoReq02CalendarDetail.getCalendarDate());
                if(a&&!b){
                    calendarDetailInfo.setCalendarDate(DateUtil.format3(saveCalendarInfoReq02CalendarDetail.getCalendarDate()));
                }else if(b&&!a){
                    calendarDetailInfo.setCalendarDate(DateUtil.format2(saveCalendarInfoReq02CalendarDetail.getCalendarDate()));
                }else {
                    throw new SystemException("xxx","保存失败,第:"+i+"的邮件格式不正确，请输入有效邮件");
                }*/

              // calendarDetailInfo.setCalendarDate(DateUtil.format2(saveCalendarInfoReq02CalendarDetail.getCalendarDate()));
                TeamInfo teamInfo=teamDAO.SelectTeamInfoByRuid(saveCalendarInfoReq02CalendarDetail.getTeamRd());
                if(teamInfo!=null){
                    calendarDetailInfo.setTeamGD(teamInfo.getGuid());
                }

                ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(saveCalendarInfoReq02CalendarDetail.getShiftRd());
                if(shiftInfo!=null){
                    calendarDetailInfo.setShiftGD(shiftInfo.getGuid());
                }
                calendarDetailDAO.InsertCalendarDetailDAO(calendarDetailInfo);
                i++;
            }
            calendarInfo1.setCalendarName(saveCalendarInfoReq02.getCalendarName());
            calendarInfo1.setDescription(saveCalendarInfoReq02.getDescription());
            calendarInfo1.setLastModifyTime(new Date());
            calendarInfo1.setLastModifyMan(CommonUtils.readUser().getRealName());
            calendarInfo1.setRemark(saveCalendarInfoReq02.getRemark());
            if(calendarDAO.UpdateCalendarInfo(calendarInfo1)<=0){
                throw new SystemException("xxx","修改主表信息失败");
            }
        }
        saveCalendarInfoRes.setBody(saveCalendarInfoResB);
        return saveCalendarInfoRes;
    }

}
