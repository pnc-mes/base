package pnc.mesadmin.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.CommonTotalNumDAO;
import pnc.mesadmin.service.CommonTotalNumIService;

import javax.annotation.Resource;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/28 16:03
 * @Description:
 */
@Service
@Transactional
public class CommonTotalNumService implements CommonTotalNumIService{
    @Resource
    CommonTotalNumDAO commonTotalNumDAO;


    @Override
    public int selectAllCZPInfo(String lineGd,String endtime, String starttime) {
       return commonTotalNumDAO.selectAllCZPInfo(lineGd,endtime,starttime);
    }

    @Override
    public int selectAllYHCheckInfo(String lineGd,String endTime, String startTime) {
        return commonTotalNumDAO.selectAllYHCheckInfo(lineGd,endTime,startTime);
    }

    @Override
    public int selectAllZJCheckInfo(String lineGd,String endTime, String startTime) {
        return commonTotalNumDAO.selectAllZJCheckInfo(lineGd,endTime,startTime);
    }


    @Override
    public int reselectAllYHCheckInfo(String lineGd,String endTime, String startTime) {
        return commonTotalNumDAO.reselectAllYHCheckInfo(lineGd,endTime,startTime);
    }

    @Override
    public int reselectAllZJCheckInfo(String lineGd,String endTime, String startTime) {
        return commonTotalNumDAO.reselectAllZJCheckInfo(lineGd,endTime,startTime);
    }
}
