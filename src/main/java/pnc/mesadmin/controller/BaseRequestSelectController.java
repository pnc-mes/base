package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllDevInfo.DevSaveRequest;
import pnc.mesadmin.dto.GetAllDevInfo.GetDevListsRequest;
import pnc.mesadmin.service.BaseRequestSelectService;
import pnc.mesadmin.service.DeviceIService;
import pnc.mesadmin.utils.BaseResponse;

import javax.annotation.Resource;

/**
 * @Description 通用下拉框接口查询
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
@Controller
@Scope("prototype")
@RequestMapping("/BaseRequestSelect")
public class BaseRequestSelectController {

    @Resource
    private BaseRequestSelectService service;

    /**
     * @Description 所有物料查询
     */
    @RequestMapping(value = "/GetAllMaterial", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllMaterial() {
        return service.GetAllMaterial();
    }

    /**
     * @Description 所有工序查询
     */
    @RequestMapping(value = "/GetAllSpec", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllSpec() {
        return service.GetAllSpec();
    }

    /**
     * @Description 所有工单查询
     */
    @RequestMapping(value = "/GetAllWo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllWo() {
        return service.GetAllWo();
    }

    /**
     * @Description 所有设备查询
     */
    @RequestMapping(value = "/GetAllDev", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllDev() {
        return service.GetAllDev();
    }

    /**
     * @Description 所有文件查询
     */
    @RequestMapping(value = "/GetAllFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllFile() {
        return service.GetAllFile();
    }

    /**
     * @Description 所有设备查询
     */
    @RequestMapping(value = "/GetAllCompany", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCompany() {
        return service.GetAllCompany();
    }

}
