package site.hhsa.demo.organizations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.hhsa.demo.organizations.repositories.EventRepo;

@Controller
public class EventController {

    EventRepo eventDao;

    public EventController(EventRepo eventDao) { this.eventDao = eventDao; }

    @GetMapping("/events")
    private String eventsIndex(Model model){
        model.addAttribute("events", eventDao.findAll());
        return "events/index";
    }

}