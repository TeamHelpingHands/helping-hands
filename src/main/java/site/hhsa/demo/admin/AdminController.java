package site.hhsa.demo.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.users.repositories.UserRepo;

@Controller
public class AdminController {

    UserRepo userDao;
    OrgRepo orgDao;

    public AdminController(UserRepo userDao, OrgRepo orgDao) {
        this.userDao = userDao;
        this.orgDao = orgDao;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model){
        model.addAttribute("org", orgDao.findAll());
        model.addAttribute("admin", userDao.findByUsername("admin"));
        return"/admin/dashboard";
    }


}
