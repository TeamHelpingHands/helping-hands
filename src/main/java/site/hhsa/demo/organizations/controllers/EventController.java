package site.hhsa.demo.organizations.controllers;

import com.google.common.collect.Lists;
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
        model.addAttribute("events", Lists.reverse(eventDao.findAll()));
        return "events/index";
    }

    @GetMapping("/events/{event_id}")
    private String eventsIndex(@PathVariable long event_id, Model model){
        model.addAttribute("event", eventDao.findOne(event_id));
        return "events/index";
    }

}
