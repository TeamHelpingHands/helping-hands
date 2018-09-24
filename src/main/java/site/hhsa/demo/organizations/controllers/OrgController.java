package site.hhsa.demo.organizations.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.CategoryRepo;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.services.MassMessenger;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;

@Controller
public class OrgController {
    EventRepo eventDao;
    UserRepo userDao;
    OrgRepo orgDao;
    CategoryRepo categoryDao;

    public OrgController(OrgRepo orgDao, CategoryRepo categoryDao, UserRepo userDao, EventRepo eventDao) {
        this.orgDao = orgDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/orgs")
    public String OrgHome(Model model) {
        model.addAttribute("orgs", orgDao.findAll());
        return "organizations/index";
    }

    @GetMapping("/orgs/{org_name}")
    public String OrgShow(@PathVariable String org_name, Model model){
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        User user = org.getUser();
        model.addAttribute("user", user);
        return "organizations/show";
    }

    @GetMapping("/orgs/{org_name}/dashboard")
    public String OrgDashboard(@PathVariable String org_name, Model model){
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        User user = org.getUser();
        model.addAttribute("user", user);
        return "organizations/dashboard";
    }

    @GetMapping("/{username}/orgs/register")
    public String OrgNew(@PathVariable String username, Model model){
        User user = userDao.findByUsername(username);
        model.addAttribute("user", user);
        return "organizations/register";
    }

    @PostMapping("/{username}/orgs/register")
    public String OrgCreate(@ModelAttribute User user,@PathVariable String username, Model model){
        user.getOrganization().setUser(userDao.findByUsername(username));
        orgDao.save(user.getOrganization());
        return "redirect:/login";
    }

    @GetMapping("orgs/{org_name}/events/create")
    public String orgNewEvent(@PathVariable String org_name, Model model){
        Organization myOrg = orgDao.findOrganizationByOrgName(org_name);
        model.addAttribute("myOrg", myOrg);
        model.addAttribute("newEvent", new Event());
        return "organizations/create-event";
    }

    @PostMapping("orgs/{org_name}/events/create")
    public String orgInsertEvent(@PathVariable String org_name, @ModelAttribute Event newEvent){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization org = currentUser.getOrganization();
        newEvent.setOrg(org);
        eventDao.save(newEvent);
//        new MassMessenger(org.getFollowers(), "One of your liked liked organizations has posted an event! https://www.hhsa.com/"+org_name+"/events");
        return "redirect:/events";
    }

    @GetMapping("orgs/{org_name}/events")
    public String orgEvents(@PathVariable String org_name, Model model){
        Organization myOrg = orgDao.findOrganizationByOrgName(org_name);
        model.addAttribute("myOrg", myOrg);
        return "events/orgs-index";
    }
    @GetMapping("/orgs/{org_name}/event/{id}")
    public String orgEvents(@PathVariable String org_name, @PathVariable long id, Model model){
        model.addAttribute("event", eventDao.findOne(id));
        return "events/show-event";
    }

    @PostMapping("{username}/orgs/{org_name}/follow")
    public String orgAddFollower(@PathVariable String username, @PathVariable String org_name){
        if(userDao.findByUsername(username).getVolunteer().getFavorites().contains(orgDao.findOrganizationByOrgName(org_name))){
            userDao.findByUsername(username).getVolunteer().getFavorites().remove(orgDao.findOrganizationByOrgName(org_name));
        }else{
            userDao.findByUsername(username).getVolunteer().getFavorites().add(orgDao.findOrganizationByOrgName(org_name));
        }
        orgDao.save(orgDao.findOrganizationByOrgName(org_name));
        return "redirect:/orgs/{org_name}";
    }

// ======== Listener for org to create event and insert into database ===== \\
//    @PostMapping("orgs/{org_name/events/create")
//    public String orgInsertEvent(@PathVariable String org_name, @ModelAttribute){
//
//        return "redirect:/organizations/dashboard";
//    }

}


