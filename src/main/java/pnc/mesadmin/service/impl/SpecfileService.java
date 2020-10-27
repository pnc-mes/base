package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseInitField;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllWorkFoolControllerInfo.GetAllWorkFoolControllerResD;
import pnc.mesadmin.dto.GetMWFInfo.*;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveSOPInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.SpecfileIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺文件信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class SpecfileService implements SpecfileIService {

    @Resource
    private SOPDAO SOPDAO;

    @Resource
    private SOPDlDAO sopDlInfoMapper;

    @Resource
    private FileGpDAO fileGpDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WFSpecDAO wfSpecDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private DevGpDAO devGpDAO;

    @Resource
    private DCVerDAO dcVerDAO;

    @Resource
    private WFDAO wfdao;
    @Resource
    private WFVerDAO wfVerDAO;

    @Override
    public PageResult<GetAllWorkFoolControllerResD> GetAllWorkFoolControllerResD(BaseRequest req) {
        List<GetAllWorkFoolControllerResD> getAllWorkFoolControllerResDS=new ArrayList<GetAllWorkFoolControllerResD>();
        Page<SOPInfo> sopInfoPage=new Page<>(req.getCurrent(),req.getSize());
        List<BaseInitField> baseInitFields=req.getFields();
        if(baseInitFields!=null){
            for(BaseInitField baseInitField:baseInitFields){
                if(baseInitField.getFieldName().equals("WFVerGd")){
                    WFInfo wfInfo=wfdao.SelectWFInfo(Integer.valueOf(baseInitField.getFieldVal()));
                    if(wfInfo!=null){
                        WFVerInfo wfVerInfo=wfVerDAO.SelectByGuid(wfInfo.getVerGd());
                        if(wfVerInfo!=null){
                            baseInitField.setFieldVal(wfVerInfo.getGuid());
                        }
                    }
                }else if(baseInitField.getFieldName().equals("MaVerGd")){
                   MaVerInfo maVerInfo=maVerDAO.SelectByRuid(Integer.valueOf(baseInitField.getFieldVal()));
                    if(maVerInfo!=null){
                      baseInitField.setFieldVal(maVerInfo.getGuid());
                    }
                }
            }
        }
        IPage<SOPInfo> sopInfoIPage=SOPDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(baseInitFields));
        List<SOPInfo> sopInfos=sopInfoIPage.getRecords();
        if(sopInfos!=null&&sopInfos.size()>0){
            for(SOPInfo sopInfo:sopInfos){
                GetAllWorkFoolControllerResD getAllWorkFoolControllerResD=new GetAllWorkFoolControllerResD();
                getAllWorkFoolControllerResD.setCreator(sopInfo.getCreator());
                getAllWorkFoolControllerResD.setCreateTime(DateUtil.format( sopInfo.getCreateTime()));
                getAllWorkFoolControllerResD.setRemark(sopInfo.getRemark());
                getAllWorkFoolControllerResD.setLastModifyMan(sopInfo.getLastModifyMan());
                getAllWorkFoolControllerResD.setLastModifyTime(DateUtil.format(sopInfo.getLastModifyTime()));
                MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(sopInfo.getMaVerGd());
                if(maVerInfo!=null){
                    getAllWorkFoolControllerResD.setMaterialRd(maVerInfo.getRuid());
                    getAllWorkFoolControllerResD.setMaterialGd(maVerInfo.getGuid());
                    getAllWorkFoolControllerResD.setMaterialCode(maVerInfo.getMaterialCode());
                    getAllWorkFoolControllerResD.setMaterialName(maVerInfo.getMaterialName());
                    getAllWorkFoolControllerResD.setMaterialDes(maVerInfo.getMaterialDes());
                    MaterialInfo materialInfo=materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                    if(materialInfo!=null){
                        getAllWorkFoolControllerResD.setMaRd(materialInfo.getRuid());
                    }
                }
                WFVerInfo wfVerInfo=wfVerDAO.SelectByGuid(sopInfo.getWFVerGd());
                if(wfVerInfo!=null){
                    getAllWorkFoolControllerResD.setWorkFoolGd(wfVerInfo.getGuid());
                    getAllWorkFoolControllerResD.setWorkFoolRd(wfVerInfo.getRuid());
                    getAllWorkFoolControllerResD.setWorkFoolName(wfVerInfo.getwFName());
                    WFInfo wfInfo=wfdao.SelectByName(wfVerInfo.getwFName());
                    if(wfInfo!=null){
                        getAllWorkFoolControllerResD.setWfRd(wfInfo.getRuid());
                    }
                }
                getAllWorkFoolControllerResDS.add(getAllWorkFoolControllerResD);

            }
        }


        return new PageResult(sopInfoIPage.getTotal(), sopInfoIPage.getPages(), sopInfoIPage.getCurrent(), sopInfoIPage.getSize(), getAllWorkFoolControllerResDS);
    }

    //查询物料工艺流程工序信息00时查询
    public GetMWFInfoRes GetSelectByRuid(GetMWFInfoReqBD00 busData00) throws SystemException {
        GetMWFInfoRes objEGetMWFInfoRes = new GetMWFInfoRes();
        GetMWFInfoResB body = new GetMWFInfoResB();

        GetMWFInfoResD data = new GetMWFInfoResD();

        List<GetMWFInfoResDSpecInfo> objEGetMWFInfoResDSpecInfo = new ArrayList<GetMWFInfoResDSpecInfo>();
        GetMWFInfoResDSpecInfo objeobjEGetMWFInfoResDSpecInfo = null;

        //查询物料版本信息
        MaVerInfo objeMaVerInfo = maVerDAO.SelectByRuid(busData00.getMaRd());
        if (objeMaVerInfo == null) {
            throw new SystemException("", "查询物料信息为空！");
        }
        //查询物料信息
        MaterialInfo objeMaterialInfo = materialDAO.SelectByGuid(objeMaVerInfo.getMaGd());
        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData00.getWFVerRd());
        WFInfo wfInfo = new WFInfo();
        if (objWFVerInfo != null) {
            wfInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
            if (wfInfo == null) {
                throw new SystemException("", "查询工艺信息为空！");
            }
        }
        //查询物料工艺工序信息
        List<WFSpecInfo> objEWFSpecInfo = new ArrayList<>();
        WFInfo objEWFInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
        if (objEWFInfo != null) {
            objEWFSpecInfo = wfSpecDAO.SelectByWFVerGd(objEWFInfo.getVerGd());
        }

        //查询工艺文件信息
        SOPInfo objESOPInfo = new SOPInfo();
        objESOPInfo.setMaVerGd(objeMaVerInfo.getGuid());
        objESOPInfo.setWFVerGd(wfInfo.getVerGd());
        objESOPInfo = SOPDAO.SelectBymaVerGdAndWfGd(objESOPInfo);
        List<SOPDlInfo> objESOPDlInfo = new ArrayList<>();
        //当工艺流程信息不为空时判断与wfInfo数量是不是相等，大于以wfinfo为准，
        // 小于把wfInfo多的一条添加到工艺流程信息
        List<SOPDlInfo> sopDlInfosAdd = new ArrayList<>();
        List<Integer> wfSpecInfosAdd = new ArrayList<>();
        if (objESOPInfo != null) {
            //查询工艺文件明细信息
            objESOPDlInfo = sopDlInfoMapper.SelectBySOPGd(objESOPInfo.getGuid());
            if (objESOPDlInfo.size() < objEWFSpecInfo.size()) {
                for (WFSpecInfo wfModel : objEWFSpecInfo) {
                    Boolean isTrue = false;
                    for (SOPDlInfo soModel : objESOPDlInfo) {
                        if (wfModel.getSpecVerGd().equals(soModel.getSpecVerGd())) {
                            isTrue = true;
                        }
                    }
                    if (!isTrue) {
                        SOPDlInfo sopDlInfo = new SOPDlInfo();
                        sopDlInfo.setSpecVerGd(wfModel.getSpecVerGd());
                        sopDlInfosAdd.add(sopDlInfo);
                    }
                }
                objESOPDlInfo.addAll(sopDlInfosAdd);
            } else if (objESOPDlInfo.size() > objEWFSpecInfo.size()) {
                for (Iterator<SOPDlInfo> iterator = objESOPDlInfo.iterator(); iterator.hasNext();) {
                    SOPDlInfo sopDlInfo=iterator.next();
                    Boolean isTrue = false;
                    for (WFSpecInfo wfModel : objEWFSpecInfo) {
                        if (sopDlInfo.getSpecVerGd().equals(wfModel.getSpecVerGd())) {
                            isTrue = true;
                        }
                    }
                    if (!isTrue) {
                        iterator.remove();
                    }
                }
            }
        }


        if (objESOPInfo != null) {
            if (objESOPDlInfo != null && objESOPDlInfo.size() > 0) {
                for (SOPDlInfo obj : objESOPDlInfo) {
                    objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();

                    SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                    FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(obj.getFileGrGd()); //查询文件组信息
                    DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(obj.getDevGrGd());//查询设备组信息
                    DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(obj.getDCVerGd());//查询数据采集信息
                    DCVerInfo objEDCVerInfo1 = dcVerDAO.selectDCVerInfoByGuid(obj.getAfDCVerGd());//查询数据采集信息
                    // 赋值
                    if (objESpecVerInfo != null) {
                        objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                        objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                        //查询文件组
                        GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                        if (objEFileGpInfo != null) {
                            objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                            objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                            objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                        } else {
                            objeFileGrInfo.setFileGrRd(0);
                            objeFileGrInfo.setFileGrName("");
                            objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                        }

                        //查询设备组
                        GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                        if (objEDevGpInfo != null) {
                            objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                            objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                            objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                        } else {
                            objeDevice.setDevGpRd(0);
                            objeDevice.setDevGpName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                        }
                        GetMWFInfoResDEndDC DD=new GetMWFInfoResDEndDC();
                        if (objEDCVerInfo1 != null) {
                            DD.setDCVerRd(objEDCVerInfo1.getRuid());
                            DD.setDCName(objEDCVerInfo1.getDcName());
                            objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                        } else {
                            DD.setDCVerRd(0);
                            DD.setDCName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                        }
                        //查询数据采集
                        GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                        if (objEDCVerInfo != null) {
                            objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                            objEDC.setDCName(objEDCVerInfo.getDcName());
                            objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                        } else {
                            objEDC.setDCVerRd(0);
                            objEDC.setDCName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                        }

                        objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                    }
                }
            } else {
                if (objEWFSpecInfo != null && objEWFSpecInfo.size() > 0) {
                    for (WFSpecInfo obj : objEWFSpecInfo) {
                        objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();
                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                        if (objESpecVerInfo != null) {
                            objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                            objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                            FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                            DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                            DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());
                            DCVerInfo objEDCVerInfo1 = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getAfDCVerGd());
                            //查询文件组
                            GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                            if (objEFileGpInfo != null) {
                                objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            } else {
                                objeFileGrInfo.setFileGrRd(0);
                                objeFileGrInfo.setFileGrName("");
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            }

                            //查询设备组
                            GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                            if (objEDevGpInfo != null) {
                                objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            } else {
                                objeDevice.setDevGpRd(0);
                                objeDevice.setDevGpName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            }
                            GetMWFInfoResDEndDC DD=new GetMWFInfoResDEndDC();
                            if (objEDCVerInfo1 != null) {
                                DD.setDCVerRd(objEDCVerInfo1.getRuid());
                                DD.setDCName(objEDCVerInfo1.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                            } else {
                                DD.setDCVerRd(0);
                                DD.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                            }
                            //查询数据采集
                            GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                            if (objEDCVerInfo != null) {
                                objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                objEDC.setDCName(objEDCVerInfo.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            } else {
                                objEDC.setDCVerRd(0);
                                objEDC.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            }

                            objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                        }
                    }
                }
            }
        } else {
            if (objEWFSpecInfo != null && objEWFSpecInfo.size() > 0) {
                for (WFSpecInfo obj : objEWFSpecInfo) {
                    objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();
                    SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                    if (objESpecVerInfo != null) {
                        objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                        objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                        DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                        DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());
                        DCVerInfo objEDCVerInfo1 = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getAfDCVerGd());
                        //查询文件组
                        GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                        if (objEFileGpInfo != null) {
                            objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                            objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                            objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                        } else {
                            objeFileGrInfo.setFileGrRd(0);
                            objeFileGrInfo.setFileGrName("");
                            objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                        }

                        //查询设备组
                        GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                        if (objEDevGpInfo != null) {
                            objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                            objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                            objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                        } else {
                            objeDevice.setDevGpRd(0);
                            objeDevice.setDevGpName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                        }
                        GetMWFInfoResDEndDC DD=new GetMWFInfoResDEndDC();
                        if (objEDCVerInfo1 != null) {
                            DD.setDCVerRd(objEDCVerInfo1.getRuid());
                            DD.setDCName(objEDCVerInfo1.getDcName());
                            objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                        } else {
                            DD.setDCVerRd(0);
                            DD.setDCName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDCEnd(DD);
                        }
                        //查询数据采集
                        GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                        if (objEDCVerInfo != null) {
                            objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                            objEDC.setDCName(objEDCVerInfo.getDcName());
                            objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                        } else {
                            objEDC.setDCVerRd(0);
                            objEDC.setDCName("");
                            objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                        }

                        objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                    }

                }
            }

        }
        if (objeMaterialInfo != null) {
            data.setMaCode(objeMaterialInfo.getMaterialCode());
            data.setMaName(objeMaterialInfo.getMaterialName());
            data.setMaDes(objeMaterialInfo.getMaterialDes());
        }
        data.setSpecInfo(objEGetMWFInfoResDSpecInfo);


        body.setData(data);
        objEGetMWFInfoRes.setBody(body);
        return objEGetMWFInfoRes;
    }
        /*GetMWFInfoRes objEGetMWFInfoRes = new GetMWFInfoRes();
        GetMWFInfoResB body = new GetMWFInfoResB();

        GetMWFInfoResD data = new GetMWFInfoResD();

        List<GetMWFInfoResDSpecInfo> objEGetMWFInfoResDSpecInfo = new ArrayList<GetMWFInfoResDSpecInfo>();
        GetMWFInfoResDSpecInfo objeobjEGetMWFInfoResDSpecInfo = null;
        //查询物料信息
        MaterialInfo objeMaterialInfo = materialDAO.SelectMaterialInfo(busData00.getMaRd());

        if (objeMaterialInfo == null) {
            throw new SystemException("", "查询物料信息为空！");
        }

        //查询物料版本信息
        //MaVerInfo objeMaVerInfo = maVerDAO.SelectMaverInfo(objeMaterialInfo.getVerGd());
        WFInfo wfInfo = wfdao.SelectWFInfo(busData00.getWFVerRd());

        //查询工艺文件信息
        //SOPInfo objESOPInfo = SOPDAO.SelectBymaVerGd(objeMaterialInfo.getVerGd());
        SOPInfo objESOPInfo = new SOPInfo();
        if (wfInfo != null) {
            objESOPInfo.setMaVerGd(objeMaterialInfo.getVerGd());
            objESOPInfo.setWFVerGd(wfInfo.getVerGd());
            objESOPInfo = SOPDAO.SelectBymaVerGdAndWfGd(objESOPInfo);

            if (objESOPInfo != null) {
                //查询工艺文件明细信息
                List<SOPDlInfo> objESOPDlInfo = sopDlInfoMapper.SelectBySOPGd(objESOPInfo.getGuid());
                if (objESOPDlInfo != null && objESOPDlInfo.size() > 0) {
                    for (SOPDlInfo obj : objESOPDlInfo) {
                        objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();

                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(obj.getFileGrGd()); //查询文件组信息
                        DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(obj.getDevGrGd());//查询设备组信息
                        DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(obj.getDCVerGd());//查询数据采集信息
                        // 赋值
                        if (objESpecVerInfo != null) {
                            objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                            objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                            //查询文件组
                            GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                            if (objEFileGpInfo != null) {
                                objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            } else {
                                objeFileGrInfo.setFileGrRd(0);
                                objeFileGrInfo.setFileGrName("");
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            }

                            //查询设备组
                            GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                            if (objEDevGpInfo != null) {
                                objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            } else {
                                objeDevice.setDevGpRd(0);
                                objeDevice.setDevGpName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            }
                            //查询数据采集
                            GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                            if (objEDCVerInfo != null) {
                                objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                objEDC.setDCName(objEDCVerInfo.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            } else {
                                objEDC.setDCVerRd(0);
                                objEDC.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            }

                            objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                        }
                    }
                } else {
                    //查询物料工艺工序信息
                    //查询工艺流程
                    WFInfo objEWFInfo = wfdao.SelectByGuid(wfInfo.getGuid());
                    List<WFSpecInfo> objEWFSpecInfo = null;
                    if (objEWFInfo != null) {
                        objEWFSpecInfo = wfSpecDAO.SelectByWFVerGd(objEWFInfo.getVerGd());
                    }
                    if (objEWFSpecInfo != null && objEWFSpecInfo.size() > 0) {
                        for (WFSpecInfo obj : objEWFSpecInfo) {
                            objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();
                            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                            if (objESpecVerInfo != null) {
                                objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                                objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                                FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                                DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                                DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());

                                //查询文件组
                                GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                                if (objEFileGpInfo != null) {
                                    objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                    objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                    objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                                } else {
                                    objeFileGrInfo.setFileGrRd(0);
                                    objeFileGrInfo.setFileGrName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                                }

                                //查询设备组
                                GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                                if (objEDevGpInfo != null) {
                                    objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                    objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                    objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                                } else {
                                    objeDevice.setDevGpRd(0);
                                    objeDevice.setDevGpName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                                }
                                //查询数据采集
                                GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                                if (objEDCVerInfo != null) {
                                    objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                    objEDC.setDCName(objEDCVerInfo.getDcName());
                                    objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                                } else {
                                    objEDC.setDCVerRd(0);
                                    objEDC.setDCName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                                }

                                objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                            }
                        }
                    }
                }
            } else {
                //查询物料工艺工序信息
                WFInfo objEWFInfo = wfdao.SelectByGuid(wfInfo.getGuid());
                List<WFSpecInfo> objEWFSpecInfo = null;
                if (objEWFInfo != null) {
                    objEWFSpecInfo = wfSpecDAO.SelectByWFVerGd(objEWFInfo.getVerGd());
                }
                if (objEWFSpecInfo != null && objEWFSpecInfo.size() > 0) {
                    for (WFSpecInfo obj : objEWFSpecInfo) {
                        objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();
                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());//查询工序版本信息
                        if (objESpecVerInfo != null) {
                            objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                            objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                            FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                            DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                            DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());
                            //查询文件组
                            GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                            if (objEFileGpInfo != null) {
                                objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            } else {
                                objeFileGrInfo.setFileGrRd(0);
                                objeFileGrInfo.setFileGrName("");
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            }

                            //查询设备组
                            GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                            if (objEDevGpInfo != null) {
                                objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            } else {
                                objeDevice.setDevGpRd(0);
                                objeDevice.setDevGpName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            }
                            //查询数据采集
                            GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                            if (objEDCVerInfo != null) {
                                objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                objEDC.setDCName(objEDCVerInfo.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            } else {
                                objEDC.setDCVerRd(0);
                                objEDC.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            }

                            objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                        }

                    }
                }

            }
        }
        if (objeMaterialInfo != null) {
            data.setMaCode(objeMaterialInfo.getMaterialCode());
            data.setMaName(objeMaterialInfo.getMaterialName());
            data.setMaDes(objeMaterialInfo.getMaterialDes());
        }
        data.setSpecInfo(objEGetMWFInfoResDSpecInfo);


        body.setData(data);
        objEGetMWFInfoRes.setBody(body);
        return objEGetMWFInfoRes;
    }*/

    //查询物料工艺流程工序信息01时查询
    public GetMWFInfoRes GetSelectByruid(GetMWFInfoReqBD01 busData01) throws SystemException {
        GetMWFInfoRes objEGetMWFInfoRes = new GetMWFInfoRes();

        GetMWFInfoResB body = new GetMWFInfoResB();

        GetMWFInfoResD data = new GetMWFInfoResD();

        List<GetMWFInfoResDSpecInfo> objEGetMWFInfoResDSpecInfo = new ArrayList<GetMWFInfoResDSpecInfo>();
        GetMWFInfoResDSpecInfo objeobjEGetMWFInfoResDSpecInfo = null;
        //查询物料版本信息
        MaVerInfo maVerInfo = maVerDAO.SelectByRuid(busData01.getMVerRd());

        if (maVerInfo == null) {
            throw new SystemException("", "查询物料信息为空！");
        }

        //查询物料描述信息
        MaterialInfo objeMaterialInfo = materialDAO.SelectByGuid(maVerInfo.getMaGd());
        //查询工艺文件信息

        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData01.getWFVerRd());
        WFInfo wfInfo = null;
        if (objWFVerInfo != null) {
            wfInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
        }
        SOPInfo objESOPInfo = new SOPInfo();
        if (wfInfo != null) {
            objESOPInfo.setMaVerGd(maVerInfo.getGuid());
            objESOPInfo.setWFVerGd(wfInfo.getVerGd());
            objESOPInfo = SOPDAO.SelectBymaVerGdAndWfGd(objESOPInfo);
            if (objESOPInfo != null) {
                //查询工艺文件明细信息
                List<SOPDlInfo> objESOPDlInfo = sopDlInfoMapper.SelectBySOPGd(objESOPInfo.getGuid());
                if (objESOPDlInfo != null && objESOPDlInfo.size() > 0) {

                    for (SOPDlInfo obj : objESOPDlInfo) {
                        objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();

                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd()); //查询工序版本信息
                        FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(obj.getFileGrGd()); //查询文件组信息
                        DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(obj.getDevGrGd());//查询设备组信息
                        DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(obj.getDCVerGd());//查询数据采集信息
                        // 赋值
                        if (objESpecVerInfo != null) {
                            objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                            objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                            //查询文件组信息
                            GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                            if (objEFileGpInfo != null) {
                                objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            } else {
                                objeFileGrInfo.setFileGrRd(0);
                                objeFileGrInfo.setFileGrName("");
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            }

                            //查询设备组
                            GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                            if (objEDevGpInfo != null) {
                                objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            } else {
                                objeDevice.setDevGpRd(0);
                                objeDevice.setDevGpName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            }
                            //查询数据采集
                            GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                            if (objEDCVerInfo != null) {
                                objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                objEDC.setDCName(objEDCVerInfo.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            } else {
                                objEDC.setDCVerRd(0);
                                objEDC.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            }

                            objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);

                        }

                    }
                } else {
                    //查询物料工艺工序信息
                    WFInfo objEWFInfo = wfdao.SelectByGuid(wfInfo.getGuid());
                    List<WFSpecInfo> objEWFSpecInfos = null;
                    if (objEWFInfo != null) {
                        objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFInfo.getVerGd());
                    }

                    if (objEWFSpecInfos != null && objEWFSpecInfos.size() > 0) {
                        for (WFSpecInfo obj : objEWFSpecInfos) {
                            objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();
                            //查询工序版本信息
                            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());
                            if (objESpecVerInfo != null) {
                                objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                                objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                                FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                                DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                                DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());

                                //查询文件组
                                GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                                if (objEFileGpInfo != null) {
                                    objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                    objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                    objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                                } else {
                                    objeFileGrInfo.setFileGrRd(0);
                                    objeFileGrInfo.setFileGrName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                                }

                                //查询设备组
                                GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                                if (objEDevGpInfo != null) {
                                    objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                    objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                    objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                                } else {
                                    objeDevice.setDevGpRd(0);
                                    objeDevice.setDevGpName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                                }
                                //查询数据采集
                                GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                                if (objEDCVerInfo != null) {
                                    objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                    objEDC.setDCName(objEDCVerInfo.getDcName());
                                    objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                                } else {
                                    objEDC.setDCVerRd(0);
                                    objEDC.setDCName("");
                                    objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                                }

                                objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                            }

                        }
                    }
                }
            } else {
                //查询物料工艺工序信息
                WFInfo objEWFInfo = wfdao.SelectByGuid(wfInfo.getGuid());
                List<WFSpecInfo> objEWFSpecInfos = null;
                if (objEWFInfo != null) {
                    objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFInfo.getVerGd());
                }

                if (objEWFSpecInfos != null && objEWFSpecInfos.size() > 0) {
                    for (WFSpecInfo obj : objEWFSpecInfos) {
                        objeobjEGetMWFInfoResDSpecInfo = new GetMWFInfoResDSpecInfo();

                        //查询工序版本信息
                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());
                        if (objESpecVerInfo != null) {
                            objeobjEGetMWFInfoResDSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                            objeobjEGetMWFInfoResDSpecInfo.setSpecName(objESpecVerInfo.getSpecName() + "-" + objESpecVerInfo.getVersion());

                            FileGpInfo objEFileGpInfo = fileGpDAO.SelectByguid(objESpecVerInfo.getFileGrGd());
                            DevGpInfo objEDevGpInfo = devGpDAO.SelectByguid(objESpecVerInfo.getDevGrGd());
                            DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByGuid(objESpecVerInfo.getdCVerGd());
                            //查询文件组
                            GetMWFInfoResDFileGrInfo objeFileGrInfo = new GetMWFInfoResDFileGrInfo();
                            if (objEFileGpInfo != null) {
                                objeFileGrInfo.setFileGrRd(objEFileGpInfo.getRuid());
                                objeFileGrInfo.setFileGrName(objEFileGpInfo.getFileGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            } else {
                                objeFileGrInfo.setFileGrRd(0);
                                objeFileGrInfo.setFileGrName("");
                                objeobjEGetMWFInfoResDSpecInfo.setFileGrInfo(objeFileGrInfo);
                            }
                            //查询设备组
                            GetMWFInfoResDDevice objeDevice = new GetMWFInfoResDDevice();
                            if (objEDevGpInfo != null) {
                                objeDevice.setDevGpRd(objEDevGpInfo.getRuid());
                                objeDevice.setDevGpName(objEDevGpInfo.getDevGpName());
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            } else {
                                objeDevice.setDevGpRd(0);
                                objeDevice.setDevGpName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDevice(objeDevice);
                            }
                            //查询数据采集
                            GetMWFInfoResDDC objEDC = new GetMWFInfoResDDC();
                            if (objEDCVerInfo != null) {
                                objEDC.setDCVerRd(objEDCVerInfo.getRuid());
                                objEDC.setDCName(objEDCVerInfo.getDcName());
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            } else {
                                objEDC.setDCVerRd(0);
                                objEDC.setDCName("");
                                objeobjEGetMWFInfoResDSpecInfo.setDC(objEDC);
                            }

                            objEGetMWFInfoResDSpecInfo.add(objeobjEGetMWFInfoResDSpecInfo);
                        }

                    }
                }
            }
        }

        if (maVerInfo != null) {
            data.setMaCode(maVerInfo.getMaterialCode());
            data.setMaName(maVerInfo.getMaterialName());
            if (objeMaterialInfo != null) {
                data.setMaDes(objeMaterialInfo.getMaterialDes());
            } else {
                data.setMaDes("");
            }
        }
        data.setSpecInfo(objEGetMWFInfoResDSpecInfo);

        body.setData(data);
        objEGetMWFInfoRes.setBody(body);

        return objEGetMWFInfoRes;
    }

    //保存工艺文件信息
    public SaveSOPInfoRes AddinsertSOPInfo(SaveSOPInfoReqBD00 busData00, SOPInfo sopInfo) throws SystemException {
        SaveSOPInfoRes saveSOPInfoRes = new SaveSOPInfoRes();

        SaveSOPInfoResB body = new SaveSOPInfoResB();

        SaveSOPInfoResD data = new SaveSOPInfoResD();


        SOPDlInfo sopDlInfo = new SOPDlInfo();

        //查询物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(busData00.getMaVerRd());

        if (objEMaVerInfo == null) {
            throw new SystemException("", "查询物料版本信息为空！");
        }

        //查询工艺流程
        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData00.getWFVerRd());
        WFInfo objEWFInfo = new WFInfo();
        if (objWFVerInfo != null) {
            objEWFInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
            if (objEWFInfo == null) {
                throw new SystemException("", "工艺流程信息不存在！");
            }
        }

        SOPInfo sopInfo1=SOPDAO.selectOne(new QueryWrapper<SOPInfo>().eq("maVerGd", objEMaVerInfo.getGuid()).eq("WFVerGd",objWFVerInfo.getGuid()));
        if(sopInfo1!=null){
            throw new SystemException("", "保存失败,当前物料:"+objEMaVerInfo.getMaterialCode()+"和工艺流程:"+objWFVerInfo.getwFName()+"已存在");
        }

        // 赋值新增工艺文件信息
        sopInfo.setGuid(CommonUtils.getRandomNumber());
        sopInfo.setMaVerGd(objEMaVerInfo.getGuid());
        sopInfo.setWFVerGd(objEWFInfo.getVerGd());//工艺流程明细标识
        sopInfo.setCreator(CommonUtils.readUser().getRealName());
        sopInfo.setCreateTime(new Date());
        sopInfo.setRemark(busData00.getRemark());

        List<SaveSOPInfoReqBD00SpecInfo> SpecInfoss = new ArrayList<SaveSOPInfoReqBD00SpecInfo>();
        int i =1;
        for (SaveSOPInfoReqBD00SpecInfo SpecInfos : busData00.getSpecInfo()) {
            SaveSOPInfoReqBD00SpecInfo objESaveSOPInfoReqBD00SpecInfo = new SaveSOPInfoReqBD00SpecInfo();

            //查询工序版本信息
            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(SpecInfos.getSpecVerRd());

            if (objESpecVerInfo == null) {
                throw new SystemException("", "查询工序版本信息为空！");
            }

            objESaveSOPInfoReqBD00SpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());

            //查询文件组信息
            FileGpInfo objEfileGpInfo = fileGpDAO.SelectByruid(SpecInfos.getFileGrRd());
            if(objEfileGpInfo!=null){
                sopDlInfo.setFileGrGd(objEfileGpInfo.getGuid());
            }else {
                sopDlInfo.setFileGrGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setFileGrRd(SpecInfos.getFileGrRd());

            //查询设备组信息
            DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(SpecInfos.getDevGpRd());
            if(objEDevGpInfo!=null){
                sopDlInfo.setDevGrGd(objEDevGpInfo.getGuid());
            }else{
                sopDlInfo.setDevGrGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setDevGpRd(SpecInfos.getDevGpRd());

            //查询数据采集qian信息
            DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getDCVerRd());
            if(objEDCVerInfo!=null){
                sopDlInfo.setDCVerGd(objEDCVerInfo.getGuid());
            }else {
                sopDlInfo.setDCVerGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setDCVerRd(SpecInfos.getDCVerRd());
            //查询数据采集后信息
            DCVerInfo objEDCVerInfo1 = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getAfDCVerRd());
            if(objEDCVerInfo1!=null){
                sopDlInfo.setAfDCVerGd(objEDCVerInfo1.getGuid());
            }else {
                sopDlInfo.setAfDCVerGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setAfDCVerRd(SpecInfos.getAfDCVerRd());
            //查询工艺文件所有信息
            List<SOPDlInfo> objEFileGpInfos = sopDlInfoMapper.SelectAllSOPDlInfo();

            if (objEFileGpInfos == null) {
                throw new SystemException("MG_MES4001411010001F", "查询工艺文件信息为空！");
            }


            //新增工序工艺文件信息
            sopDlInfo.setGuid(CommonUtils.getRandomNumber());
            sopDlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            sopDlInfo.setSOPGd(sopInfo.getGuid());
            sopDlInfo.setSpecVerGd(objESpecVerInfo.getGuid());
            sopDlInfo.setSpecName(objESpecVerInfo.getSpecName());
            sopDlInfo.setCreator(CommonUtils.readUser().getRealName());
            sopDlInfo.setCreateTime(new Date());
            sopDlInfo.setRemark(busData00.getRemark());

            SpecInfoss.add(objESaveSOPInfoReqBD00SpecInfo);

            if (sopDlInfoMapper.InsertSOPDlInfo(sopDlInfo) <= 0) {
                throw new SystemException("MG_MES4001413010000F", "新增工艺工序明细信息失败");
            }
            i++;
        }

        busData00.setSpecInfo(SpecInfoss);


        // 保存
        int count = SOPDAO.InsertSOPInfo(sopInfo);

        if (count <= 0) throw new SystemException("MG_MES4001413010000F", "新增工艺文件信息失败！");

        body.setData(data);
        saveSOPInfoRes.setBody(body);

        return saveSOPInfoRes;
    }

    //更新工艺文件信息
    public SaveSOPInfoRes ModupdateSOPInfo(SaveSOPInfoReqBD02 busData02, SOPInfo sopInfo) throws SystemException {
        SaveSOPInfoRes saveSOPInfoRes = new SaveSOPInfoRes();

        SaveSOPInfoResB body = new SaveSOPInfoResB();

        SaveSOPInfoResD data = new SaveSOPInfoResD();
        MaterialInfo materialInfo=materialDAO.SelectMaterialInfo(busData02.getMaVerRd());
        if (materialInfo == null) {
            throw new SystemException("", "查询物料信息为空！");
        }
        //查询物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(materialInfo.getVerGd());

        if (objEMaVerInfo == null) {
            throw new SystemException("", "查询物料版本信息为空！");
        }

        //查询工艺流程
        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData02.getWFVerRd());
        WFInfo objEWFInfo = new WFInfo();
        if (objWFVerInfo != null) {
            objEWFInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
            if (objEWFInfo == null) {
                throw new SystemException("", "工艺流程信息不存在！");
            }
        }

        SOPInfo sopInfo1=SOPDAO.selectOne(new QueryWrapper<SOPInfo>().eq("maVerGd", objEMaVerInfo.getGuid()).eq("WFVerGd",objWFVerInfo.getGuid()));
        if(sopInfo1==null){
            throw new SystemException("", "保存失败,当前物料:"+objEMaVerInfo.getMaterialCode()+"和工艺流程:"+objWFVerInfo.getwFName()+"不存在");
        }
        List<SOPDlInfo> sopDlInfos=sopDlInfoMapper.SelectBySOPGd(sopInfo1.getGuid());
        if(sopDlInfos!=null&&sopDlInfos.size()>0){
            for(SOPDlInfo obj:sopDlInfos){
                if(sopDlInfoMapper.DeleteSOPDlInfo(obj.getRuid())<=0){
                    throw new SystemException("", "删除明细信息失败！");
                }
            }
        }

        if(SOPDAO.DeleteSOPInfo(sopInfo1.getRuid())<=0){
            throw new SystemException("", "删除主表信息失败！");
        }
        SOPDlInfo sopDlInfo = new SOPDlInfo();



        // 赋值新增工艺文件信息
        sopInfo.setGuid(CommonUtils.getRandomNumber());
        sopInfo.setMaVerGd(objEMaVerInfo.getGuid());
        sopInfo.setWFVerGd(objEWFInfo.getVerGd());//工艺流程明细标识
        sopInfo.setCreator(CommonUtils.readUser().getRealName());
        sopInfo.setCreateTime(new Date());
        sopInfo.setRemark(busData02.getRemark());

        List<SaveSOPInfoReqBD02SpecInfo> SpecInfoss = new ArrayList<SaveSOPInfoReqBD02SpecInfo>();

        int i=1;
        for (SaveSOPInfoReqBD02SpecInfo SpecInfos : busData02.getSpecInfo()) {
            SaveSOPInfoReqBD02SpecInfo objESaveSOPInfoReqBD00SpecInfo = new SaveSOPInfoReqBD02SpecInfo();

            //查询工序版本信息
            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(SpecInfos.getSpecVerRd());

            if (objESpecVerInfo == null) {
                throw new SystemException("", "查询工序版本信息为空！");
            }

            objESaveSOPInfoReqBD00SpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());

            //查询文件组信息
            FileGpInfo objEfileGpInfo = fileGpDAO.SelectByruid(SpecInfos.getFileGrRd());
            if(objEfileGpInfo!=null){
                sopDlInfo.setFileGrGd(objEfileGpInfo.getGuid());
            }else {
                sopDlInfo.setFileGrGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setFileGrRd(SpecInfos.getFileGrRd());

            //查询设备组信息
            DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(SpecInfos.getDevGpRd());
            if(objEDevGpInfo!=null){
                sopDlInfo.setDevGrGd(objEDevGpInfo.getGuid());
            }else {
                sopDlInfo.setDevGrGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setDevGpRd(SpecInfos.getDevGpRd());

            //查询数据采集前信息
            DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getDCVerRd());
            if(objEDCVerInfo!=null){
                sopDlInfo.setDCVerGd(objEDCVerInfo.getGuid());
            }else {
                sopDlInfo.setDCVerGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setDCVerRd(SpecInfos.getDCVerRd());

            //查询数据采集后信息
            DCVerInfo objEDCVerInfo1 = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getAfDCVerRd());
            if(objEDCVerInfo1!=null){
                sopDlInfo.setAfDCVerGd(objEDCVerInfo1.getGuid());
            }else {
                sopDlInfo.setAfDCVerGd("");
            }
            objESaveSOPInfoReqBD00SpecInfo.setDCVerRd(SpecInfos.getAfDCVerRd());


            //查询工艺文件所有信息
            List<SOPDlInfo> objEFileGpInfos = sopDlInfoMapper.SelectAllSOPDlInfo();

            if (objEFileGpInfos == null) {
                throw new SystemException("MG_MES4001411010001F", "查询工艺文件信息为空！");
            }


            //新增工序工艺文件信息
            sopDlInfo.setGuid(CommonUtils.getRandomNumber());
            sopDlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            sopDlInfo.setSOPGd(sopInfo.getGuid());
            sopDlInfo.setSpecVerGd(objESpecVerInfo.getGuid());
            sopDlInfo.setSpecName(objESpecVerInfo.getSpecName());
            sopDlInfo.setCreator(CommonUtils.readUser().getRealName());
            sopDlInfo.setCreateTime(new Date());
            sopDlInfo.setRemark(busData02.getRemark());

            SpecInfoss.add(objESaveSOPInfoReqBD00SpecInfo);

            if (sopDlInfoMapper.InsertSOPDlInfo(sopDlInfo) <= 0) {
                throw new SystemException("MG_MES4001413010000F", "新增工艺工序明细信息失败");
            }

            i++;
        }

        busData02.setSpecInfo(SpecInfoss);


        // 保存
        int count = SOPDAO.InsertSOPInfo(sopInfo);

        if (count <= 0) throw new SystemException("MG_MES4001413010000F", "新增工艺文件信息失败！");



        //查询物料版本信息
      /*
             SOPDlInfo sopDlInfo = new SOPDlInfo();
       MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(busData02.getMaVerRd());

        if (objEMaVerInfo == null) {
            throw new SystemException("", "查询物料版本信息为空！");
        }

        //查询工艺文件信息
        SOPInfo objeSOPInfo = new SOPInfo();
        objeSOPInfo.setMaVerGd(objEMaVerInfo.getGuid());
        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData02.getWFVerRd());
        WFInfo wfInfo = new WFInfo();
        if (objWFVerInfo != null) {
            wfInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
            if (wfInfo == null) {
                throw new SystemException("", "查询工艺信息为空！");
            }
        }
        objeSOPInfo.setWFVerGd(wfInfo.getVerGd());
        objeSOPInfo = SOPDAO.SelectBymaVerGdAndWfGd(objeSOPInfo);

        if (objeSOPInfo != null) {
            //查询工序工艺文件信息
            List<SOPDlInfo> objeSOPDlInfo = sopDlInfoMapper.SelectByguid(objeSOPInfo.getGuid());

            if (objeSOPDlInfo != null || objeSOPDlInfo.size() > 0) {
                //循环删除工序工艺文件
                for (SOPDlInfo obj0 : objeSOPDlInfo) {
                    sopDlInfoMapper.DeleteSOPDlInfo(obj0.getRuid());
                }
            }

            List<SaveSOPInfoReqBD02SpecInfo> SpecInfoss = new ArrayList<SaveSOPInfoReqBD02SpecInfo>();

            for (SaveSOPInfoReqBD02SpecInfo SpecInfos : busData02.getSpecInfo()) {
                SaveSOPInfoReqBD02SpecInfo objESaveSOPInfoReqBD02SpecInfo = new SaveSOPInfoReqBD02SpecInfo();

                //查询工序版本信息
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(SpecInfos.getSpecVerRd());

                if (objESpecVerInfo == null) {
                    throw new SystemException("MG_MES4001810010002F", "查询工序版本信息为空！");
                }

                objESaveSOPInfoReqBD02SpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());

                //查询文件组信息
                FileGpInfo objEfileGpInfo = fileGpDAO.SelectByruid(SpecInfos.getFileGrRd());
                objESaveSOPInfoReqBD02SpecInfo.setFileGrRd(SpecInfos.getFileGrRd());

                //查询设备组信息
                DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(SpecInfos.getDevGpRd());
                objESaveSOPInfoReqBD02SpecInfo.setDevGpRd(SpecInfos.getDevGpRd());

                //查询数据采集信息
                DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getDCVerRd());
                objESaveSOPInfoReqBD02SpecInfo.setDCVerRd(SpecInfos.getDCVerRd());

                SpecInfoss.add(objESaveSOPInfoReqBD02SpecInfo);

                //新增工序工艺版本信息
                sopDlInfo.setGuid(CommonUtils.getRandomNumber());
                sopDlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                sopDlInfo.setSOPGd(objeSOPInfo.getGuid());
                sopDlInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                sopDlInfo.setCreator(CommonUtils.readUser().getRealName());
                sopDlInfo.setCreateTime(new Date());
                sopDlInfo.setRemark(busData02.getRemark());
                sopDlInfo.setSpecName(objESpecVerInfo.getSpecName());
                if (objEfileGpInfo != null) {
                    sopDlInfo.setFileGrGd(objEfileGpInfo.getGuid());
                } else {
                    sopDlInfo.setFileGrGd("");
                }
                if (objEDevGpInfo != null) {
                    sopDlInfo.setDevGrGd(objEDevGpInfo.getGuid());
                } else {
                    sopDlInfo.setDevGrGd("");
                }
                if (objEDCVerInfo != null) {
                    sopDlInfo.setDCVerGd(objEDCVerInfo.getGuid());
                } else {
                    sopDlInfo.setDCVerGd("");
                }
                //新增工序工艺文件信息
                busData02.setSpecInfo(SpecInfoss);
                //新增工序工艺信息
                sopDlInfoMapper.InsertSOPDlInfo(sopDlInfo);
            }

        } else {
            String guids = CommonUtils.getRandomNumber();

            //查询工艺流程
            WFVerInfo objWFVerInfo01 = wfVerDAO.SelectByRuid(busData02.getWFVerRd());
            WFInfo objEWFInfo = new WFInfo();
            if (objWFVerInfo01 != null) {
                objEWFInfo = wfdao.SelectByGuid(objWFVerInfo01.getWfGd());
            }

            if (objEWFInfo == null) {
                throw new SystemException("", "工艺流程信息不存在");
            }

            sopInfo.setGuid(guids);
            sopInfo.setMaVerGd(objEMaVerInfo.getGuid());
            sopInfo.setWFVerGd(objEWFInfo.getVerGd());//工艺流程明细标识
            sopInfo.setCreator(CommonUtils.readUser().getRealName());
            sopInfo.setCreateTime(new Date());
            sopInfo.setRemark(busData02.getRemark());

            SOPDAO.InsertSOPInfo(sopInfo);

            List<SaveSOPInfoReqBD02SpecInfo> SpecInfoss = new ArrayList<SaveSOPInfoReqBD02SpecInfo>();

            for (SaveSOPInfoReqBD02SpecInfo SpecInfos : busData02.getSpecInfo()) {
                SaveSOPInfoReqBD02SpecInfo objESaveSOPInfoReqBD02SpecInfo = new SaveSOPInfoReqBD02SpecInfo();

                //查询工序版本信息
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(SpecInfos.getSpecVerRd());

                if (objESpecVerInfo == null) {
                    throw new SystemException("MG_MES4001810010002F", "查询工序版本信息为空！");
                }

                objESaveSOPInfoReqBD02SpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());

                //查询文件组信息
                FileGpInfo objEfileGpInfo = fileGpDAO.SelectByruid(SpecInfos.getFileGrRd());
                objESaveSOPInfoReqBD02SpecInfo.setFileGrRd(SpecInfos.getFileGrRd());

                //查询设备组信息
                DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(SpecInfos.getDevGpRd());
                objESaveSOPInfoReqBD02SpecInfo.setDevGpRd(SpecInfos.getDevGpRd());

                //查询数据采集信息
                DCVerInfo objEDCVerInfo = dcVerDAO.selectDCVerInfoByRuid(SpecInfos.getDCVerRd());
                objESaveSOPInfoReqBD02SpecInfo.setDCVerRd(SpecInfos.getDCVerRd());

                SpecInfoss.add(objESaveSOPInfoReqBD02SpecInfo);

                //新增工序工艺版本信息
                sopDlInfo.setGuid(CommonUtils.getRandomNumber());
                sopDlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                sopDlInfo.setSOPGd(guids);
                sopDlInfo.setSpecVerGd(objESpecVerInfo.getGuid());
                sopDlInfo.setCreator(CommonUtils.readUser().getRealName());
                sopDlInfo.setCreateTime(new Date());
                sopDlInfo.setRemark(busData02.getRemark());
                sopDlInfo.setSpecName(objESpecVerInfo.getSpecName());
                if (objEfileGpInfo != null) {
                    sopDlInfo.setFileGrGd(objEfileGpInfo.getGuid());
                } else {
                    sopDlInfo.setFileGrGd("");
                }
                if (objEDevGpInfo != null) {
                    sopDlInfo.setDevGrGd(objEDevGpInfo.getGuid());
                } else {
                    sopDlInfo.setDevGrGd("");
                }
                if (objEDCVerInfo != null) {
                    sopDlInfo.setDCVerGd(objEDCVerInfo.getGuid());
                } else {
                    sopDlInfo.setDCVerGd("");
                }
                //新增工序工艺文件信息
                busData02.setSpecInfo(SpecInfoss);
                //新增工序工艺信息
                sopDlInfoMapper.InsertSOPDlInfo(sopDlInfo);
            }
        }
*/
        body.setData(data);
        saveSOPInfoRes.setBody(body);

        return saveSOPInfoRes;
    }

    //删除工艺文件信息
    public SaveSOPInfoRes RmdeleteSOPInfo(SaveSOPInfoReqBD01 busData01) throws SystemException {
        SaveSOPInfoRes saveSOPInfoRes = new SaveSOPInfoRes();

        SaveSOPInfoResB body = new SaveSOPInfoResB();

        SaveSOPInfoResD data = new SaveSOPInfoResD();

        //查询物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(busData01.getMaVerRd());

        if (objEMaVerInfo == null) {
            throw new SystemException("", "查询物料版本信息为空！");
        }

        //查询工艺文件信息
        SOPInfo objeSOPInfo = new SOPInfo();
        objeSOPInfo.setMaVerGd(objEMaVerInfo.getGuid());
        WFVerInfo objWFVerInfo = wfVerDAO.SelectByRuid(busData01.getWFVerRd());
        WFInfo wfInfo = new WFInfo();
        if (objWFVerInfo != null) {
            wfInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
            if (wfInfo == null) {
                throw new SystemException("", "查询工艺信息为空！");
            }
        }
        objeSOPInfo.setWFVerGd(wfInfo.getVerGd());
        objeSOPInfo = SOPDAO.SelectBymaVerGdAndWfGd(objeSOPInfo);


        if (objeSOPInfo == null) {
            throw new SystemException("MG_MES4001411010001F", "查询工艺文件信息为空！");
        }

        //查询工序工艺文件信息
        List<SOPDlInfo> objeSOPDlInfo = sopDlInfoMapper.SelectByguid(objeSOPInfo.getGuid());

        if (objeSOPDlInfo == null || objeSOPDlInfo.size() <= 0) {
            throw new SystemException("MG_MES4001412010001F", "查询工序工艺文件信息为空！");
        }

        //循环删除工序工艺文件
        for (SOPDlInfo obj0 : objeSOPDlInfo) {
            sopDlInfoMapper.DeleteSOPDlInfo(obj0.getRuid());
        }
        SOPDAO.DeleteSOPInfo(objeSOPInfo.getRuid());

        body.setData(data);
        saveSOPInfoRes.setBody(body);

        return saveSOPInfoRes;
    }

}
