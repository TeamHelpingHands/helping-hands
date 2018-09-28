package site.hhsa.demo.volunteers.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.organizations.models.Event;
import site.hhsa.demo.organizations.models.FeedbackFromOrganization;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.users.models.Message;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.MessageRepo;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.FeedbackFromOrgRepo;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VolunteerController {

    UserRepo userDao;
    VolunteerRepo volDao;
    EventRepo eventDao;
    MessageRepo messageDao;
    FeedbackFromOrgRepo feedbackFromOrgDao;

    public VolunteerController(UserRepo userDao, VolunteerRepo volDao, EventRepo eventDao, MessageRepo messageDao, FeedbackFromOrgRepo feedbackFromOrgDao) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.eventDao = eventDao;
        this.messageDao = messageDao;
        this.feedbackFromOrgDao = feedbackFromOrgDao;
    }

    @GetMapping("/vols/{username}/register")
    public String volRegisterGet(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        model.addAttribute("user", user);
        return "volunteers/register";
    }

    @PostMapping("/vols/{username}/register")
    public String volRegisterPost(@PathVariable String username, @ModelAttribute User user) {
        user.getVolunteer().setUser(userDao.findByUsername(username));
        volDao.save(user.getVolunteer());
        return "redirect:/login";
    }

    @GetMapping("/vols/{username}/events")
    public String volsEventIndex(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        List<Event> events = eventDao.findAllByVolunteersContains(currentUser.getVolunteer());

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("events", events);

        return "events/vols-index";
    }

    @GetMapping("/vols/dash")
    public String volProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        List<Message> messages = messageDao.findAllByReceiver(currentUser);
        List<FeedbackFromOrganization> feedbacks = feedbackFromOrgDao.findAllByVolunteer(currentUser.getVolunteer());
        List<Event> events = eventDao.findAllByVolunteersContains(currentUser.getVolunteer());
        int noOfEventsEnrolled = events.size();
        int noOfDidAttend = 0;

        for (FeedbackFromOrganization feedback: feedbacks) {
            if (feedback.isDidAttend()) {
                noOfDidAttend++;
            }
        }

        int newMessagesCount = 0;
        for (Message message : messages) {
            if (!message.isOpened()) {
                newMessagesCount++;
            }
        }
        model.addAttribute("noEventsEnrolled", noOfEventsEnrolled);
        model.addAttribute("noOfDidAttend", noOfDidAttend);
        model.addAttribute("newMessageCount", newMessagesCount);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("events", eventDao.findAll());
        return "volunteers/dashboard";
    }

    @GetMapping("/vols/messages")
    public String myMessages(Model model) {
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
        return "volunteers/messages";
    }

    @PostMapping("vols/messages")
    public String deleteMessage(@RequestParam String id, Model model) {
        Message message = messageDao.findOne(Long.parseLong(id));
        message.setReceiverDel(true);
        messageDao.save(message);
        model.addAttribute("deleted", "Message deleted.");
        return "redirect:/vols/messages";
    }

    @PostMapping("/vols/messages/reply")
    public String messageReply(
            @ModelAttribute Message newReply,
            @RequestParam String receiverId,
            @RequestParam String senderId,
            @RequestParam String subject,
            @RequestParam String body,
            Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = new Message();
        message.setSubject(subject);
        message.setBody(body);
        message.setReceiver(userDao.findOne(Long.parseLong(receiverId)));
        message.setSender(currentUser);
        message.setOpened(false);
        message.setReceiverDel(false);
        messageDao.save(message);
        model.addAttribute("messageSent", "Message sent.");
        return "redirect:/vols/messages";
    }

    @GetMapping("vols/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername("tenglishjr");
        User user = userDao.findByUsername(username);
        model.addAttribute("message", new Message());
        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "volunteers/profile";
    }

    @PostMapping("vols/{username}")
    public String sendMessageVol(@PathVariable String username, @ModelAttribute Message message, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(username);
        message.setSender(currentUser);
        message.setReceiver(user);
        messageDao.save(message);
        model.addAttribute("messageSent", "Your message has been sent.");
        return "redirect:/vols/"+username;
    }
}
