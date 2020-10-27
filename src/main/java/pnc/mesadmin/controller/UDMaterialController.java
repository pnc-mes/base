package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pnc.mesadmin.service.UDMaterialService;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称 装卸物料Controller
 * 创建时间：2019-06-15
 * 修改人：yin.yang
 * 修改时间：
 */
@Controller
@RequestMapping("/UDMaterialFPPlus")
public class UDMaterialController {
    @Resource
    UDMaterialService service;

}
