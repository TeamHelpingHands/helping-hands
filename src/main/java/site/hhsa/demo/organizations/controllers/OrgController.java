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
import site.hhsa.demo.users.models.Message;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.MessageRepo;
import site.hhsa.demo.users.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrgController {
    EventRepo eventDao;
    UserRepo userDao;
    OrgRepo orgDao;
    CategoryRepo categoryDao;
    MessageRepo messageDao;

    public OrgController(EventRepo eventDao, UserRepo userDao, OrgRepo orgDao, CategoryRepo categoryDao, MessageRepo messageDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.orgDao = orgDao;
        this.categoryDao = categoryDao;
        this.messageDao = messageDao;
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
//        user.setOrganization(new Organization());
        model.addAttribute("user", user);
        return "organizations/register";
    }

    @PostMapping("/{username}/orgs/register")
    public String OrgCreate(@ModelAttribute User user,@PathVariable String username, Model model){
        user.getOrganization().setUser(userDao.findByUsername(username));
        orgDao.save(user.getOrganization());
        return "redirect:/orgs/"+ user.getOrganization().getOrgName()+"/dashboard";
    }

    @GetMapping("/orgs/messages")
    public String myOrgMessages(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> inboxMessages = messageDao.findAllByReceiver(currentUser);
        List<Message> sentMessages = messageDao.findAllBySender(currentUser);
        List<Message> delMessages = new ArrayList<>();
        int noOfMessagesInbox = inboxMessages.size();
        int noOfMessagesSent = sentMessages.size();
        int newMessages = 0;
        for (Message message : inboxMessages) {
            if (!message.isReceiverDel()) {
                if (!message.isOpened()) {
                    newMessages++;
                    message.setOpened(true);
                    messageDao.save(message);
                }
            } else {
                delMessages.add(message);
            }
        }
        inboxMessages.removeAll(delMessages);
        model.addAttribute("delMessage", new Message());
        model.addAttribute("newReply", new Message());
        model.addAttribute("noOfMessagesInbox", noOfMessagesInbox);
        model.addAttribute("noOfMessagesSent", noOfMessagesSent);
        model.addAttribute("newMessages", newMessages);
        model.addAttribute("inboxMessages", inboxMessages);
        model.addAttribute("sentMessages", sentMessages);
        model.addAttribute("currentUser", currentUser);
        return "organizations/messages";
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


