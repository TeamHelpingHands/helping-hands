package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.users.models.Message;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.MessageRepo;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VolunteerController {

    UserRepo userDao;
    VolunteerRepo volDao;
    EventRepo eventDao;
    MessageRepo messageDao;

    public VolunteerController(UserRepo userDao, VolunteerRepo volDao, EventRepo eventDao, MessageRepo messageDao) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.eventDao = eventDao;
        this.messageDao = messageDao;
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
        return "redirect:/vols/"+user.getUsername()+"/dash";
    }

    @GetMapping("/vols/{username}/dash")
    public String volProfile(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername("tenglishjr");
        List<Message> messages = messageDao.findAllByReceiver(currentUser);
        int newMessagesCount = 0;
        for (Message message : messages) {
            if (!message.isOpened()) {
                newMessagesCount++;

            }
        }
        model.addAttribute("newMessageCount", newMessagesCount);
        model.addAttribute("user", currentUser);
        model.addAttribute("events", eventDao.findAll());
        return "volunteers/dashboard";
    }

    @GetMapping("/vols/{username}/messages")
    public String myMessages(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername(username);
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

    @PostMapping("vols/{username}/messages")
    public String deleteMessage(@PathVariable String username, @RequestParam String id, Model model) {
        System.out.println("THIS IS THE ID YOURE LOOKING FOR:  " + id);
        Message message = messageDao.findOne(Long.parseLong(id));
        System.out.println("LOOOOOOK HEREREERERERER: 1");
        message.setReceiverDel(true);
        System.out.println("LOOOOOOOK HEREREERERERER: 2");
        messageDao.save(message);
        System.out.println("LOOOOOOK HERERREREERRE: 3");
        model.addAttribute("deleted", "Message deleted.");
        return "redirect:/vols/"+username+"/messages";
    }

    @PostMapping("/vols/message/reply")
    public String messageReply(
            @ModelAttribute Message newReply,
            @RequestParam String receiverId,
            @RequestParam String senderId,
            @RequestParam String subject,
            @RequestParam String body,
            Model model) {
        User currentUser = userDao.findByUsername("tenglishjr");
        Message message = new Message();
        message.setSubject(subject);
        message.setBody(body);
        message.setReceiver(userDao.findOne(Long.parseLong(receiverId)));
        message.setSender(userDao.findOne(Long.parseLong(senderId)));
        message.setOpened(false);
        message.setReceiverDel(false);
        messageDao.save(message);
        model.addAttribute("messageSent", "Message sent.");
        return "redirect:/vols/"+currentUser.getUsername()+"/messages";
    }

    @GetMapping("vols/{username}/profile")
    public String showProfile(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername("tenglishjr");
        User user = userDao.findByUsername(username);
        model.addAttribute("message", new Message());
        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "volunteers/profile";
    }

    @PostMapping("vols/{username}/profile")
    public String sendMessageVol(@PathVariable String username, @ModelAttribute Message message) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername("tenglishjr");
        User user = userDao.findByUsername(username);
        message.setSender(currentUser);
        message.setReceiver(user);
        messageDao.save(message);
        return "redirect:/vols/"+username+"/profile";
    }
}
