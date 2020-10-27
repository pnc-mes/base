package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Inspect")
public class CompressInspectController {
    @RequestMapping(value = "/inspectPG")
    public String getcompanyinfoPage() {
        return "mprocess/inspect/inspect";
    }
}
