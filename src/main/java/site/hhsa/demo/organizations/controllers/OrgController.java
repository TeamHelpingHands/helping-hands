package site.hhsa.demo.organizations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.OrgRepo;

import java.util.Date;

@Controller
public class OrgController {

    OrgRepo orgDao;

    public OrgController(OrgRepo orgDao) {
        this.orgDao = orgDao;
    }

    @GetMapping("/organizations/home")
    public String OrgHome() {
        return "organizations/index";
    }

    @GetMapping("/organizations/{org_name}")
    public String OrgShow(@PathVariable String org_name, Model model){
        model.addAttribute("myOrg", orgDao.findOrganizationByOrgName(org_name));
        return "organizations/show";
    }

    @GetMapping("/organizations/{org_name}/dashboard")
    public String OrgDashboard(@PathVariable String org_name, Model model){
        model.addAttribute("myOrg", orgDao.findOrganizationByOrgName(org_name));
        return "organizations/dashboard";
    }

    @GetMapping("/organizations/register")
    public String OrgNew(Model model){
        model.addAttribute("org", new Organization());
        return "organizations/register";
    }

    @PostMapping("/organizations/register")
    public String OrgCreate(@ModelAttribute Organization org, Model model){
        org.setDateCreated(new Date().toString());
        orgDao.save(org);
        model.addAttribute("myOrg", org);
        return "redirect:/organizations/"+ org.getOrgName()+"/dashboard";
    }

    @GetMapping("/organizations/login")
    public String OrgLogin(){
        return "organizations/login";
    }

}


