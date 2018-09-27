package site.hhsa.demo.organizations.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.FeedbackFromOrganization;
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
import site.hhsa.demo.volunteers.repositories.FeedbackFromOrgRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrgController {
    FeedbackFromOrgRepo feedbackDao;
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

    public OrgController(EventRepo eventDao, UserRepo userDao, OrgRepo orgDao, CategoryRepo categoryDao, MessageRepo messageDao, FeedbackFromOrgRepo feedbackDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.orgDao = orgDao;
        this.categoryDao = categoryDao;
        this.messageDao = messageDao;
        this.feedbackDao = feedbackDao;
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
        model.addAttribute("message", new Message());
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
        Event event = eventDao.findOne(id);
        List<Volunteer> vols = event.getVolunteers();
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userDao.findByUsername(currentUser.getUsername());
            model.addAttribute("user", user);
            if (vols.contains(user.getVolunteer())) {
                String alert = "Test123";
                model.addAttribute("alert", alert);
            }
        }
        String oldDateTime = eventDao.findOne(id).getDateTime();
        String newDate = oldDateTime.substring(0, 10);
        String newTime = oldDateTime.substring(11);
        model.addAttribute("vols", vols);
        model.addAttribute("newTime", newTime);
        model.addAttribute("newDate", newDate);
        model.addAttribute("event", eventDao.findOne(id));
        return "events/show-event";
    }

    @PostMapping("/orgs/{org_name}/event/{id}")
    public String insertVolToEvent(@PathVariable String org_name, @PathVariable long id, Model model){
        Event event = eventDao.findOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());
        List<Volunteer> vols = event.getVolunteers();
        if (!vols.contains(user.getVolunteer())) {
            vols.add(user.getVolunteer());
        }
        eventDao.save(event);
        model.addAttribute("user", user);
        model.addAttribute("vols", vols);
        return"redirect:/orgs/"+ org_name+"/event/"+id;
    }


    @GetMapping("/orgs/{org_name}/follow")
    public String orgAddFollower(@PathVariable String org_name){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());
        Organization org = orgDao.findOrganizationByOrgName(org_name);
        List<User> followers = org.getFollowers();

        if (followers.contains(user)) {
            followers.remove(user);
        } else {
            followers.add(user);
            org.setFollowers(followers);
        }
        orgDao.save(org);
        return "redirect:/orgs/"+org_name+"/favorites";
    }

    @GetMapping("/orgs/{org_name}/events/{event_id}/dash")
    public String eventDash(@PathVariable long event_id, Model model){
        Event event = eventDao.findOne(event_id);
        List<FeedbackFromOrganization> feedbacks = feedbackDao.findFeedbackFromOrganizationByEvent(event);
        List<Volunteer> eventVolunteers = new ArrayList<>();
        for(FeedbackFromOrganization feedback : feedbacks ){
            eventVolunteers.add(feedback.getVolunteer());
        }
        model.addAttribute("event", event);
        model.addAttribute("attendees", eventVolunteers);

        return "organizations/event-dash";
    }

    @PostMapping("/orgs/{org_name}/events/{event_id}/dash")
    public String eventDashUpdate(@PathVariable long event_id,@RequestParam("volunteer") long volunteer, @RequestParam("feedback") String feedback, Model model){
        Event event = eventDao.findOne(event_id);
        FeedbackFromOrganization orgFeedback = new FeedbackFromOrganization();
        orgFeedback.setDidAttend(true);
        orgFeedback.setFeedback(feedback);
        orgFeedback.setEvent(event);
        orgFeedback.setVolunteer(userDao.findOne(volunteer).getVolunteer());
        orgFeedback.setOrg(event.getOrganization());
        feedbackDao.save(orgFeedback);
        model.addAttribute("event", event);
        return "redirect:/orgs/"+ event.getOrganization() + "/events/" + event.getId() + "/dash";
    }

// ======== Listener for org to create event and insert into database ===== \\
//    @PostMapping("orgs/{org_name/events/create")
//    public String orgInsertEvent(@PathVariable String org_name, @ModelAttribute){
//
//        return "redirect:/organizations/dashboard";
//    }

}


