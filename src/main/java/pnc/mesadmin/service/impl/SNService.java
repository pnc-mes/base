package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.SerialRuleDAO;
import pnc.mesadmin.dao.SerialRuleTBFDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoRes;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoResB;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoResD;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoReqBD00;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoRes;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoResB;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoResD;
import pnc.mesadmin.dto.SaveSNInfo.*;
import pnc.mesadmin.entity.SerialRuleInfo;
import pnc.mesadmin.entity.SerialRuleTBFInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.SNIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：序号管理Service实现层
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class SNService implements SNIService{
    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private SerialRuleTBFDAO serialRuleTBFDAO;

    //dto查询序号管理列表信息
    @Override
    public GetAllSNInfoRes QALLGetAllSNInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllSNInfoRes objEGetAllSNInfoRes=new GetAllSNInfoRes();
        GetAllSNInfoResB objEGetAllSNInfoResB=new GetAllSNInfoResB();
        List<GetAllSNInfoResD> objEGetAllSNInfoResDs=new ArrayList<GetAllSNInfoResD>();

        //查询序号信息
        List<SerialRuleInfo> objESerialRuleInfos=baseIService.QALLBaseInfo(SerialRuleDAO.class, "SelectSerialRuleInfo",
                argInitDataFields, argPageInfo);
        if(objESerialRuleInfos!=null||objESerialRuleInfos.size()>0) {

            //对序号管理信息遍历，并赋值给dto
            for (SerialRuleInfo obj : objESerialRuleInfos) {
                GetAllSNInfoResD objEGetAllSNInfoResD = new GetAllSNInfoResD();
                objEGetAllSNInfoResD.setSNRd(obj.getRuid());
                objEGetAllSNInfoResD.setSNName(obj.getRuleName());
                objEGetAllSNInfoResDs.add(objEGetAllSNInfoResD);
            }
        }
        objEGetAllSNInfoResB.setData(objEGetAllSNInfoResDs);
        objEGetAllSNInfoRes.setBody(objEGetAllSNInfoResB);

        return objEGetAllSNInfoRes;
    }

    /**
     * 查询序号规则列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllSNInfoResD> QALLSNNew(BaseRequest req) {
        List<GetAllSNInfoResD> resDList = new ArrayList<GetAllSNInfoResD>();
        GetAllSNInfoResD resD = null;

        IPage<SerialRuleInfo> iPage = serialRuleDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for (SerialRuleInfo obj : iPage.getRecords()) {
            resD = new GetAllSNInfoResD();
            resD.setSNRd(obj.getRuid());
            resD.setSNName(obj.getRuleName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //dto查询序号管理信息
    @Override
    public GetSNInfoRes GetGetSNInfoRes(GetSNInfoReqBD00 argGetSNInfoReqBD00) {
        GetSNInfoRes objEGetSNInfoRes=new GetSNInfoRes();
        GetSNInfoResB objEGetSNInfoResB=new GetSNInfoResB();
        GetSNInfoResD objEGetSNInfoResD=new GetSNInfoResD();

        //根据传过来的id查询序号信息
        SerialRuleInfo objESerialRuleInfo=serialRuleDAO.SelectSerialRuleInfoByruid(argGetSNInfoReqBD00.getSNRd());
        if(objESerialRuleInfo==null){
            throw new SystemException("MG_MES300151110001F","查询序号管理信息为空");
        }
        objEGetSNInfoResD.setSNRd(objESerialRuleInfo.getRuid());
        objEGetSNInfoResD.setSNName(objESerialRuleInfo.getRuleName());
        objEGetSNInfoResD.setIsScript(objESerialRuleInfo.getIsScript());
        if(objESerialRuleInfo.getScriptName()!=null) {
            objEGetSNInfoResD.setScriptName(objESerialRuleInfo.getScriptName());
        }
        if(objESerialRuleInfo.getPrefix()!=null) {
            objEGetSNInfoResD.setPrefix(objESerialRuleInfo.getPrefix());
        }
        if(objESerialRuleInfo.getSuffix()!=null) {
            objEGetSNInfoResD.setSuffix(objESerialRuleInfo.getSuffix());
        }

        if(objESerialRuleInfo.getsNLength()!=null){
            objEGetSNInfoResD.setSNLength(objESerialRuleInfo.getsNLength());
        }

        if(objESerialRuleInfo.getStart()!=null) {
            objEGetSNInfoResD.setStart(objESerialRuleInfo.getStart());
        }

        if(objESerialRuleInfo.getStep()!=null) {
            objEGetSNInfoResD.setStep(objESerialRuleInfo.getStep());
        }
        if("00".equals(objESerialRuleInfo.getReset())){
            objEGetSNInfoResD.setReset("00");
        }
        if("01".equals(objESerialRuleInfo.getReset())){
            objEGetSNInfoResD.setReset("01");
        }

        objEGetSNInfoResD.setLastLevel(objESerialRuleInfo.getLastLevel());
        objEGetSNInfoResD.setCreator(objESerialRuleInfo.getCreator());
        objEGetSNInfoResD.setCreateTime(DateUtil.format(objESerialRuleInfo.getCreateTime()));
        objEGetSNInfoResD.setLastModifyMan(objESerialRuleInfo.getLastModifyMan());
        objEGetSNInfoResD.setLastModifyTime(DateUtil.format(objESerialRuleInfo.getLastModifyTime()));
        objEGetSNInfoResD.setRemark(objESerialRuleInfo.getRemark());

        List<SerialRuleTBFInfo> serialRuleTBFInfo=serialRuleTBFDAO.selectS(objESerialRuleInfo.getGuid());
        List<GetSNInfoResD.BFInfo> bfInfos=new ArrayList<GetSNInfoResD.BFInfo>();
        for (SerialRuleTBFInfo s:serialRuleTBFInfo){
            GetSNInfoResD.BFInfo bfInfo=new GetSNInfoResD.BFInfo();
            bfInfo.setBFCode(s.getFieldCode());
            bfInfo.setBFName(s.getFieldName());
            bfInfos.add(bfInfo);
        }
        objEGetSNInfoResD.setBFInfo(bfInfos);

        objEGetSNInfoResB.setData(objEGetSNInfoResD);
        objEGetSNInfoRes.setBody(objEGetSNInfoResB);

        return objEGetSNInfoRes;
    }
    //dto保存序号管理信息
    @Override
    public SaveSNInfoRes AddSaveSNInfoRes(SaveSNInfoReqBD00 argSaveSNInfoReqBD00) {
        SaveSNInfoRes objESaveSNInfoRes=new SaveSNInfoRes();
        SaveSNInfoResB objESaveSNInfoResB=new SaveSNInfoResB();

        SerialRuleInfo objESerialRuleInfo=new SerialRuleInfo();
        objESerialRuleInfo.setGuid(CommonUtils.getRandomNumber());

        SerialRuleTBFInfo serialRuleTBFInfo=new SerialRuleTBFInfo();

        List<SaveSNInfoReqBD00.BFInfo>  datas=argSaveSNInfoReqBD00.getBFInfo();

        //不允许序号名称重复
        List<SerialRuleInfo> objESerialRuleInfos=serialRuleDAO.SelectSerialRuleInfo();
        for(SerialRuleInfo obj:objESerialRuleInfos){
            if(obj.getRuleName().equals(argSaveSNInfoReqBD00.getSNName())){
                throw new SystemException("MG_MES3001512010001F","新增失败，序号名称已存在");
            }
        }

        objESerialRuleInfo.setRuleName(argSaveSNInfoReqBD00.getSNName());
        //由于别名设置成SN+Ruid，因此此地方先随便写个默认值，最后保存的时候再更新即可

        objESerialRuleInfo.setAliasCode("SN");

        objESerialRuleInfo.setIsScript(argSaveSNInfoReqBD00.getIsScript());
        objESerialRuleInfo.setScriptName(argSaveSNInfoReqBD00.getScriptName());
        String uname=CommonUtils.readUser().getRealName();

        if("01".equals(argSaveSNInfoReqBD00.getIsScript())){
            objESerialRuleInfo.setPrefix(argSaveSNInfoReqBD00.getPrefix());
            objESerialRuleInfo.setSuffix(argSaveSNInfoReqBD00.getSuffix());
            if(argSaveSNInfoReqBD00.getSNLength()>100){
                throw new SystemException("MG_MES3001512010002F","新增失败，流水号长度过大");
            }
            objESerialRuleInfo.setsNLength(argSaveSNInfoReqBD00.getSNLength());
            objESerialRuleInfo.setStart(argSaveSNInfoReqBD00.getStart());
            objESerialRuleInfo.setStep(argSaveSNInfoReqBD00.getStep());

        }else {
            if(datas.size()<=0){
                throw new SystemException("","请绑定数据!");
            }
            serialRuleTBFInfo.setCreator(uname);
            serialRuleTBFInfo.setSerialGd(objESerialRuleInfo.getGuid());
            for(SaveSNInfoReqBD00.BFInfo bfInfo:datas){
                if(bfInfo.getBFCode().equals("")||bfInfo.getBFName().equals("")){
                    throw new SystemException("","请不要绑定空数据!");
                }
                int l=0;
                for(SaveSNInfoReqBD00.BFInfo bfInfo1:datas){
                    if(bfInfo.getBFCode().equals(bfInfo1.getBFCode())&&bfInfo.getBFName().equals(bfInfo1.getBFName())){
                        l=l+1;
                    }
                    if(l>1){
                        throw new SystemException("","请不要绑定重复数据!");
                    }
                }
            }
            for(SaveSNInfoReqBD00.BFInfo bfInfo:datas){
                serialRuleTBFInfo.setGuid(CommonUtils.getRandomNumber());
                serialRuleTBFInfo.setCreateTime(new Date());
                serialRuleTBFInfo.setFieldCode(bfInfo.getBFCode());
                serialRuleTBFInfo.setFieldName(bfInfo.getBFName());
                serialRuleTBFDAO.InsertTBF(serialRuleTBFInfo);
            }
        }

        objESerialRuleInfo.setLastLevel(0);
        if("00".equals(argSaveSNInfoReqBD00.getReset())){
            objESerialRuleInfo.setReset("00");
        }
        else if("01".equals(argSaveSNInfoReqBD00.getReset())){
            objESerialRuleInfo.setReset("01");
        }else {
            objESerialRuleInfo.setReset("01");
        }
        objESerialRuleInfo.setRemark(argSaveSNInfoReqBD00.getRemark());
        objESerialRuleInfo.setCreateTime(new Date());
        objESerialRuleInfo.setCreator(uname);
        serialRuleDAO.InsertSerialRuleInfo(objESerialRuleInfo);

        SerialRuleInfo objESerialRuleInfoss=serialRuleDAO.SelectSerialRuleInfoByguid(objESerialRuleInfo.getGuid());
        if (objESerialRuleInfoss==null){
            throw new SystemException("MG_MES3001512010003F","查询序号管理信息失败");
        }
        objESerialRuleInfoss.setAliasCode("SN"+objESerialRuleInfoss.getRuid());
        if(serialRuleDAO.UpdataSerialRuleInfo(objESerialRuleInfoss)<=0){
            throw new SystemException("MG_MES3001512010004F","更新别名的名称失败");
        }
        objESaveSNInfoRes.setBody(objESaveSNInfoResB);
        return objESaveSNInfoRes;
    }

    //dto删除序号管理信息
    @Override
    public SaveSNInfoRes RmSaveSNInfoRes(SaveSNInfoReqBD01 argSaveSNInfoReqBD01) {
        SaveSNInfoRes objESaveSNInfoRes=new SaveSNInfoRes();
        SaveSNInfoResB objESaveSNInfoResB=new SaveSNInfoResB();
        SerialRuleInfo objESerialRuleInfo= serialRuleDAO.SelectSerialRuleInfoByruid(argSaveSNInfoReqBD01.getSNRd());
        int i=serialRuleTBFDAO.del(objESerialRuleInfo.getGuid());
        if(objESerialRuleInfo==null){
            throw new SystemException("MG_MES3001513010002F","查询序号管理信息失败");
        }
        if(objESerialRuleInfo.getRuleName().equals("包裹批次规则")){
            throw new SystemException("MG_MES3001513010003F","删除序号管理信息失败");
        }

        if(serialRuleDAO.DeleteSerialRuleInfo(argSaveSNInfoReqBD01.getSNRd())<=0){
            throw new SystemException("MG_MES3001513010001F","删除序号管理信息失败");
        }
        objESaveSNInfoRes.setBody(objESaveSNInfoResB);
        return objESaveSNInfoRes;
    }

    //dto更新序号管理信息
    @Override
    public SaveSNInfoRes ModSaveSNInfoRes(SaveSNInfoReqBD02 argSaveSNInfoReqBD02) {
        SaveSNInfoRes objESaveSNInfoRes=new SaveSNInfoRes();
        SaveSNInfoResB objESaveSNInfoResB=new SaveSNInfoResB();
        SerialRuleTBFInfo serialRuleTBFInfo=new SerialRuleTBFInfo();
        SerialRuleInfo serialRuleInfo=new SerialRuleInfo();
        SerialRuleInfo objESerialRuleInfo=serialRuleDAO.SelectSerialRuleInfoByruid(argSaveSNInfoReqBD02.getSNRd());

        if(objESerialRuleInfo==null){
            throw new SystemException("MG_MES3001514010001F","查询序号管理信息失败");
        }
        if(objESerialRuleInfo.getRuleName().equals("包裹批次规则")){
            throw new SystemException("MG_MES3001514010004F","更新序号管理信息失败");
        }

        //不允许序号名称重复
        SerialRuleInfo serialRuleInfoname=serialRuleDAO.SelectSerialRuleInfoByruid(argSaveSNInfoReqBD02.getSNRd());
        SerialRuleInfo serialRuleInfo1=serialRuleDAO.SelectSerialRuleInfoByRuleName(argSaveSNInfoReqBD02.getSNName());
        if(serialRuleInfo1!=null&&serialRuleInfo1.getRuid()!=argSaveSNInfoReqBD02.getSNRd()){
            throw new SystemException("MG_MES3001514010002F","更新失败，序号名称已存在");
        }
      /*  if(serialRuleInfoname!=null&&!serialRuleInfoname.getRuleName().equals(objESerialRuleInfo.getRuleName())){
            throw new SystemException("MG_MES3001514010002F","更新失败，序号名称已存在");
        }*/
        serialRuleInfo.setRuid(objESerialRuleInfo.getRuid());
        serialRuleInfo.setGuid(objESerialRuleInfo.getGuid());
        serialRuleInfo.setRuleName(argSaveSNInfoReqBD02.getSNName());

        int i=serialRuleTBFDAO.del(serialRuleInfoname.getGuid());
        serialRuleInfo.setIsScript(argSaveSNInfoReqBD02.getIsScript());
        if(argSaveSNInfoReqBD02.getIsScript().equals("01")){
            serialRuleInfo.setPrefix(argSaveSNInfoReqBD02.getPrefix());
            serialRuleInfo.setSuffix(argSaveSNInfoReqBD02.getSuffix());
            serialRuleInfo.setsNLength(argSaveSNInfoReqBD02.getSNLength());
            serialRuleInfo.setStart(argSaveSNInfoReqBD02.getStart());
            serialRuleInfo.setStep(argSaveSNInfoReqBD02.getStep());
          /* */
        }else {
            if(argSaveSNInfoReqBD02.getScriptName()==null&&argSaveSNInfoReqBD02.getScriptName()==""){
                    throw new SystemException("","请输入脚本名称!");
            }
            serialRuleInfo.setScriptName(argSaveSNInfoReqBD02.getScriptName());
            List<SaveSNInfoReqBD02.BFInfo>  datas=argSaveSNInfoReqBD02.getBFInfo();
            if(datas.size()<=0){
                throw new SystemException("","请绑定数据!");
            }

            serialRuleTBFInfo.setCreator(CommonUtils.readUser().getRealName());
            serialRuleTBFInfo.setSerialGd(objESerialRuleInfo.getGuid());

            for(SaveSNInfoReqBD02.BFInfo bfInfo:datas){
                if(bfInfo.getBFCode().equals("")||bfInfo.getBFName().equals("")){
                    throw new SystemException("","请不要绑定空数据!");
                }
                int l=0;
                for(SaveSNInfoReqBD02.BFInfo bfInfo1:datas){
                    if(bfInfo.getBFCode().equals(bfInfo1.getBFCode())&&bfInfo.getBFName().equals(bfInfo1.getBFName())){
                      l=l+1;
                    }
                    if(l>1){
                        throw new SystemException("","请不要绑定重复数据!");
                    }
                }
            }

            for(SaveSNInfoReqBD02.BFInfo bfInfo:datas){
                serialRuleTBFInfo.setGuid(CommonUtils.getRandomNumber());
                serialRuleTBFInfo.setCreateTime(new Date());
                serialRuleTBFInfo.setFieldCode(bfInfo.getBFCode());
                serialRuleTBFInfo.setFieldName(bfInfo.getBFName());
                serialRuleTBFDAO.InsertTBF(serialRuleTBFInfo);

            }


        }
        serialRuleInfo.setLastLevel(objESerialRuleInfo.getLastLevel());
        if(argSaveSNInfoReqBD02.getReset()!=null){
            serialRuleInfo.setReset(argSaveSNInfoReqBD02.getReset());
        }
        else{
            serialRuleInfo.setReset("01");
        }
        serialRuleInfo.setRemark(argSaveSNInfoReqBD02.getRemark());
        serialRuleInfo.setCreator(objESerialRuleInfo.getCreator());
        serialRuleInfo.setCreateTime(objESerialRuleInfo.getCreateTime());
        serialRuleInfo.setLastModifyTime(new Date());
        serialRuleInfo.setLastModifyMan(CommonUtils.readUser().getRealName());

        if(serialRuleDAO.UpdataSerialRuleInfo(serialRuleInfo)<=0){
            throw new SystemException("MG_MES3001514010003F","更新序号管理信息失败");
        }

        objESaveSNInfoRes.setBody(objESaveSNInfoResB);
        return objESaveSNInfoRes;
    }

    //dto复制序号管理信息
    @Override
    public SaveSNInfoRes AddSaveSNInfoRess(SaveSNInfoReqBD03 argSaveSNInfoReqBD03) {
        SaveSNInfoRes objESaveSNInfoRes=new SaveSNInfoRes();
        SaveSNInfoResB objESaveSNInfoResB=new SaveSNInfoResB();

        //复制序号管理信息，先查询该条信息，进行复制
        SerialRuleInfo objESerialRuleInfo=serialRuleDAO.SelectSerialRuleInfoByruid(argSaveSNInfoReqBD03.getSNRd());
        if(objESerialRuleInfo==null){
            throw new SystemException("MG_MES3001515010001F","查询序号管理信息失败");
        }
        objESerialRuleInfo.setGuid(CommonUtils.getRandomNumber());
        objESerialRuleInfo.setCreateTime(new Date());
        objESerialRuleInfo.setLastModifyTime(new Date());
        objESerialRuleInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objESerialRuleInfo.setCreator(CommonUtils.readUser().getRealName());
        //复制即新增
        serialRuleDAO.InsertSerialRuleInfo(objESerialRuleInfo);

        //新增之后再查询
        SerialRuleInfo objESerialRuleInfos=serialRuleDAO.SelectSerialRuleInfoByguid(objESerialRuleInfo.getGuid());
        if (objESerialRuleInfos==null){
            throw new SystemException("MG_MES3001515010002F","查询序号管理信息失败");
        }
        //名称具有唯一性
        objESerialRuleInfos.setRuleName(objESerialRuleInfos.getRuleName()+objESerialRuleInfos.getRuid());
        if(serialRuleDAO.UpdataSerialRuleInfo(objESerialRuleInfos)<=0){
            throw new SystemException("MG_MES3001515010003F","更新序号管理名称信息失败");
        }
        objESaveSNInfoRes.setBody(objESaveSNInfoResB);
        return objESaveSNInfoRes;
    }

    //生成序号
    @Override
    public String GetCreateSR(SerialRuleInfo serialRuleInfo) {
        String batch = "";

        String prefix = serialRuleInfo.getPrefix();
        batch += prefix;
        int SNLength = serialRuleInfo.getsNLength();      //流水长度
        int step = serialRuleInfo.getStep();            //步长
        int lastLevel = serialRuleInfo.getLastLevel();      //流水位
        int newLastLevel = 0;             //新的流水位
        if (lastLevel == 0) {
            newLastLevel = serialRuleInfo.getStart();
        } else {
            newLastLevel = step + lastLevel;
        }

        serialRuleInfo.setLastLevel(newLastLevel);

        String strNewLastLevel = newLastLevel + "";

        for (int i = 0; i < SNLength - strNewLastLevel.length(); i++) {
            batch += "0";
        }
        batch += strNewLastLevel + serialRuleInfo.getSuffix();

        if(serialRuleDAO.UpdataSerialRuleInfo(serialRuleInfo) <= 0){
            throw new SystemException("", "序号生成失败");
        }

        return batch;
    }
}
