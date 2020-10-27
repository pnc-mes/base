package pnc.mesadmin.service.impl;

import com.alibaba.druid.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetGCInfo.*;
import pnc.mesadmin.dto.SaveGCInfo.*;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoReqBD00;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoRes;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoResD;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.baseUtil;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：全局配置信息Service层实现层类
 * 创建人：刘福志
 * 创建时间：2017-8-25
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class GConfigService implements GConfigIService {

    @Resource
    private ModeCDAO modeCDAO;

    @Resource
    private MsgCDAO msgCDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private SynchCDAO synchCDAO;

    @Resource
    private SMTPDAO sMTPDAO;

//巴卫修正
//    @Resource(name = "redisTemplateS")
    @Resource
    private   RedisTemplate redisTemplate;

    //查询全局配置列表信息
    public GetGCInfoRes QALLselectAllGetGCInfo() {
        GetGCInfoRes objEGetGCInfoRes = new GetGCInfoRes();
        GetGCInfoResB objEGetGCInfoResB = new GetGCInfoResB();
        GetGCInfoResD objEGetGCInfoResD = new GetGCInfoResD();

        //模式配置信息
        List<GetGCInfoResDModeC> objEGetGCInfoResDModeC = new ArrayList<GetGCInfoResDModeC>();

        List<ModeCInfo> objEModeCInfo = modeCDAO.SelectAllModeCInfo();
        if (objEModeCInfo != null && objEModeCInfo.size() > 0) {
            for (ModeCInfo obj1 : objEModeCInfo) {
                GetGCInfoResDModeC objEGetGCInfoResDModeCs = new GetGCInfoResDModeC();
                objEGetGCInfoResDModeCs.setModeName(obj1.getModeName());
                objEGetGCInfoResDModeCs.setModeValue(obj1.getModeValue());
                objEGetGCInfoResDModeC.add(objEGetGCInfoResDModeCs);
            }
        }

        //消息配置信息
        List<GetGCInfoResDMsgC> objEGetGCInfoResDMsgC = new ArrayList<GetGCInfoResDMsgC>();

        List<MsgCInfo> objEMsgCInfo = msgCDAO.SelectAllMsgCInfo();
        if (objEMsgCInfo != null && objEMsgCInfo.size() > 0) {
            for (MsgCInfo obj2 : objEMsgCInfo) {
                GetGCInfoResDMsgC objEGetGCInfoResDMsgCs = new GetGCInfoResDMsgC();
                objEGetGCInfoResDMsgCs.setMsgName(obj2.getMsgName());
                objEGetGCInfoResDMsgCs.setMsgValue(obj2.getMsgValue());
                objEGetGCInfoResDMsgC.add(objEGetGCInfoResDMsgCs);
            }
        }

        //查询同步名称信息
        List<GetGCInfoResDSynchC> objEGetGCInfoResDSynchC = new ArrayList<GetGCInfoResDSynchC>();

        List<SynchCInfo> objESynchCInfo = synchCDAO.SelectAllSynchCInfo();
        if (objESynchCInfo != null && objESynchCInfo.size() > 0) {
            for (SynchCInfo obj3 : objESynchCInfo) {
                GetGCInfoResDSynchC objEGetGCInfoResDSynchCs = new GetGCInfoResDSynchC();
                objEGetGCInfoResDSynchCs.setSynchName(obj3.getSynchName());
                objEGetGCInfoResDSynchCs.setStatus(obj3.getStatus());
                objEGetGCInfoResDSynchC.add(objEGetGCInfoResDSynchCs);
            }
        }

        //查询生成代码规则
        List<GetGCInfoResDCodeRule> objEGetGCInfoResDCodeRule = new ArrayList<>();

        List<CodeRuleInfo> objECodeRuleInfo = codeRuleDAO.SelectAllCodeRuleInfo();

        if (objECodeRuleInfo != null && objECodeRuleInfo.size() > 0) {
            for (CodeRuleInfo obj3 : objECodeRuleInfo) {
                GetGCInfoResDCodeRule objEGetGCInfoResDCodeRules = new GetGCInfoResDCodeRule();

                if ("00".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("物料代码");
                } else if ("01".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("BOM代码");
                } else if ("02".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("供应商代码");
                } else if ("03".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("工单号");
                } else if ("04".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("采购单号");
                } else if ("05".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("来料通知单号");
                } else if ("06".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("领料单号");
                } else if ("07".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("退料单号");
                } else if ("08".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("成品入库单号");
                } else if ("09".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("盘点单号");
                } else if ("10".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("企业代码");
                } else if ("11".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("仓库代码");
                } else if ("12".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("库位代码");
                } else if ("13".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("载具家族代码");
                } else if ("14".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("订单代码");
                }else if ("15".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("成品出库单号");
                }else if ("16".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("质检单号");
                }else if ("17".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("OQC单号");
                }else if ("18".equals(obj3.getCodeType())) {
                    objEGetGCInfoResDCodeRules.setCodeType("备货单号");
                }


                objEGetGCInfoResDCodeRules.setPrefix(obj3.getPrefix());
                objEGetGCInfoResDCodeRules.setDtFormat(obj3.getDtFormat());
                objEGetGCInfoResDCodeRules.setSuffix(obj3.getSuffix());
                objEGetGCInfoResDCodeRules.setSNLength(obj3.getsNLength());
                objEGetGCInfoResDCodeRules.setStatus(obj3.getStatus());
                objEGetGCInfoResDCodeRule.add(objEGetGCInfoResDCodeRules);
            }
        }

        objEGetGCInfoResD.setModeC(objEGetGCInfoResDModeC);
        objEGetGCInfoResD.setMsgC(objEGetGCInfoResDMsgC);
        objEGetGCInfoResD.setCodeRule(objEGetGCInfoResDCodeRule);
        objEGetGCInfoResD.setSynchC(objEGetGCInfoResDSynchC);

        objEGetGCInfoResB.setData(objEGetGCInfoResD);
        objEGetGCInfoRes.setBody(objEGetGCInfoResB);
        return objEGetGCInfoRes;
    }

    //新增全局配置信息
    public SaveGCInfoRes AddinsertSaveGCInfo(SaveGCInfoReqBD00 busData00) {
        SaveGCInfoRes objESaveGCInfoRes = new SaveGCInfoRes();
        SaveGCInfoResB objESaveGCInfoResB = new SaveGCInfoResB();

        ModeCInfo modeCInfo = new ModeCInfo();

        MsgCInfo msgCInfo = new MsgCInfo();

        //保存邮箱服务器 熊伟
        SaveSMTPInfoReqBD00 SaveSMTPInfoReqBD00=busData00.getSMTPInfo();
        SMTPInfo sMTPInfo=new SMTPInfo();
        if(SaveSMTPInfoReqBD00!=null){
            SaveSMTPInfoRes saveSMTPInfoRes = new SaveSMTPInfoRes();
            SaveSMTPInfoResD data = new SaveSMTPInfoResD();
            if("".equals(SaveSMTPInfoReqBD00.getSMTPURL())||SaveSMTPInfoReqBD00.getSMTPURL()==null){
                throw new SystemException("","服务器地址不能为空");
            }
            if("".equals(SaveSMTPInfoReqBD00.getPassword())||SaveSMTPInfoReqBD00.getPassword()==null){
                throw new SystemException("","密码不能为空");
            }
            if(SaveSMTPInfoReqBD00.getPort()<=0){
                throw new SystemException("","端口不能为空");
            }
            //当前用户
            String userName = CommonUtils.readUser().getRealName();
            //当前时间
            Date date = new Date();
            sMTPInfo.setRuid(SaveSMTPInfoReqBD00.getRuid());
            sMTPInfo.setUserName(SaveSMTPInfoReqBD00.getUserName());
            sMTPInfo.setPassword(SaveSMTPInfoReqBD00.getPassword());
            sMTPInfo.setSMTPURL(SaveSMTPInfoReqBD00.getSMTPURL());
            sMTPInfo.setUseSSL(SaveSMTPInfoReqBD00.getUseSSL());
            sMTPInfo.setPort(SaveSMTPInfoReqBD00.getPort());
            sMTPInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            sMTPInfo.setLastModifyTime(new Date());
            int count = sMTPDAO.UpdateSMTPInfo(sMTPInfo);
            if(count<=0){
                throw new SystemException("","更新邮件服务器信息失败！");
            }
            sMTPDAO.UpdateSMTPInfo(sMTPInfo);
            try{
                if (!StringUtils.isEmpty(SaveSMTPInfoReqBD00.getSMTPURL()) || SaveSMTPInfoReqBD00.getPort()>0 || !StringUtils.isEmpty(SaveSMTPInfoReqBD00.getUserName()) || !StringUtils.isEmpty(SaveSMTPInfoReqBD00.getPassword())
               || SaveSMTPInfoReqBD00.getUseSSL()==true ) {
                    redisTemplate.delete("SMTP_CONFIG");
                     StringBuilder stringBuilder = new StringBuilder();
                    if (!StringUtils.isEmpty(SaveSMTPInfoReqBD00.getSMTPURL())) {
                        stringBuilder.append(SaveSMTPInfoReqBD00.getSMTPURL());
                    }
                    stringBuilder.append("|");
                    if (SaveSMTPInfoReqBD00.getPort()>0) {
                        stringBuilder.append(SaveSMTPInfoReqBD00.getPort());
                    }
                    stringBuilder.append("|");
                    if (!StringUtils.isEmpty(SaveSMTPInfoReqBD00.getUserName())) {
                        String username = baseUtil.encodeStr(SaveSMTPInfoReqBD00.getUserName());
                          stringBuilder.append(username);
                    }
                    stringBuilder.append("|");
                    if (!StringUtils.isEmpty(SaveSMTPInfoReqBD00.getPassword())) {
                        String  password = baseUtil.encodeStr(SaveSMTPInfoReqBD00.getPassword());
                        stringBuilder.append(password);
                    }
                    stringBuilder.append("|");
                    if (SaveSMTPInfoReqBD00.getUseSSL()==true) {
                        stringBuilder.append(1);
                    }
                    if (SaveSMTPInfoReqBD00.getUseSSL()!=true) {
                        stringBuilder.append(0);
                    }
                    redisTemplate.opsForValue().set("SMTP_CONFIG",stringBuilder.toString());
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        //新增模式配置信息
        for (SaveGCInfoReqBD00ModeC modeC : busData00.getModeC()) {
            ModeCInfo objEModeCInfo = modeCDAO.SelectmodeName(modeC.getModeName());

            if (objEModeCInfo != null) {
                modeCInfo.setRuid(objEModeCInfo.getRuid());
                modeCInfo.setModeName(modeC.getModeName());
                modeCInfo.setModeValue(modeC.getModeValue());
                modeCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                modeCInfo.setLastModifyTime(new Date());
                modeCDAO.UpdateModeCInfo(modeCInfo);
            } else {
                modeCInfo.setGuid(CommonUtils.getRandomNumber());
                modeCInfo.setModeName(modeC.getModeName());
                modeCInfo.setModeValue(modeC.getModeValue());
                modeCInfo.setCreator(CommonUtils.readUser().getRealName());
                modeCInfo.setCreateTime(new Date());
                modeCDAO.InsertModeCInfo(modeCInfo);
            }
        }

        //新增消息配置
        for (SaveGCInfoReqBD00MsgC msgC : busData00.getMsgC()) {
            MsgCInfo objEMsgCInfo = msgCDAO.SelectmsgName(msgC.getMsgName());

            if (objEMsgCInfo != null) {
                msgCInfo.setRuid(objEMsgCInfo.getRuid());
                msgCInfo.setMsgName(msgC.getMsgName());
                msgCInfo.setMsgValue(msgC.getMsgValue());
                msgCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                msgCInfo.setLastModifyTime(new Date());
                msgCDAO.UpdateMsgCInfo(msgCInfo);
            } else {
                msgCInfo.setGuid(CommonUtils.getRandomNumber());
                msgCInfo.setMsgName(msgC.getMsgName());
                msgCInfo.setMsgValue(msgC.getMsgValue());
                msgCInfo.setCreator(CommonUtils.readUser().getRealName());
                msgCInfo.setCreateTime(new Date());
                msgCDAO.InsertMsgCInfo(msgCInfo);
            }
        }

        //新增代码生成规则
        if (busData00.getCodeRule().size() > 0) {
            //查询所有代码生成
            for (SaveGCInfoReqBD00CodeRule codeRule : busData00.getCodeRule()) {
                CodeRuleInfo codeRuleInfo = new CodeRuleInfo();

                String CodeType = "";
                if ("物料代码".equals(codeRule.getCodeType())) {
                    CodeType = "00";
                } else if ("BOM代码".equals(codeRule.getCodeType())) {
                    CodeType = "01";
                } else if ("供应商代码".equals(codeRule.getCodeType())) {
                    CodeType = "02";
                } else if ("工单号".equals(codeRule.getCodeType())) {
                    CodeType = "03";
                } else if ("采购单号".equals(codeRule.getCodeType())) {
                    CodeType = "04";
                } else if ("来料通知单号".equals(codeRule.getCodeType())) {
                    CodeType = "05";
                } else if ("领料单号".equals(codeRule.getCodeType())) {
                    CodeType = "06";
                } else if ("退料单号".equals(codeRule.getCodeType())) {
                    CodeType = "07";
                } else if ("成品入库单号".equals(codeRule.getCodeType())) {
                    CodeType = "08";
                } else if ("盘点单号".equals(codeRule.getCodeType())) {
                    CodeType = "09";
                } else if ("企业代码".equals(codeRule.getCodeType())) {
                    CodeType = "10";
                } else if ("仓库代码".equals(codeRule.getCodeType())) {
                    CodeType = "11";
                } else if ("库位代码".equals(codeRule.getCodeType())) {
                    CodeType = "12";
                } else if ("载具家族代码".equals(codeRule.getCodeType())) {
                    CodeType = "13";
                } else if ("订单单号".equals(codeRule.getCodeType())) {
                    CodeType = "14";
                }else if("成品出库单号".equals(codeRule.getCodeType())){
                    CodeType = "15";
                }else if("质检单号".equals(codeRule.getCodeType())){
                    CodeType = "16";
                }else if("OQC单号".equals(codeRule.getCodeType())){
                    CodeType = "17";
                }else {
                    CodeType = "18";
                }

                CodeRuleInfo objECodeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType(CodeType);

                if (objECodeRuleInfo != null) {
                    codeRuleInfo.setRuid(objECodeRuleInfo.getRuid());
                    codeRuleInfo.setPrefix(codeRule.getPrefix());
                    if (!"".equals(codeRule.getDtFormat())) {
                        if ("yy".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yy");
                        } else if ("yymm".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yymm");
                        } else if ("yymmdd".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yymmdd");
                        } else if ("yyyymm".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyymm");
                        } else if ("yyyymmdd".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyymmdd");
                        } else if ("yyyy".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyy");
                        } else {
                            throw new SystemException("", "时间格式不正确，请输入正确的时间格式");
                        }
                    } else {
                        codeRuleInfo.setDtFormat(codeRule.getDtFormat());
                    }
                    codeRuleInfo.setSuffix(codeRule.getSuffix());

                    if (!"".equals(codeRule.getSNLength())) {
                        Pattern p1 = Pattern.compile("^[1-9]+[0-9]*$");
                        if (p1.matcher(String.valueOf(codeRule.getSNLength())).find()) {
                            codeRuleInfo.setsNLength(codeRule.getSNLength());
                        } else {
                            throw new SystemException("", "保存全部序号位数不能为空且为非零正整数");
                        }
                    }
                    codeRuleInfo.setStatus(codeRule.getStatus());

                    codeRuleDAO.UpdateCodeRuleInfo(codeRuleInfo);
                } else {
                    codeRuleInfo.setGuid(CommonUtils.getRandomNumber());
                    codeRuleInfo.setCodeType(CodeType);
                    codeRuleInfo.setPrefix(codeRule.getPrefix());

                    if (!"".equals(codeRule.getDtFormat())) {
                        if ("yy".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yy");
                        } else if ("yymm".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yymm");
                        } else if ("yymmdd".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yymmdd");
                        } else if ("yyyymm".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyymm");
                        } else if ("yyyymmdd".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyymmdd");
                        } else if ("yyyy".equals(codeRule.getDtFormat())) {
                            codeRuleInfo.setDtFormat("yyyy");
                        } else {
                            throw new SystemException("", "时间格式不正确，请输入正确的时间格式");
                        }
                    } else {
                        codeRuleInfo.setDtFormat(codeRule.getDtFormat());
                    }
                    codeRuleInfo.setSuffix(codeRule.getSuffix());
                    if (!"".equals(codeRule.getSNLength())) {
                        Pattern p1 = Pattern.compile("^[1-9]+[0-9]*$");
                        if (p1.matcher(String.valueOf(codeRule.getSNLength())).find()) {
                            codeRuleInfo.setsNLength(codeRule.getSNLength());
                        } else {
                            throw new SystemException("", "保存全部序号位数不能为空且为非零正整数");
                        }
                    }
                    codeRuleInfo.setStart(1);
                    codeRuleInfo.setStep(1);
                    codeRuleInfo.setLastLevel(0);
                    codeRuleInfo.setReset("01");
                    codeRuleInfo.setStatus(codeRule.getStatus());
                    codeRuleInfo.setCreator(CommonUtils.readUser().getRealName());
                    codeRuleInfo.setCreateTime(new Date());

                    codeRuleDAO.InsertCodeRuleInfo(codeRuleInfo);
                }
            }

            //新增数据同步控制
            for (SaveGCInfoReqBD00SynchC synchC : busData00.getSynchC()) {
                SynchCInfo synchCInfo = new SynchCInfo();
                SynchCInfo objesynchCInfo = synchCDAO.SelectBysynchName(synchC.getSynchName());

                if (objesynchCInfo == null) {
                    synchCInfo.setGuid(CommonUtils.getRandomNumber());
                    synchCInfo.setSynchName(synchC.getSynchName());
                    synchCInfo.setStatus(synchC.getStatus());
                    synchCInfo.setCreator(CommonUtils.readUser().getRealName());
                    synchCInfo.setCreateTime(new Date());

                    synchCDAO.InsertSynchcinfo(synchCInfo);
                } else {
                    synchCInfo.setRuid(objesynchCInfo.getRuid());
                    synchCInfo.setSynchName(synchC.getSynchName());
                    synchCInfo.setStatus(synchC.getStatus());
                    synchCInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                    synchCInfo.setLastModifyTime(new Date());

                    synchCDAO.UpdateSynchcinfo(synchCInfo);
                }
            }

        }

        objESaveGCInfoRes.setBody(objESaveGCInfoResB);
        return objESaveGCInfoRes;
    }

    //生成代码规则
    @Override
    public String GetCreateSC(CodeRuleInfo codeRuleInfo) {
        SimpleDateFormat df = TimeCheckout(codeRuleInfo.getDtFormat());
        if (codeRuleInfo.getLastModifyTime() == null || codeRuleInfo.getRemark() == null) {
            codeRuleInfo.setLastModifyTime(new Date());
            codeRuleInfo.setRemark(df.format(codeRuleInfo.getLastModifyTime()));
            codeRuleInfo.setLastLevel(0);
        } else {
            String nowTime = df.format(new Date());
            if (nowTime.equals(codeRuleInfo.getRemark())) {
                codeRuleInfo.setLastModifyTime(new Date());
                codeRuleInfo.setRemark(nowTime);
            } else {
                codeRuleInfo.setLastModifyTime(new Date());
                codeRuleInfo.setRemark(nowTime);
                codeRuleInfo.setLastLevel(0);
            }
        }
        String SCode = "";
        //前缀
        String prefix = codeRuleInfo.getPrefix();
        SCode += prefix;
        //流水号长度
        int SNLength = codeRuleInfo.getsNLength();
        //步长
        int step = codeRuleInfo.getStep();
        //最后流水位
        int lastLevel = codeRuleInfo.getLastLevel();
        int newLastLevel = 0;
        if (lastLevel == 0) {
            //起始位
            newLastLevel = codeRuleInfo.getStart();
        } else {
            newLastLevel = step + lastLevel;
        }

        codeRuleInfo.setLastLevel(newLastLevel);

        String strNewLastLevel = newLastLevel + "";
        if (SNLength - strNewLastLevel.length() < 0) {
            throw new SystemException("", "请到全局配置中调整序号位数");
        }
        SimpleDateFormat timeCode = TimeCheckout(codeRuleInfo.getDtFormat());
        SCode += timeCode.format(codeRuleInfo.getLastModifyTime());
        for (int i = 0; i < SNLength - strNewLastLevel.length(); i++) {
            SCode += "0";
        }
        SCode += strNewLastLevel + codeRuleInfo.getSuffix();
        if (codeRuleDAO.UpdateCodeRuleInfoLastLevel(codeRuleInfo) <= 0) {
            throw new SystemException("", "代码生成失败");
        }

        return SCode;
    }


    public SimpleDateFormat TimeCheckout(String forMat) {
        SimpleDateFormat dfNumber = null;
        if (!"".equals(forMat)) {
            if ("yy".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yy", Locale.CHINESE);
            } else if ("yymm".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yyMM");
            } else if ("yymmdd".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yyMMdd");
            } else if ("yyyymm".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yyyyMM");
            } else if ("yyyymmdd".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yyyyMMdd");
            } else if ("yyyy".equals(forMat)) {
                dfNumber = new SimpleDateFormat("yyyy");
            } else {
                throw new SystemException("", "时间格式不正确，请输入正确的时间格式");
            }
        } else {
            throw new SystemException("", "时间格式不正确，请输入正确的时间格式");
        }
        return dfNumber;
    }
}
