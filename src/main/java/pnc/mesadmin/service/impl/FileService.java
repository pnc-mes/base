package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.FileDAO;
import pnc.mesadmin.dao.FileVerDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoRes;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoResB;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoResD;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoResB;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoBD00;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoRes;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoResD;
import pnc.mesadmin.dto.GetFileVInfo.*;
import pnc.mesadmin.dto.SaveFileInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.FileIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.FastfdsUtils;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class FileService implements FileIService {

    @Resource
    private FileDAO fileInfoMapper;

    @Resource
    private FileVerDAO fileVerInfoMapper;

    @Resource
    private BaseIService baseIService;

    //查询文件列表信息
    public GetAllFileInfoRes QALLselectAllFileInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllFileInfoRes objEGetAllFileInfoRes = new GetAllFileInfoRes();

        GetAllFileInfoResB body = new GetAllFileInfoResB();

        List<GetAllFileInfoResD> dataList =  new ArrayList<GetAllFileInfoResD>();

        // 获取文件信息列表
        List<FileInfo> fileInfoList = baseIService.QALLBaseInfo(FileDAO.class, "SelectAllFileInfo",
                argInitDataFields, argPageInfo);
        if(fileInfoList!=null || fileInfoList.size()>0) {
            GetAllFileInfoResD data = null;

            // 循环赋值
            for (int i = 0; i < fileInfoList.size(); i++) {

                data = new GetAllFileInfoResD();
                data.setFileRd(fileInfoList.get(i).getRuid());
                data.setFileName(fileInfoList.get(i).getFileName());

                //查询文件版本信息
                List<FileVerInfo> fileVerInfoList = fileVerInfoMapper.SelectByVerGd(fileInfoList.get(i).getVerGd());

                if(fileVerInfoList!=null || fileVerInfoList.size()>0) {
                    //遍历查询文件版本信息
                    for (FileVerInfo fileVerInfo : fileVerInfoList) {
                        data.setFileVerRd(fileVerInfo.getRuid());
                        data.setVersion(fileVerInfo.getVersion());
                    }
                }
                dataList.add(data);

            }
        }

        body.setData(dataList);
        objEGetAllFileInfoRes.setBody(body);

        return objEGetAllFileInfoRes;
    }

    //查询文件版本列表信息
    public GetFileTreeInfoRes GetselectfileVerRd(GetFileTreeInfoBD00 argGetFileTreeInfoBD00) throws SystemException {
        GetFileTreeInfoRes objEGetFileTreeInfoRes=new GetFileTreeInfoRes();
        GetFileTreeInfoResB objEGetFileTreeInfoResB=new GetFileTreeInfoResB();
        List<GetFileTreeInfoResD> objEGetFileTreeInfoResDs=new ArrayList<GetFileTreeInfoResD>();

        FileInfo objFileInfo=fileInfoMapper.SelectByfileRd(argGetFileTreeInfoBD00.getFileRd());
        if(objFileInfo!=null) {
            List<FileVerInfo> objEFileVerInfo = fileVerInfoMapper.SelectFileVerInfoByfileGd(objFileInfo.getGuid());
            if (objEFileVerInfo != null || objEFileVerInfo.size() > 0) {

                for (FileVerInfo obj : objEFileVerInfo) {
                    GetFileTreeInfoResD objEGetFileTreeInfoResD = new GetFileTreeInfoResD();
                    objEGetFileTreeInfoResD.setFileVerRd(obj.getRuid());
                    objEGetFileTreeInfoResD.setVersion(obj.getVersion());
                    objEGetFileTreeInfoResDs.add(objEGetFileTreeInfoResD);
                }
            }
        }
        objEGetFileTreeInfoResB.setData(objEGetFileTreeInfoResDs);
        objEGetFileTreeInfoRes.setBody(objEGetFileTreeInfoResB);
        return objEGetFileTreeInfoRes;
    }

    //查询文件版本信息00
    public GetFileVInfoRes GetselectByfileRd(GetFileVInfoReqBD00 argGetFileVInfoReqBD00) throws SystemException {
        GetFileVInfoRes objEGetFileVInfoRes = new GetFileVInfoRes();

        GetFileVInfoResB objEGetFileVInfoResB = new GetFileVInfoResB();

        // 获取文件信息
        FileInfo objEfileInfo = fileInfoMapper.SelectByfileRd(argGetFileVInfoReqBD00.getFileRd());

        if(objEfileInfo!=null) {
            //查询文件版本信息
            List<FileVerInfo> objEFileVerInfos = fileVerInfoMapper.SelectFileVerInfoByfileGd(objEfileInfo.getGuid());
            if (objEFileVerInfos != null || objEFileVerInfos.size() > 0) {
                for (FileVerInfo obj : objEFileVerInfos) {
                    if (!"01".equals(obj.getIsDef())) {
                        GetFileVInfoResD objEGetFileVInfoResD = new GetFileVInfoResD();

                        String strIsDef = obj.getIsDef();

                        if ("00".equals(strIsDef)) {
                            strIsDef = "00";
                        } else {
                            strIsDef = "01";
                        }

                        //赋值查询文件版本信息
                        objEGetFileVInfoResD.setFileRd(objEfileInfo.getRuid());
                        objEGetFileVInfoResD.setFileVerRd(obj.getRuid());
                        objEGetFileVInfoResD.setFileName(obj.getFileName());
                        objEGetFileVInfoResD.setVersion(obj.getVersion());
                        objEGetFileVInfoResD.setIsDef(strIsDef);
                        objEGetFileVInfoResD.setFilePath(FastfdsUtils.readProps() + obj.getFilePath());
                        objEGetFileVInfoResD.setCreator(obj.getCreator());
                        objEGetFileVInfoResD.setCreateTime(DateUtil.format(obj.getCreateTime()));
                        objEGetFileVInfoResD.setLastModifyMan(obj.getLastModifyMan());
                        objEGetFileVInfoResD.setLastModifyTime(DateUtil.format(obj.getLastModifyTime()));
                        objEGetFileVInfoResD.setRemark(obj.getRemark());
                        objEGetFileVInfoResD.setStatus(obj.getStatus());
                        objEGetFileVInfoResB.setData(objEGetFileVInfoResD);
                    }
                    objEGetFileVInfoRes.setBody(objEGetFileVInfoResB);
                }
            }
        }
        return objEGetFileVInfoRes;
    }

    //查询文件版本信息01
    public GetFileVInfoRes GetselectByfileVerRd(GetFileVInfoReqBD01 argGetFileVInfoReqBD01) throws SystemException {
        GetFileVInfoRes objEGetFileVInfoRes = new  GetFileVInfoRes();

        GetFileVInfoResB body = new GetFileVInfoResB();

        // 获取文件版本信息
        FileVerInfo fileVerInfo = fileVerInfoMapper.SelectByfileVerRd(argGetFileVInfoReqBD01.getFileVerRd());

        if(fileVerInfo != null) {
            //查询文件信息
            FileInfo fileInfo = fileInfoMapper.SelectByguid(fileVerInfo.getFileGd());

            GetFileVInfoResD objEGetFileVInfoResD = new GetFileVInfoResD();

            String strIsDef = fileVerInfo.getIsDef();

            //判断是否是默认版本
            if ("00".equals(strIsDef)) {
                strIsDef = "00";
            } else {
                strIsDef = "01";
            }
            if(fileInfo!=null) {
                //查询文件版本信息
                objEGetFileVInfoResD.setFileRd(fileInfo.getRuid());
            }
            objEGetFileVInfoResD.setFileVerRd(fileVerInfo.getRuid());
            objEGetFileVInfoResD.setFileName(fileVerInfo.getFileName());
            objEGetFileVInfoResD.setVersion(fileVerInfo.getVersion());
            objEGetFileVInfoResD.setIsDef(strIsDef);
            objEGetFileVInfoResD.setFilePath(fileVerInfo.getFilePath());
            objEGetFileVInfoResD.setCreator(fileVerInfo.getCreator());
            objEGetFileVInfoResD.setCreateTime(DateUtil.format(fileVerInfo.getCreateTime()));
            objEGetFileVInfoResD.setLastModifyMan(fileVerInfo.getLastModifyMan());
            objEGetFileVInfoResD.setLastModifyTime(DateUtil.format(fileVerInfo.getLastModifyTime()));
            objEGetFileVInfoResD.setRemark(fileVerInfo.getRemark());
            objEGetFileVInfoResD.setStatus(fileVerInfo.getStatus());
            body.setData(objEGetFileVInfoResD);
        }

        objEGetFileVInfoRes.setBody(body);
        return objEGetFileVInfoRes;
    }

    //保存文件信息
    public SaveFileInfoRes AddinsertFileInfo(HttpServletRequest request,SaveFileInfoReqBD00 busData00, FileInfo fileInfo) throws SystemException ,IOException {
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        FileVerInfo  fileVerInfo=new FileVerInfo();

        fileVerInfo.setGuid(CommonUtils.getRandomNumber());

        //查询文件所有信息
        List<FileInfo> objEFileInfos=fileInfoMapper.SelectAllFileInfo();

        // 赋值新增文件信息
        fileInfo.setGuid(CommonUtils.getRandomNumber());
        fileInfo.setVerGd(fileVerInfo.getGuid());

        //逻辑校验保存的文件名称不能相同
        for (FileInfo obj:objEFileInfos){
            if (obj.getFileName().equals(busData00.getFileName())){
                throw new SystemException("MG0006F","文件名称已存在");
            }
        }

        fileInfo.setFileName(busData00.getFileName());
        fileInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        fileInfo.setCreateTime(new Date());
        fileInfo.setRemark(busData00.getRemark());

        if(fileInfoMapper.InsertFileInfo(fileInfo)<=0){
            throw new SystemException("MG_MES4001312010000F","新增文件信息失败！");
        }

        //查询文件版本所有信息
        List<FileVerInfo> objEFileVerInfos=fileVerInfoMapper.SelectAllfileInfo();

        if (objEFileVerInfos==null){
            throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空！");
        }

        //生成默认文件版本
        fileVerInfo.setFileGd(fileInfo.getGuid());

        //逻辑校验保存的文件版本名称不能相同
        for (FileVerInfo obj:objEFileVerInfos){
            if (obj.getFileName().equals(busData00.getFileName())){
                throw new SystemException("MG0006F","文件名称已存在");
            }
        }

        //校验上传文件类型-pdf
        Map<String, String> path = FastfdsUtils.upload(request, "pdf");

        fileVerInfo.setFileName(busData00.getFileName());
        fileVerInfo.setVersion(busData00.getVersion());
        fileVerInfo.setIsDef("00");
        fileVerInfo.setFilePath(path.get(busData00.getFilePath()));
        fileVerInfo.setStatus(busData00.getStatus());
        fileVerInfo.setCreator(CommonUtils.readUser().getRealName());
        fileVerInfo.setCreateTime(new Date());
        fileVerInfo.setRemark(busData00.getRemark());

        // 保存
        fileVerInfoMapper.InsertFileVerInfo(fileVerInfo);

        return saveFileInfoRes;
    }

    //更新文件信息
    public SaveFileInfoRes ModupdateFileInfo(HttpServletRequest request,SaveFileInfoReqBD02 busData02, FileInfo fileInfo) throws SystemException ,IOException{
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB body = new SaveFileInfoResB();

        SaveFileInfoResD data = new SaveFileInfoResD();


        FileInfo objEfileInfo = fileInfoMapper.SelectByfileRd(busData02.getFileRd());

        // 判断返回文件是否为空
        if (objEfileInfo == null) {
            throw new SystemException("MG_MES4001212010001F", "查询文件信息为空！");
        }

        //查询文件名称
        FileInfo objEFileInfos=fileInfoMapper.SelectfileName(busData02.getFileName());

        if(objEFileInfos!=null && !objEFileInfos.getFileName().equals(objEfileInfo.getFileName())){
            throw new SystemException("MG0006F","更新失败，文件名称已存在");
        }

        // 赋值更新文件信息
        fileInfo.setRuid(busData02.getFileRd());

        if ((busData02.getFileName()).equals("")){
            throw new SystemException("MG0003F","文件名称不能为空");
        }

        fileInfo.setFileName(busData02.getFileName());

        //查询文件版本所有信息
        FileVerInfo objEFileVerInfos=fileVerInfoMapper.SelectByfileVerRd(busData02.getFileVerRd());

        if (objEFileVerInfos==null){
            throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
        }

        FileVerInfo objeFileVerInfo=fileVerInfoMapper.SelectVersion(objEFileVerInfos.getFileGd(),busData02.getVersion());

        if(objeFileVerInfo!=null && !objeFileVerInfo.getVersion().equals(objEFileVerInfos.getVersion())){
            throw new SystemException("MG0006F","更新失败，版本已存在");
        }


        if ((busData02.getFileName()).equals("")){
            throw new SystemException("MG0003F","文件名称不能为空");
        }

        //上传文件
        Set<String> stringSet = new HashSet<String>();
        stringSet.add(objEFileVerInfos.getFilePath());

        Map<String, String> path = FastfdsUtils.modify(request, stringSet, "pdf");//FastfdsUtils.upload(request, fileName.substring(fileName.lastIndexOf("/")));


        objEFileVerInfos.setFileGd(objEfileInfo.getGuid());
        objEFileVerInfos.setFileName(busData02.getFileName());
        objEFileVerInfos.setRuid(busData02.getFileVerRd());
        objEFileVerInfos.setVersion(busData02.getVersion());
        objEFileVerInfos.setIsDef(busData02.getIsDef());
        if(!"".equals(path)) {
            objEFileVerInfos.setFilePath(path.get(busData02.getFilePath()));
        }
        objEFileVerInfos.setStatus(busData02.getStatus());
        objEFileVerInfos.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEFileVerInfos.setLastModifyTime(new Date());
        objEFileVerInfos.setRemark(busData02.getRemark());
        fileInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        fileInfo.setLastModifyTime(new Date());
        fileInfo.setRemark(busData02.getRemark());

        // 更新
        int count = fileInfoMapper.UpdateFileInfo(fileInfo);

        if(count <=0) throw new SystemException("MG_MES4001312030002F","更新文件信息失败！");

        if(fileVerInfoMapper.UpdateFileVerInfo(objEFileVerInfos)<=0){
            throw new SystemException("MG_MES4001312030002F","更新文件默认版本信息失败！");
        }

        body.setData(data);
        saveFileInfoRes.setBody(body);

        return saveFileInfoRes;
    }

    //复制文件信息
    public SaveFileInfoRes copyFileInfo(SaveFileInfoReqBD03 busData03, FileInfo fileInfo) throws SystemException {
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB body = new SaveFileInfoResB();

        SaveFileInfoResD data = new SaveFileInfoResD();

        String guid = CommonUtils.getRandomNumber();
        String guid1 = CommonUtils.getRandomNumber();
        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        //查询文件信息
        FileInfo objEfileInfo =fileInfoMapper.SelectByfileRd(busData03.getFileRd());

        FileVerInfo objEFileVerInfo = fileVerInfoMapper.SelectByFileVerGd(objEfileInfo.getVerGd());

        objEfileInfo.setGuid(guid);
        objEfileInfo.setVerGd(guid1);
        objEfileInfo.setLastModifyTime(null);
        objEfileInfo.setLastModifyMan(null);
        objEfileInfo.setCreateTime(date);
        objEfileInfo.setCreator(userName);

        if(fileInfoMapper.InsertFileInfo(objEfileInfo)<=0){
            throw new SystemException("MG_MES4001312040003F","复制文件信息失败");
        }

        FileInfo objeFileInfo=fileInfoMapper.SelectByguid(guid);
        objeFileInfo.setFileName(objeFileInfo.getFileName()+objeFileInfo.getRuid());
        if(fileInfoMapper.UpdateFileInfo(objeFileInfo)<=0){
            throw new SystemException("MG_MES4001312040003F","复制文件信息失败");
        }

        objEFileVerInfo.setGuid(guid1);
        objEFileVerInfo.setFileName(objeFileInfo.getFileName());
        objEFileVerInfo.setFileGd(guid);
        objEFileVerInfo.setCreator(CommonUtils.readUser().getRealName());
        objEFileVerInfo.setCreateTime(date);
        objEFileVerInfo.setLastModifyMan(null);
        objEFileVerInfo.setLastModifyTime(null);
        fileVerInfoMapper.InsertFileVerInfo(objEFileVerInfo);

        body.setData(data);
        saveFileInfoRes.setBody(body);

        return saveFileInfoRes;
    }

    //新增文件版本信息
    public SaveFileInfoRes AddinsertFileVerInfo(HttpServletRequest request,SaveFileInfoReqBD04 busData04, FileVerInfo fileVerInfo) throws SystemException ,IOException{
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB body = new SaveFileInfoResB();

        SaveFileInfoResD data = new SaveFileInfoResD();

        // 查询文件信息
        FileInfo fileInfo=fileInfoMapper.SelectByfileRd(busData04.getFileRd());

        if (fileInfo==null){
            throw new SystemException("MG_MES4001212010001F","查询文件信息为空");
        }

        //查询文件名称
        FileInfo objEFileInfos=fileInfoMapper.SelectfileName(busData04.getFileName());

        if(objEFileInfos!=null && !objEFileInfos.getFileName().equals(fileInfo.getFileName())){
            throw new SystemException("MG0006F","新增失败，文件名称已存在");
        }

        String fileVerInfoguid=CommonUtils.getRandomNumber();

        //赋值新增文件
        fileInfo.setFileName(busData04.getFileName());
        fileInfo.setVerGd(fileVerInfoguid);
        fileInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        fileInfo.setLastModifyTime(new Date());
        fileInfo.setRemark(busData04.getRemark());
        if(fileInfoMapper.UpdateFileInfo(fileInfo) <= 0){
            throw new SystemException("MG_MES4001312030002F", "新增失败");
        }

        FileVerInfo objeFileVerInfo=fileVerInfoMapper.SelectVersion(fileInfo.getGuid(),busData04.getVersion());

        if(objeFileVerInfo!=null && !objeFileVerInfo.getGuid().equals(fileInfo.getVerGd())){
            throw new SystemException("MG0006F","新增失败，版本已存在");
        }

        if ((busData04.getFileName()).equals("")){
            throw new SystemException("MG0003F","文件名称不能为空");
        }

        if ((busData04.getVersion()).equals("")){
            throw new SystemException("MG0003F","版本不能为空");
        }

        //赋值新增文件版本
        fileVerInfo.setGuid(fileVerInfoguid);
        fileVerInfo.setFileGd(fileInfo.getGuid());
        fileVerInfo.setFileName(busData04.getFileName());
        fileVerInfo.setVersion(busData04.getVersion());

        //判断新增文件版本是否是默认版本
        if ("00".equals(busData04.getIsDef())){
            fileVerInfo.setIsDef("00");//默认版本

            //查询文件版本信息
            List<FileVerInfo> objEFileVerInfos=fileVerInfoMapper.SelectFileVerInfofileGd(fileInfo.getGuid());

            if (objEFileVerInfos==null){
                throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
            }

            //循环更新文件版本
            for (FileVerInfo obj:objEFileVerInfos){
                obj.setIsDef("01");

                if(fileVerInfoMapper.UpdateFileVerInfo(obj)<=0){
                    throw new SystemException("MG_MES4001312030002F","更新文件版本的默认版本信息失败");
                }

                FileInfo objFileInfo=fileInfoMapper.SelectByguid(obj.getFileGd());

                if (objFileInfo==null){
                    throw new SystemException("MG_MES4001212010001F","查询文件信息为空");
                }

                objFileInfo.setFileName(busData04.getFileName());
                objFileInfo.setRemark(busData04.getRemark());

                if(fileInfoMapper.UpdateFileInfo(objFileInfo)<=0){
                    throw new SystemException("MG_MES4001312030002F","更新文件的默认名称信息失败");
                }
            }
        }

        //校验上传文件类型-pdf
        Map<String, String> path = FastfdsUtils.upload(request, "pdf");

        //赋值新增文件版本信息
        fileVerInfo.setIsDef(busData04.getIsDef());
        fileVerInfo.setFilePath(path.get(busData04.getFilePath()));
        fileVerInfo.setStatus(busData04.getStatus());
        fileVerInfo.setCreator(CommonUtils.readUser().getRealName());
        fileVerInfo.setCreateTime(new Date());
        fileVerInfo.setRemark(busData04.getRemark());

        // 保存
        int count = fileVerInfoMapper.InsertFileVerInfo(fileVerInfo);
        if(count <=0) throw new SystemException("MG_MES4001312050004F","新增文件版本信息失败！");

        body.setData(data);
        saveFileInfoRes.setBody(body);

        return saveFileInfoRes;
    }

    //删除文件版本信息
    public SaveFileInfoRes RmdeleteFileVerInfo(SaveFileInfoReqBD05 busData05) throws SystemException {
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB body = new SaveFileInfoResB();

        SaveFileInfoResD data = new SaveFileInfoResD();

        //查询文件版本信息
        FileVerInfo objEFileVerInfo=fileVerInfoMapper.SelectByfileVerRd(busData05.getFileVerRd());

        if (objEFileVerInfo==null){
            throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
        }

        //逻辑判断是否是默认版本
        if("00".equals(objEFileVerInfo.getIsDef())){

            List<FileVerInfo> objEFileVerInfos=fileVerInfoMapper.SelectFileVerInfofileGd(objEFileVerInfo.getFileGd());

            if (objEFileVerInfos==null){
                throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
            }

            //循环删除文件版本
            for(FileVerInfo obj:objEFileVerInfos){
                if(fileVerInfoMapper.DeleteFileVerInfo(obj.getFileGd(),obj.getGuid())<=0){
                    throw new SystemException("MG_MES4001312060005F","删除文件版本全部信息失败");
                }
                //删除文件
                FastfdsUtils.delete(obj.getFilePath());
            }

            FileInfo objEFileInfo=fileInfoMapper.SelectByguid(objEFileVerInfo.getFileGd());

            if (objEFileInfo==null){
                throw new SystemException("MG_MES4001212010001F","查询文件信息为空");
            }

            //删除文件信息
            if(fileInfoMapper.DeleteFileInfo(objEFileInfo.getRuid())<=0){
                throw new SystemException("MG_MES4001312020001F","删除文件信息失败");
            }
        }
        else{
            if(fileVerInfoMapper.DeleteFileVerInforuid(busData05.getFileVerRd())<=0){
                throw new SystemException("MG_MES4001312060005F","删除文件版本信息失败");
            }
        }

        //删除文件
        FastfdsUtils.delete(objEFileVerInfo.getFilePath());

        body.setData(data);
        saveFileInfoRes.setBody(body);

        return saveFileInfoRes;
    }

    //删除文件信息
    public SaveFileInfoRes RmdeleteFileInfo(SaveFileInfoReqBD01 busData01) throws SystemException {
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB body = new SaveFileInfoResB();

        SaveFileInfoResD data = new SaveFileInfoResD();


        //根据传过来的文件id查询文件信息并获取文件版本标识
        FileInfo objEFileInfo=fileInfoMapper.SelectByfileRd(busData01.getFileRd());

        if (objEFileInfo==null){
            throw new SystemException("MG_MES4001310010001F","查询文件信息为空");
        }

        String strGuid=objEFileInfo.getGuid();

        //根据标识查询文件版本信息
        List<FileVerInfo> objEFileVerInfos=fileVerInfoMapper.SelectFileVerInfofileGd(strGuid);

        if (objEFileVerInfos==null){
            throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
        }

        //循环删除文件版本
        for(FileVerInfo obj:objEFileVerInfos){
            if(fileVerInfoMapper.DeleteFileVerInfo(obj.getFileGd(),obj.getGuid())<=0){
                throw new SystemException("MG_MES4001312060005F","删除文件信息所有的版本失败");
            }
            //删除文件
            FastfdsUtils.delete(obj.getFilePath());
        }

        if(fileInfoMapper.DeleteFileInfo(busData01.getFileRd())<=0){
            throw new SystemException("MG_MES4001312020001F","删除文件信息失败");
        }


        body.setData(data);
        saveFileInfoRes.setBody(body);

        return saveFileInfoRes;
    }

    /**
     * 获取文件列表信息(新)
     * @param request
     * @return
     */
    @Override
    public PageResult<GetAllFileInfoResD> QALLFileInfoNew(BaseRequest request) {
        List<GetAllFileInfoResD> resDList =  new ArrayList<GetAllFileInfoResD>();
        GetAllFileInfoResD resD = null;
        FileVerInfo fileVerInfo = null;

        IPage<FileInfo> iPage = fileInfoMapper.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        for(FileInfo fileInfo : iPage.getRecords()){
            resD = new GetAllFileInfoResD();
            resD.setFileRd(fileInfo.getRuid());
            resD.setFileName(fileInfo.getFileName());

            //查询文件版本信息
            fileVerInfo = fileVerInfoMapper.SelectByFileVerGd(fileInfo.getVerGd());
            if(fileVerInfo != null) {
                resD.setFileVerRd(fileVerInfo.getRuid());
                resD.setVersion(fileVerInfo.getVersion());
            }
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    /**
     * 获取所有文件版本列表信息(新)
     * @param request
     * @return
     */
    @Override
    public PageResult<GetAllFileInfoResD> QALLFileVerListNew(BaseRequest request) {
        List<GetAllFileInfoResD> resDList =  new ArrayList<GetAllFileInfoResD>();
        GetAllFileInfoResD resD = null;
        List<FileVerInfo> fileVerInfos = null;

        IPage<FileInfo> iPage = fileInfoMapper.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        for(FileInfo fileInfo : iPage.getRecords()){
            //查询文件版本信息
            fileVerInfos = fileVerInfoMapper.SelectFileVerInfofileGd(fileInfo.getGuid());
            for(FileVerInfo o : fileVerInfos) {
                resD = new GetAllFileInfoResD();
                resD.setFileRd(fileInfo.getRuid());
                resD.setFileName(fileInfo.getFileName());
                resD.setFileVerRd(o.getRuid());
                resD.setVersion(o.getVersion());
                resDList.add(resD);
            }
        }

        return new PageResult<>(iPage, resDList);
    }

    /**
     * 获取文件版本列表信息(新)
     * @param request
     * @return
     */
    @Override
    public PageResult<GetFileVInfoResD> QALLFileVerInfoNew(BaseRequest<GetFileVInfoReqBD00> request) {
        GetFileVInfoReqBD00 req = request.getData();

        // 获取文件信息
        FileInfo fileInfo = fileInfoMapper.SelectByfileRd(req.getFileRd());

        if(fileInfo != null) {
            List<GetFileVInfoResD> resDList = new ArrayList<>();
            GetFileVInfoResD resD = null;

            //查询文件版本信息
            Page<FileVerInfo> page = fileVerInfoMapper.selectPage(new MyPage(request), new LambdaQueryWrapper<FileVerInfo>()
                    .eq(FileVerInfo::getFileGd, fileInfo.getGuid()));
            for (FileVerInfo obj : page.getRecords()) {
                //赋值查询文件版本信息
                resD = new GetFileVInfoResD();
                resD.setFileRd(fileInfo.getRuid());
                resD.setFileVerRd(obj.getRuid());
                resD.setFileName(obj.getFileName());
                resD.setVersion(obj.getVersion());
                resD.setIsDef(obj.getIsDef());
                resD.setFilePath(obj.getFilePath());
                resD.setStatus(obj.getStatus());
                resD.setCreator(obj.getCreator());
                resD.setCreateTime(DateUtil.format(obj.getCreateTime()));
                resD.setLastModifyMan(obj.getLastModifyMan());
                resD.setLastModifyTime(DateUtil.format(obj.getLastModifyTime()));
                resD.setRemark(obj.getRemark());

                resDList.add(resD);
            }
            return new PageResult<>(page, resDList);
        }
        return new PageResult<>(0, 0, 0, 0, new ArrayList<>());
    }

    /**
     * 新增文件信息(新)
     * @param request
     */
    @Override
    public void AddFileInfo(BaseRequest<SaveFileInfoReqBD00> request) {
        SaveFileInfoReqBD00 req = request.getData();

        //用户名
        String username = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //文件名称
        String fileName = req.getFileName().trim();
        if(StringUtils.isBlank(fileName)){
            throw new SystemException("", "文件名称不能为空");
        }
        if(StringUtils.isBlank(req.getFilePath())){
            throw new SystemException("", "文件不能为空,请上传");
        }
        if (StringUtils.isBlank(req.getVersion())){
            throw new SystemException("","版本号不能为空");
        }

        //文件版本信息
        FileVerInfo fileVerInfo = new FileVerInfo();
        fileVerInfo.setGuid(CommonUtils.getRandomNumber());

        //判断是否存在相同名称的文件
        FileInfo fileInfo = fileInfoMapper.SelectfileName(fileName);
        if(fileInfo != null){
            throw new SystemException("","文件名称已存在");
        }

        //赋值新增文件信息
        fileInfo = new FileInfo();
        fileInfo.setGuid(CommonUtils.getRandomNumber());
        fileInfo.setVerGd(fileVerInfo.getGuid());
        fileInfo.setFileName(fileName);
        fileInfo.setCreator(username);
        fileInfo.setCreateTime(date);
        fileInfo.setRemark(req.getRemark());

        if(fileInfoMapper.InsertFileInfo(fileInfo) <= 0){
            throw new SystemException("", "新增文件信息失败！");
        }

        //生成默认文件版本
        fileVerInfo.setFileGd(fileInfo.getGuid());
        fileVerInfo.setFileName(fileName);
        fileVerInfo.setVersion(req.getVersion());
        fileVerInfo.setIsDef("00");
        fileVerInfo.setFilePath(req.getFilePath());
        fileVerInfo.setStatus(req.getStatus());
        fileVerInfo.setCreator(username);
        fileVerInfo.setCreateTime(date);
        fileVerInfo.setRemark(req.getRemark());

        if(fileVerInfoMapper.InsertFileVerInfo(fileVerInfo) <= 0){
            throw new SystemException("", "新增文件信息失败！");
        }
    }

    /**
     * 新增文件版本信息(新)
     * @param request
     */
    @Override
    public void AddFileVerInfo(BaseRequest<SaveFileInfoReqBD04> request) {
        SaveFileInfoReqBD04 req = request.getData();

        //用户名
        String username = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        if(StringUtils.isBlank(req.getFilePath())){
            throw new SystemException("", "文件不能为空,请上传");
        }

        if (StringUtils.isBlank(req.getVersion())){
            throw new SystemException("","版本号不能为空");
        }

        //查询文件信息
        FileInfo fileInfo = fileInfoMapper.SelectByfileRd(req.getFileRd());
        if (fileInfo == null){
            throw new SystemException("","文件信息为空");
        }

        String fileVerGd = CommonUtils.getRandomNumber();

        FileVerInfo fileVerInfo = fileVerInfoMapper.SelectVersion(fileInfo.getGuid(),req.getVersion());

        if(fileVerInfo != null){
            throw new SystemException("","新增失败，版本已存在");
        }

        //赋值新增文件版本
        fileVerInfo = new FileVerInfo();
        fileVerInfo.setGuid(fileVerGd);
        fileVerInfo.setFileGd(fileInfo.getGuid());
        fileVerInfo.setFileName(fileInfo.getFileName());
        fileVerInfo.setVersion(req.getVersion());

        //判断新增文件版本是否是默认版本
        if ("00".equals(req.getIsDef())){
            //查询文件版本信息
            List<FileVerInfo> objEFileVerInfos = fileVerInfoMapper.SelectFileVerInfofileGd(fileInfo.getGuid());

            if (objEFileVerInfos == null){
                throw new SystemException("","查询文件版本信息为空");
            }

            //循环更新文件版本
            for (FileVerInfo obj : objEFileVerInfos){
                obj.setIsDef("01");
                obj.setLastModifyMan(username);
                obj.setLastModifyTime(date);

                if(fileVerInfoMapper.UpdateFileVerInfo(obj) <= 0){
                    throw new SystemException("MG_MES4001312030002F", "更新文件版本的默认版本信息失败");
                }
            }

            //赋值新增文件
            fileInfo.setVerGd(fileVerGd);
            fileInfo.setLastModifyMan(username);
            fileInfo.setLastModifyTime(date);
            fileInfo.setRemark(req.getRemark());
            if(fileInfoMapper.updateById(fileInfo) <= 0){
                throw new SystemException("", "新增文件子版本失败");
            }
        }

        //赋值新增文件版本信息
        fileVerInfo.setIsDef(req.getIsDef());
        fileVerInfo.setFilePath(req.getFilePath());
        fileVerInfo.setStatus(req.getStatus());
        fileVerInfo.setCreator(username);
        fileVerInfo.setCreateTime(date);
        fileVerInfo.setRemark(req.getRemark());

        int count = fileVerInfoMapper.InsertFileVerInfo(fileVerInfo);
        if(count <=0) throw new SystemException("MG_MES4001312050004F", "新增文件版本信息失败！");
    }

    /**
     * 修改文件信息(新)
     * @param request
     */
    @Override
    public void ModFileInfo(BaseRequest<SaveFileInfoReqBD02> request) {
        SaveFileInfoReqBD02 req = request.getData();

        //用户名
        String username = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //文件名称
        String fileName = req.getFileName().trim();
        if(StringUtils.isBlank(fileName)){
            throw new SystemException("", "文件名称不能为空");
        }
        if (StringUtils.isBlank(req.getVersion())){
            throw new SystemException("","版本号不能为空");
        }

        //查询文件版本所有信息
        FileVerInfo fileVerInfo = fileVerInfoMapper.SelectByfileVerRd(req.getFileVerRd());
        if (fileVerInfo == null){
            throw new SystemException("","查询文件版本信息为空");
        }

        //查询文件信息
        FileInfo fileInfo = fileInfoMapper.SelectByguid(fileVerInfo.getFileGd());
        if (fileInfo == null) {
            throw new SystemException("", "查询文件信息为空！");
        }

        //判断文件名称是否重复
        FileInfo objEFileInfos = fileInfoMapper.SelectfileName(fileName);
        if(objEFileInfos !=null && !objEFileInfos.getFileName().equals(fileInfo.getFileName())){
            throw new SystemException("","更新失败，文件名称已存在");
        }

        //判断文件版本是否重复
        FileVerInfo objeFileVerInfo = fileVerInfoMapper.SelectVersion(fileVerInfo.getFileGd(), req.getVersion());
        if(objeFileVerInfo!=null && !objeFileVerInfo.getVersion().equals(fileVerInfo.getVersion())){
            throw new SystemException("","更新失败，版本已存在");
        }

        //如果是默认版本则可以更新文件名称
        if("00".equals(req.getIsDef())) {
            //查询文件版本信息
            List<FileVerInfo> objEFileVerInfos = fileVerInfoMapper.SelectFileVerInfofileGd(fileInfo.getGuid());

            if (objEFileVerInfos == null){
                throw new SystemException("","查询文件版本信息为空");
            }

            //循环更新文件版本
            for (FileVerInfo obj : objEFileVerInfos){
                obj.setIsDef("01");
                obj.setLastModifyMan(username);
                obj.setLastModifyTime(date);

                if(fileVerInfoMapper.UpdateFileVerInfo(obj) <= 0){
                    throw new SystemException("MG_MES4001312030002F", "更新文件版本的默认版本信息失败");
                }
            }

            //赋值新增文件
            fileInfo.setFileName(fileName);
            fileInfo.setVerGd(fileVerInfo.getGuid());
            fileInfo.setLastModifyMan(username);
            fileInfo.setLastModifyTime(date);
            fileInfo.setRemark(req.getRemark());
            if(fileInfoMapper.updateById(fileInfo) <= 0){
                throw new SystemException("", "新增文件子版本失败");
            }

            fileVerInfo.setIsDef(req.getIsDef());
        }

        fileVerInfo.setFileGd(fileInfo.getGuid());
        fileVerInfo.setFileName(fileInfo.getFileName());
        fileVerInfo.setVersion(req.getVersion());
        if(StringUtils.isNotBlank(req.getFilePath())) {
            fileVerInfo.setFilePath(req.getFilePath());
        }
        fileVerInfo.setStatus(req.getStatus());
        fileVerInfo.setLastModifyMan(username);
        fileVerInfo.setLastModifyTime(date);
        fileVerInfo.setRemark(req.getRemark());

        if(fileVerInfoMapper.UpdateFileVerInfo(fileVerInfo) <= 0){
            throw new SystemException("MG_MES4001312030002F","更新文件默认版本信息失败！");
        }
    }

    /**
     * 删除文件信息(新)
     * @param request
     */
    @Override
    public void RmFileInfo(BaseRequest<SaveFileInfoReqBD01> request) {
        SaveFileInfoReqBD01 req = request.getData();

        //根据传过来的文件id查询文件信息并获取文件版本标识
        FileInfo objEFileInfo = fileInfoMapper.SelectByfileRd(req.getFileRd());
        if (objEFileInfo==null){
            throw new SystemException("", "查询文件信息为空");
        }

        String strGuid = objEFileInfo.getGuid();

        //根据标识查询文件版本信息
        List<FileVerInfo> objEFileVerInfos = fileVerInfoMapper.SelectFileVerInfofileGd(strGuid);
        if (objEFileVerInfos == null){
            throw new SystemException("", "查询文件版本信息为空");
        }

        //循环删除文件版本
        for(FileVerInfo obj : objEFileVerInfos){
            if(fileVerInfoMapper.DeleteFileVerInfo(obj.getFileGd(), obj.getGuid()) <= 0){
                throw new SystemException("","删除文件信息所有的版本失败");
            }
        }

        if(fileInfoMapper.DeleteFileInfo(req.getFileRd()) <= 0){
            throw new SystemException("MG_MES4001312020001F","删除文件信息失败");
        }
    }

    /**
     * 删除文件版本信息(新)
     * @param request
     */
    @Override
    public void RmFileVerInfo(BaseRequest<SaveFileInfoReqBD05> request) {
        SaveFileInfoReqBD05 req = request.getData();

        //查询文件版本信息
        FileVerInfo fileVerInfo = fileVerInfoMapper.SelectByfileVerRd(req.getFileVerRd());
        if (fileVerInfo == null){
            throw new SystemException("","查询文件版本信息为空");
        }

        //逻辑判断是否是默认版本
        if("00".equals(fileVerInfo.getIsDef())){
            List<FileVerInfo> objEFileVerInfos = fileVerInfoMapper.SelectFileVerInfofileGd(fileVerInfo.getFileGd());
            if (objEFileVerInfos==null){
                throw new SystemException("MG_MES4001311010001F","查询文件版本信息为空");
            }

            //循环删除文件版本
            for(FileVerInfo obj:objEFileVerInfos){
                if(fileVerInfoMapper.DeleteFileVerInfo(obj.getFileGd(),obj.getGuid())<=0){
                    throw new SystemException("MG_MES4001312060005F","删除文件版本全部信息失败");
                }
            }

            FileInfo objEFileInfo = fileInfoMapper.SelectByguid(fileVerInfo.getFileGd());

            if (objEFileInfo == null){
                throw new SystemException("MG_MES4001212010001F","查询文件信息为空");
            }

            //删除文件信息
            if(fileInfoMapper.DeleteFileInfo(objEFileInfo.getRuid()) <= 0){
                throw new SystemException("MG_MES4001312020001F", "删除文件信息失败");
            }
        }else {
            if(fileVerInfoMapper.DeleteFileVerInforuid(req.getFileVerRd()) <= 0){
                throw new SystemException("MG_MES4001312060005F", "删除文件版本信息失败");
            }
        }
    }
}
