package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllDevInfo.DevSaveRequest;
import pnc.mesadmin.dto.GetAllDevInfo.GetDevListsRequest;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.DeviceIService;
import pnc.mesadmin.utils.BaseResponse;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
@Controller
@Scope("prototype")
@RequestMapping("/Device")
public class DeviceController {

    @Resource
    private DeviceIService deviceIService;

    /**
     * @Description 分页查询列表
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetDevList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetDevList(GetDevListsRequest request) {
        return deviceIService.GetDevList(request);
    }

    /**
     * @Description 获取设备详情
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetDevDetails", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetDevDetails(DevSaveRequest request) {
        return deviceIService.GetDevDetails(request);
    }

    /**
     * @Description 新增设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/AddDevInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse AddDevInfo(@RequestBody DevSaveRequest request) {
        try {
            return deviceIService.AddDevInfo(request);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * @Description 删除设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/RmDevInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse RmDevInfo(DevSaveRequest request) {
        try {
            return deviceIService.RmDevInfo(request);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

    }

    /**
     * @Description 更新设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/ModSaveDevInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse ModSaveDevInfo(@RequestBody DevSaveRequest request) {
        try {
            return deviceIService.ModSaveDevInfo(request);
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
}
