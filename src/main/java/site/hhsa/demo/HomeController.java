package site.hhsa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/faq")
    public String faqPage(){
        return "faq";
    }

    @GetMapping("/about-team")
    public String aboutUs(){
        return "about-team";
    }

    @GetMapping("org/create/event")
    public String orgCreateEvent(){
        return "organizations/create-event";
    }

    @PostMapping("org/create/event")
    public String insertNewEvent(){
        return "redirect:/organizations/dashboard";
    }

    @GetMapping("/org/dashboard")
    public String orgDashboard(){
        return "/organizations/dashboard";
    }


}
