package site.hhsa.demo.volunteers.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        List<Message> messages = messageDao.findAllByReceiverOrderByTimeSentDesc(currentUser);
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
        List<Message> inboxMessages = messageDao.findAllByReceiverOrderByTimeSentDesc(currentUser);
        List<Message> sentMessages = messageDao.findAllBySenderOrderByTimeSentDesc(currentUser);
        List<Message> delInboxMessages = new ArrayList<>();
        List<Message> delSentMessages = new ArrayList<>();
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
                delInboxMessages.add(message);
            }

            if (message.isSenderDel()) {
                delSentMessages.add(message);
            }
        }
        inboxMessages.removeAll(delInboxMessages);
        sentMessages.removeAll(delSentMessages);
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
    public String deleteMessage(@RequestParam String id, @RequestParam String which, RedirectAttributes redir) {
        Message message = messageDao.findOne(Long.parseLong(id));
        if (which.equals("sender")) {
            message.setSenderDel(true);
        } else {
            message.setReceiverDel(true);
        }
        messageDao.save(message);
        redir.addFlashAttribute("deleted", "Message deleted.");
        return "redirect:/vols/messages";
    }

    @PostMapping("/vols/message/reply")
    public String messageReply(
            @ModelAttribute Message newReply,
            @RequestParam String receiverId,
            @RequestParam String subject,
            @RequestParam String body,
            RedirectAttributes redir) {
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
        redir.addFlashAttribute("messageSent", "Message sent.");
        return "redirect:/vols/messages";
    }

    @GetMapping("vols/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());
        User userProfile = userDao.findByUsername(username);
        List<Event> events = eventDao.findAllByVolunteersContains(userProfile.getVolunteer());
        List<FeedbackFromOrganization> feedbacks = feedbackFromOrgDao.findAllByVolunteer(currentUser.getVolunteer());

        int noOfDidAttend = 0;

        for (FeedbackFromOrganization feedback: feedbacks) {
            if (feedback.isDidAttend()) {
                noOfDidAttend++;
            }
        }

        model.addAttribute("message", new Message());
        model.addAttribute("user", userProfile);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("noOfDidAttend", noOfDidAttend);
        return "volunteers/profile";
    }

    @PostMapping("vols/{username}")
    public String sendMessageVol(@PathVariable String username, @ModelAttribute Message message, RedirectAttributes redir) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(currentUser.getUsername());
        message.setSender(user);
        message.setReceiver(userDao.findByUsername(username));
        messageDao.save(message);
        redir.addFlashAttribute("messageSent", 1);
        return "redirect:/vols/"+username;
    }
}
