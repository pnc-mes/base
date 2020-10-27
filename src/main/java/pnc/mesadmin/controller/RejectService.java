package pnc.mesadmin.controller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.RejectDAO;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoRes;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResB;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResD;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResD1;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoReqBD00;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoRes;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.RejectInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.RejectIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码Service实现层
 * 创建人：郝赞
 * 创建时间：2018-8-6
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class RejectService implements RejectIService {

    @Resource
    private RejectDAO rejectDAO;

    @Resource
    private BaseIService baseIService;


    //获取不良信息列表信息
    @Override
    public GetAllRejectInfoRes GetAllRejectInfoRes(GetAllRejectInfoResD1 getAllRejectInfoResD1) {

        GetAllRejectInfoResB getAllRejectInfoResB = new GetAllRejectInfoResB();

        List<GetAllRejectInfoResD> getAllRejectInfoResD = new ArrayList<GetAllRejectInfoResD>();

        GetAllRejectInfoRes getAllRejectInfoRes = new GetAllRejectInfoRes();
        if(getAllRejectInfoResD1.getBatch()==null ||getAllRejectInfoResD1.getBatch().equals("")){
            throw new SystemException("xx","请输入批次号");
        }


        getAllRejectInfoResD = rejectDAO.SelectAllRejectInfo(getAllRejectInfoResD1.getBatch());

        getAllRejectInfoResB.setData(getAllRejectInfoResD);

        getAllRejectInfoRes.setStatus("00");
        getAllRejectInfoRes.setBody(getAllRejectInfoResB);


        return getAllRejectInfoRes;

    }

    @Override
    public SaveRejectInfoRes AddReject(SaveRejectInfoReqBD00[] saveRejectInfoReqBD00) {
        SaveRejectInfoResB saveRejectInfoResB = new SaveRejectInfoResB();
        SaveRejectInfoRes saveRejectInfoRes = new SaveRejectInfoRes();
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();

        for(SaveRejectInfoReqBD00 s:saveRejectInfoReqBD00){
            if(s.getBatch()==null||s.getBatch().equals("")){
                throw new SystemException("","请输入批次号");
            }
            if(s.getSpecName()==null||s.getMaName().equals("")){
                throw new SystemException("","请选择不良工序");
            }
            if(s.getReaDes()==null||s.getReaDes().equals("")){
                throw new SystemException("","请选择不良原因");
            }
            if(s.getBadNum()==null||s.getBadNum().equals("")){
                throw new SystemException("","请填写不良数量");
            }
            //当前时间
            Date date = new Date();
            RejectInfo rejectInfo=new RejectInfo();
            rejectInfo.setGuid(CommonUtils.getRandomNumber());
            rejectInfo.setBatch(s.getBatch());
            rejectInfo.setReaDes(s.getReaDes());
            //rejectInfo.setMaName(s.getMaName());
            rejectInfo.setNum(s.getNum());
            //rejectInfo.setSpecName(s.getSpecName());
            //rejectInfo.setBadNum(s.getBadNum());
            //rejectInfo.setUnitName(s.getUnitName());
            rejectInfo.setCreator(userName);
            rejectInfo.setCreateTime(date);

            rejectDAO.addReject(rejectInfo);

        }
        saveRejectInfoResB.setMsgCode("");
        saveRejectInfoResB.setMsgDes("成功");
        saveRejectInfoRes.setStatus("00");
        saveRejectInfoRes.setBody(saveRejectInfoResB);

        return saveRejectInfoRes;

    }


}
