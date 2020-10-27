package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.SMTPDAO;
import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoRes;
import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoResB;
import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoResD;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoRes;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoResB;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSMTPInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SMTPInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.SMTPIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：通知管理Service层
 * 创建人：乔帅阳
 * 创建时间：2018-7-3
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class SMTPService implements SMTPIService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private SMTPDAO sMTPDAO;
    @Override
    //获取邮件服务器列表信息
    public GetAllSMTPInfoRes QALLselectAllSMTPInfoInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllSMTPInfoRes objGetAllSMTPInfoRes = new GetAllSMTPInfoRes();
        GetAllSMTPInfoResB body =  new GetAllSMTPInfoResB();
        List<GetAllSMTPInfoResD> dataList = new ArrayList<>();
        List<SMTPInfo>  SMTPInfoList = baseIService.QALLBaseInfo(SMTPDAO.class, "SelectAllSMTPInfo", argInitDataFields, argPageInfo);// sMTPDAO.SelectAllSMTPInfo();
        if(SMTPInfoList.size()>0){
              for (int i=0;i<SMTPInfoList.size();i++){
                  GetAllSMTPInfoResD data =  new GetAllSMTPInfoResD();
                  data.setSMTPRd(SMTPInfoList.get(i).getRuid());
                  dataList.add(data);
              }
        }
        body.setData(dataList);
        objGetAllSMTPInfoRes.setBody(body);
        return objGetAllSMTPInfoRes;
    }
    //获取邮件服务器信息
    @Override
    public GetSMTPInfoRes GetselectSMTPInfo() throws SystemException {
        GetSMTPInfoRes objGetShiftInfoRes = new GetSMTPInfoRes();
        GetSMTPInfoResB body = new GetSMTPInfoResB();
        GetSMTPInfoResD data = new GetSMTPInfoResD();
        SMTPInfo objSMTPInfo = sMTPDAO.SelectSMTP();
        if (objSMTPInfo!=null){
            data.setSMTPRd(objSMTPInfo.getRuid());
            data.setUserName(objSMTPInfo.getUserName());
            data.setPassword(objSMTPInfo.getPassword());
            data.setSMTPURL(objSMTPInfo.getSMTPURL());
            data.setPort(objSMTPInfo.getPort());
            data.setUseSSL(objSMTPInfo.getUseSSL());
            data.setCreator(objSMTPInfo.getCreator());
            data.setCreateTime(DateUtil.format(objSMTPInfo.getCreateTime()));
            data.setLastModifyMan(objSMTPInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(objSMTPInfo.getLastModifyTime()));
            data.setRemark(objSMTPInfo.getRemark());
        }
        body.setData(data);
        objGetShiftInfoRes.setBody(body);
        return objGetShiftInfoRes;
    }


   //保存邮件服务器信息
    @Override
    public SaveSMTPInfoRes AddinsertSMTPInfo(SaveSMTPInfoReqBD00 busData00, SMTPInfo sMTPInfo) throws SystemException {
        SaveSMTPInfoRes saveSMTPInfoRes = new SaveSMTPInfoRes();
        SaveSMTPInfoResB body = new SaveSMTPInfoResB();
        SaveSMTPInfoResD data = new SaveSMTPInfoResD();


        if("".equals(busData00.getSMTPURL())||busData00.getSMTPURL()==null){
            throw new SystemException("","服务器地址不能为空");
        }
        if("".equals(busData00.getPassword())||busData00.getPassword()==null){
            throw new SystemException("","密码不能为空");
        }
        if(busData00.getPort()<=0){
            throw new SystemException("","端口不能为空");
        }
        SMTPInfo smtpInfo=sMTPDAO.SelectAllSMTPInfoBySMTPName();
       /* List<SMTPInfo> objSMTPInfo = sMTPDAO.SelectAllSMTPInfo();
        for (SMTPInfo obj:objSMTPInfo){
            if(obj.getSMTPName().equals(busData00.getSMTPName())){
                throw new SystemException("","名称已存在");
            }
        }*/
       if(smtpInfo!=null){
           throw new SystemException("","名称已存在");
       }
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        sMTPInfo.setGuid(CommonUtils.getRandomNumber());
        sMTPInfo.setUserName(busData00.getUserName());
        sMTPInfo.setPassword(busData00.getPassword());
        sMTPInfo.setSMTPURL(busData00.getSMTPURL());
        sMTPInfo.setUseSSL(busData00.getUseSSL());
        sMTPInfo.setPort(busData00.getPort());
        sMTPInfo.setCreator(userName);
        sMTPInfo.setCreateTime(date);
        sMTPDAO.InsertSMTPInfo(sMTPInfo);
        body.setData(data);
        saveSMTPInfoRes.setBody(body);
        return saveSMTPInfoRes;
    }
    //删除邮件服务器信息
    @Override
    public SaveSMTPInfoRes RmdeleteSMTPInfo(int Ruid) throws SystemException {
        SaveSMTPInfoRes saveSMTPInfoRes = new SaveSMTPInfoRes();
        SaveSMTPInfoResB body = new SaveSMTPInfoResB();
        SaveSMTPInfoResD data = new SaveSMTPInfoResD();
        int count = sMTPDAO.DeleteSMTPInfo(Ruid);
        if(count <=0) throw new SystemException("MG_MES","删除邮箱服务器信息失败！");
        body.setData(data);
        saveSMTPInfoRes.setBody(body);
        return saveSMTPInfoRes;
    }
   //更新邮件服务器信息
    @Override
    public SaveSMTPInfoRes ModupdateCustomerInfo(SaveSMTPInfoReqBD02 busData02, SMTPInfo sMTPInfo) throws SystemException {
        SaveSMTPInfoRes saveSMTPInfoRes = new SaveSMTPInfoRes();
        SaveSMTPInfoResB body = new SaveSMTPInfoResB();
        SaveSMTPInfoResD data = new SaveSMTPInfoResD();

        if("".equals(busData02.getSMTPName())||busData02.getSMTPName()==null){
            throw new SystemException("","服务器名称不能为空");
        }
        if("".equals(busData02.getSMTPURL())||busData02.getSMTPURL()==null){
            throw new SystemException("","服务器地址不能为空");
        }
        if("".equals(busData02.getPassword())||busData02.getPassword()==null){
            throw new SystemException("","密码不能为空");
        }

        SMTPInfo smtpInfo1=sMTPDAO.SelectSMTPByID(busData02.getSMTPRd());

        //当前用户
        String userName = CommonUtils.readUser().getRealName();

        //当前时间
        Date date = new Date();
        sMTPInfo.setRuid(busData02.getSMTPRd());
        sMTPInfo.setUserName(busData02.getUserName());
        sMTPInfo.setPassword(busData02.getPassword());
        sMTPInfo.setSMTPURL(busData02.getSMTPURL());
        sMTPInfo.setUseSSL(busData02.getUseSSL());
        sMTPInfo.setPort(busData02.getPort());
        sMTPInfo.setRemark(busData02.getRemark());
        sMTPInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        sMTPInfo.setLastModifyTime(new Date());
        int count = sMTPDAO.UpdateSMTPInfo(sMTPInfo);
        if(count<=0){
            throw new SystemException("","更新邮件服务器信息失败！");
        }
        body.setData(data);
        saveSMTPInfoRes.setBody(body);
        return saveSMTPInfoRes;
    }
    //复制邮件服务器信息
    @Override
    public SaveSMTPInfoRes copySMTPInfo(SaveSMTPInfoReqBD03 busData03, SMTPInfo sMTPInfo) throws SystemException {
        SaveSMTPInfoRes saveSMTPInfoRes = new SaveSMTPInfoRes();
        SaveSMTPInfoResB body = new SaveSMTPInfoResB();
        SaveSMTPInfoResD data = new SaveSMTPInfoResD();
        SMTPInfo objSMTPInfo = sMTPDAO.SelectSMTPByID(busData03.getSMTPRd());
        if(objSMTPInfo==null){
            throw new SystemException("","查询邮件服务器信息为空");
        }
        //复制一条邮件服务器信息
        sMTPInfo.setGuid(CommonUtils.getRandomNumber());
        sMTPInfo.setUserName(objSMTPInfo.getUserName());
        sMTPInfo.setPassword(objSMTPInfo.getPassword());
        sMTPInfo.setSMTPURL(objSMTPInfo.getSMTPURL());
        sMTPInfo.setUseSSL(objSMTPInfo.getUseSSL());
        sMTPInfo.setCreator(objSMTPInfo.getCreator());
        sMTPInfo.setCreateTime(new Date());
        sMTPInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        sMTPInfo.setLastModifyTime(new Date());
        sMTPInfo.setRemark(objSMTPInfo.getRemark());
        // 调用邮件服务器信息sql复制一条数据
        int count = sMTPDAO.InsertSMTPInfo(sMTPInfo);
        if(count <=0) throw new SystemException("","复制邮件服务器信息失败！");
        //查询邮件服务器信息
        SMTPInfo objsMTPInfo = sMTPDAO.SelectByGuid(sMTPInfo.getGuid());
        if(sMTPDAO.UpdateSMTPInfo(objsMTPInfo)<=0){
            throw new SystemException("","复制邮件服务器信息失败！");
        }
        body.setData(data);
        saveSMTPInfoRes.setBody(body);
        return saveSMTPInfoRes;
    }
}
