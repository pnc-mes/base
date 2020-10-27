package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Solidfy")
public class SolidifyController {
    @RequestMapping(value = "/solidfyPG")
    public String getcompanyinfoPage() {
        return "mprocess/solidfy/solidfy";
    }
}
