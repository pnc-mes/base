package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dao.LineDao;
import pnc.mesadmin.dao.ShiftDAO;
import pnc.mesadmin.dao.StationDao;
import pnc.mesadmin.dao.UserDAO;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.StationInfo;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.service.CommonTotalNumIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/28 16:02
 * @Description:
 */
@Controller
@RequestMapping("/CommonTotalNum")
public class CommonTotalNumController {
    @Resource
    CommonTotalNumIService commonTotalNumIService;

    @Resource
    ShiftDAO shiftDAO;

    @Resource
    LineDao lineDao;

    @Resource
    UserDAO userDAO;

    @Resource
    private StationDao stationDao;
    @ResponseBody
    @RequestMapping(value = "/CZPInfo")
    public int aa(int ShiftRd, HttpSession httpSession) throws Exception{

        int result=0;

        Date date=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        String time=formatter.format(date);
        UserInfo userInfo=userDAO.SelectUserInfoByUserName(CommonUtils.readUser().getRealName());
        if(userInfo!=null){
            //查询线体标识
            List<StationInfo> stationInfos = stationDao.SelectByExecGdExecType(userInfo.getGuid(), "01");
            if (stationInfos.size() <= 0) {
                throw new SystemException("", "当前执行对象无工位操作权限,请到工位管理中进行相关配置");
            } else if (stationInfos.size() > 1) {
                throw new SystemException("", "当前执行对象工位信息配置有误，不能存在一个执行对象适配多个工位的情况");
            }
            StationInfo stationInfo = stationInfos.get(0);




       ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(ShiftRd);
       if(shiftInfo!=null) {
           boolean flag = CommonUtils.hourMinuteBetween(time, formatter.format(shiftInfo.getStartTime()), formatter.format(shiftInfo.getEndTime()));
           //int LineRd = (int) httpSession.getAttribute("LineRd");
          // Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(LineRd);
          // if (lineinfo != null) {
               if (flag) {
                   result = commonTotalNumIService.selectAllCZPInfo(stationInfo.getLineGd(), df.format(date) + " " + shiftInfo.getEndTime(), df.format(date) + " " + shiftInfo.getStartTime());

               } else {
                   String year = df.format(date).split("-")[0];
                   String mouse = df.format(date).split("-")[1];
                   int day = Integer.valueOf(df.format(date).split("-")[2]) + 1;
                   result = commonTotalNumIService.selectAllCZPInfo(stationInfo.getLineGd(), year + "-" + mouse + "-" + day + " " + shiftInfo.getEndTime(), df.format(date) + " " + time);
               }
           }

     //  }
    }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/YHCheckInfo")
    public String bb(int ShiftRd,HttpSession httpSession) throws Exception{
        int result=0;

        int result2=0;
        Date date=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        String time=formatter.format(date);

        ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(ShiftRd);
        if(shiftInfo!=null) {
            boolean flag = CommonUtils.hourMinuteBetween(time, formatter.format(shiftInfo.getStartTime()), formatter.format(shiftInfo.getEndTime()));
            // int LineRd = (int) httpSession.getAttribute("LineRd");
            //  Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(LineRd);
            //  if (lineinfo != null) {
            UserInfo userInfo = userDAO.SelectUserInfoByUserName(CommonUtils.readUser().getRealName());
            if (userInfo != null) {
                //查询线体标识
                List<StationInfo> stationInfos = stationDao.SelectByExecGdExecType(userInfo.getGuid(), "01");
                if (stationInfos.size() <= 0) {
                    throw new SystemException("", "当前执行对象无工位操作权限,请到工位管理中进行相关配置");
                } else if (stationInfos.size() > 1) {
                    throw new SystemException("", "当前执行对象工位信息配置有误，不能存在一个执行对象适配多个工位的情况");
                }
                StationInfo stationInfo = stationInfos.get(0);

                if (flag) {
                    result = commonTotalNumIService.selectAllYHCheckInfo(stationInfo.getLineGd(), df.format(date) + " " + shiftInfo.getEndTime(), df.format(date) + " " + shiftInfo.getStartTime());

                    result2 = commonTotalNumIService.reselectAllYHCheckInfo(stationInfo.getLineGd(), df.format(date) + " " + shiftInfo.getEndTime(), df.format(date) + " " + shiftInfo.getStartTime());
                } else {
                    String year = df.format(date).split("-")[0];
                    String mouse = df.format(date).split("-")[1];
                    int day = Integer.valueOf(df.format(date).split("-")[2]) + 1;
                    result = commonTotalNumIService.selectAllYHCheckInfo(stationInfo.getLineGd(), year + "-" + mouse + "-" + day + " " + shiftInfo.getEndTime(), df.format(date) + " " + time);
                    result2 = commonTotalNumIService.reselectAllYHCheckInfo(stationInfo.getLineGd(), year + "-" + mouse + "-" + day + " " + shiftInfo.getEndTime(), df.format(date) + " " + time);
                }
            }
            // }
        }

        return result+","+result2;
    }

    @ResponseBody
    @RequestMapping(value = "/ZJCheckInfo")
    public String cc(int ShiftRd,HttpSession httpSession) throws Exception{
        int result=0;
        int result2=0;
        Date date=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        String time=formatter.format(date);

        ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(ShiftRd);
        if(shiftInfo!=null) {
            boolean flag = CommonUtils.hourMinuteBetween(time, formatter.format(shiftInfo.getStartTime()), formatter.format(shiftInfo.getEndTime()));
            // int LineRd = (int) httpSession.getAttribute("LineRd");
            //  Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(LineRd);
            // if (lineinfo != null) {
            UserInfo userInfo = userDAO.SelectUserInfoByUserName(CommonUtils.readUser().getRealName());
            if (userInfo != null) {
                //查询线体标识
                List<StationInfo> stationInfos = stationDao.SelectByExecGdExecType(userInfo.getGuid(), "01");
                if (stationInfos.size() <= 0) {
                    throw new SystemException("", "当前执行对象无工位操作权限,请到工位管理中进行相关配置");
                } else if (stationInfos.size() > 1) {
                    throw new SystemException("", "当前执行对象工位信息配置有误，不能存在一个执行对象适配多个工位的情况");
                }
                StationInfo stationInfo = stationInfos.get(0);

                if (flag) {
                    result = commonTotalNumIService.selectAllZJCheckInfo(stationInfo.getLineGd(), df.format(date) + " " + shiftInfo.getEndTime(), df.format(date) + " " + shiftInfo.getStartTime());
                    result2 = commonTotalNumIService.reselectAllZJCheckInfo(stationInfo.getLineGd(), df.format(date) + " " + shiftInfo.getEndTime(), df.format(date) + " " + shiftInfo.getStartTime());
                } else {
                    String year = df.format(date).split("-")[0];
                    String mouse = df.format(date).split("-")[1];
                    int day = Integer.valueOf(df.format(date).split("-")[2]) + 1;
                    result = commonTotalNumIService.selectAllZJCheckInfo(stationInfo.getLineGd(), year + "-" + mouse + "-" + day + " " + shiftInfo.getEndTime(), df.format(date) + " " + time);
                    result2 = commonTotalNumIService.reselectAllZJCheckInfo(stationInfo.getLineGd(), year + "-" + mouse + "-" + day + " " + shiftInfo.getEndTime(), df.format(date) + " " + time);
                }
            }
            // }
        }

        return result+","+result2;
    }

}
