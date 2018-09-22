package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.users.models.Message;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.MessageRepo;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

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
        User user = userDao.findByUsername(username);
        List<Message> messages = messageDao.findAllByReceiver(user);
        int newMessagesCount = 0;
        for (Message message : messages) {
            if (!message.isOpened()) {
                newMessagesCount++;
                message.setOpened(true);
                messageDao.save(message);
            }
        }
        model.addAttribute("newMessageCount", newMessagesCount);
        model.addAttribute("user", user);
        model.addAttribute("events", eventDao.findAll());
        return "volunteers/dashboard";
    }

    @GetMapping("vols/{username}/messages")
    public String myMessages(@PathVariable String username, Model model) {
        User currentUser = userDao.findByUsername(username);
        List<Message> messages = messageDao.findAllByReceiver(currentUser);
        int noOfMessages = messages.size();
        model.addAttribute("noOfMessages", noOfMessages);
        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", currentUser);
        return "volunteers/messages";
    }

    @GetMapping("vols/{username}/profile")
    public String showProfile(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        model.addAttribute("message", new Message());
        model.addAttribute("user", user);
        return "volunteers/profile";
    }

    @PostMapping("vols/{username}/profile")
    public String sendMessageVol(@PathVariable String username, @ModelAttribute Message message) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findByUsername(username);
//        message.setSender(currentUser);
//        message.setReceiver(user);
//        messageDao.save(message);
        return "redirect:/vols/"+username+"/profile";
    }
}
