package site.hhsa.demo.organizations.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrgController {
    EventRepo eventDao;
    UserRepo userDao;
    OrgRepo orgDao;
    CategoryRepo categoryDao;
    MessageRepo messageDao;

    @Value("${accountSID}")
    private String ACCOUNT_SID;

    @Value("${authTOKEN}")
    private String AUTH_TOKEN;

    @Value("${phnNUM}")
    private String Phn_num;

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
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User currentUser = userDao.findByUsername(user.getUsername());
            model.addAttribute("currentUser", currentUser);
            if(currentUser.getFavorites().contains(org)){
                System.out.println("IS THIS A FAVORITE ORG? " + currentUser.getFavorites().contains(org));
                model.addAttribute("follower", true);
            }else{
                model.addAttribute("follower",false);
            }
        }
        model.addAttribute("message", new Message());
        model.addAttribute("org", org);
        return "organizations/show";
    }


    @GetMapping("/orgs/{org_name}/favorites")
    public String OrgShowUser(@PathVariable String org_name, Model model){
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());
        if(user.getFavorites().contains(org)){
            model.addAttribute("follower", true);
        }else{
            model.addAttribute("follower",false);
        }
        orgDao.save(orgDao.findOrganizationByOrgName(org_name));
        model.addAttribute("user", user);
        model.addAttribute("org", org);
        return "organizations/show";
    }

    @PostMapping("orgs/{org_name}/favorites")
    public String sendMessageVol(@PathVariable String org_name, @ModelAttribute Message message, Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user1.getUsername());
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        User user = userDao.findByUsername(org.getUser().getUsername());
        message.setSender(currentUser);
        message.setReceiver(user);
        messageDao.save(message);
        model.addAttribute("messageSent", "Your message has been sent.");
        return "redirect:/orgs/"+org_name;
    }

    @GetMapping("/orgs/dashboard")
    public String OrgDashboard(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        List<Message> messages = messageDao.findAllByReceiver(currentUser);
        int newMessages = messages.size();
        model.addAttribute("newMessages", newMessages);
        model.addAttribute("currentUser", currentUser);
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
//        user.getOrganization().setOrgName(user.getOrganization().getOrgName().replace(" ","-"));
        orgDao.save(user.getOrganization());
        return "redirect:/login";
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

    @PostMapping("orgs/messages")
    public String deleteMessage(@RequestParam String id, Model model) {
        Message message = messageDao.findOne(Long.parseLong(id));
        message.setReceiverDel(true);
        messageDao.save(message);
        model.addAttribute("deleted", "Message deleted.");
        return "redirect:/orgs/messages";
    }

    @PostMapping("/orgs/message/reply")
    public String messageReply(
            @ModelAttribute Message newReply,
            @RequestParam String receiverId,
            @RequestParam String subject,
            @RequestParam String body,
            Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        Message message = new Message();
        message.setSubject(subject);
        message.setBody(body);
        message.setReceiver(userDao.findOne(Long.parseLong(receiverId)));
        message.setSender(currentUser);
        message.setOpened(false);
        message.setReceiverDel(false);
        messageDao.save(message);
        model.addAttribute("messageSent", "Message sent.");
        return "redirect:/orgs/messages";
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        newEvent.setOrg(org);
        eventDao.save(newEvent);
        new MassMessenger(org.getFollowers(), "One of your liked liked organizations has posted an event! https://www.hhsa.com/"+org_name+"/events", ACCOUNT_SID,AUTH_TOKEN,Phn_num);
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

    @PostMapping("/orgs/{org_name}/event/{id}")
    public String insertVolToEvent(@PathVariable String org_name, @PathVariable long id, Model model){
        Event event = eventDao.findOne(id);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());

        List<Volunteer> vol = event.getVolunteers();
        vol.add(user.getVolunteer());
        eventDao.save(event);
        return"redirect:/orgs/"+ org_name+"/event/"+id;
    }

    @PostMapping("/orgs/{org_name}/follow")
    public String orgAddFollower(@PathVariable String org_name){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());
        if(user.getFavorites().contains(orgDao.findOrganizationByOrgName(org_name))){
            user.getFavorites().remove(orgDao.findOrganizationByOrgName(org_name));
        }else{
            user.getFavorites().add(orgDao.findOrganizationByOrgName(org_name));
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


