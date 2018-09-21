package site.hhsa.demo.organizations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.CategoryRepo;
import site.hhsa.demo.organizations.repositories.OrgRepo;

import java.util.Date;

@Controller
public class OrgController {

    OrgRepo orgDao;
    CategoryRepo categoryDao;

    public OrgController(OrgRepo orgDao, CategoryRepo categoryDao) {
        this.orgDao = orgDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/organizations")
    public String OrgHome(Model model) {
        model.addAttribute("orgs", orgDao.findAll());
        return "organizations/index";
    }

    @GetMapping("/organizations/{org_name}")
    public String OrgShow(@PathVariable String org_name, Model model){
        model.addAttribute("myOrg", orgDao.findOrganizationByOrgName(org_name));
        return "organizations/show";
    }

    @GetMapping("/orgs/{org_name}/dashboard")
    public String OrgDashboard(@PathVariable String org_name, Model model){
        model.addAttribute("org", orgDao.findOrganizationByOrgName(org_name));
        return "organizations/dashboard";
    }

    @GetMapping("/organizations/register")
    public String OrgNew(Model model){
        model.addAttribute("org", new Organization());
        return "organizations/register";
    }

    @PostMapping("/organizations/register")
    public String OrgCreate(@ModelAttribute Organization org, Model model){
        orgDao.save(org);
        model.addAttribute("myOrg", org);
        return "redirect:/organizations/"+ org.getOrgName()+"/dashboard";
    }

    @GetMapping("/organizations/login")
    public String OrgLogin(){
        return "organizations/login";
    }

    @GetMapping("organizations/{org_name}/events/new")
    public String orgNewEvent(@PathVariable String org_name, Model model){
        Organization myOrg = orgDao.findOrganizationByOrgName(org_name);
        model.addAttribute("myOrg", myOrg);
        model.addAttribute("newEvent", new Event());
        return "organizations/new_event";
    }

    @GetMapping("organizations/{org_name}/events")
    public String orgEvents(@PathVariable String org_name, Model model){
        Organization myOrg = orgDao.findOrganizationByOrgName(org_name);
        model.addAttribute("myOrg", myOrg);
        return "events/show";
    }

}


