package site.hhsa.demo.users.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;

@Controller
public class UserController {

    UserRepo userDao;

    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/home")
    public String test(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        if (currentUser.isOrg()) {
            return "redirect:/orgs/"+currentUser.getOrganization().getOrgName()+"/dashboard";
        } else {
            return "redirect:/vols/dash";
        }
    }


}
