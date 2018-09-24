package site.hhsa.demo.users.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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

//    @GetMapping("/{org_name}/create/event")
//    private String userCreateEvent(@PathVariable String org_name, Model viewModel, @ModelAttribute UserRepo userDao){
//        viewModel.addAttribute("org_name", userDao);
//
//
//        return "organizations/dashboard";
//    }

    @GetMapping("/home")
    @ResponseBody
    public String goToDash(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
//        if(currentUser.isOrg()){
//            return "/orgs/"+currentUser.getOrganization().getOrgName()+"/dashboard";
//        }
//        return "/vols/"+currentUser.getUsername()+"/dashboard";
        return " "+currentUser.isOrg();
    }


}
