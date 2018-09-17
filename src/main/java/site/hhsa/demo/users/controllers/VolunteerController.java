package site.hhsa.demo.users.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import site.hhsa.demo.users.repositories.VolunteerRepo;

@Controller
public class VolunteerController {

    VolunteerRepo volunteerRepo;

    public VolunteerController(VolunteerRepo volunteerRepo) {
        this.volunteerRepo = volunteerRepo;
    }

    @GetMapping("/volunteers")
    public String testVols() {
        return "volunteers/index";
    }
}
