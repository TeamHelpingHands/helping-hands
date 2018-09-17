package site.hhsa.demo.organizations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrgController {

    @GetMapping("/organization/home")
    public String test() {
        return "index";
    }

}


