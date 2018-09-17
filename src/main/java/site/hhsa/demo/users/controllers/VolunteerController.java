package site.hhsa.demo.users.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.hhsa.demo.users.models.Volunteer;
import site.hhsa.demo.users.repositories.VolunteerRepo;

@Controller
public class VolunteerController {

    VolunteerRepo volunteerRepo;

    public VolunteerController(VolunteerRepo volunteerRepo) {
        this.volunteerRepo = volunteerRepo;
    }

    @GetMapping("/vol/register")
    public String registerTest() {
        return "volunteers/register";
    }

    @GetMapping("/vol/profile")
    public String volProfile(Model viewModel) {

        Volunteer vol = volunteerRepo.findOne(1L);
        viewModel.addAttribute("vol", vol);

        return "volunteers/profile";
    }

    @GetMapping("/vol/login")
    public String volLogin(){
        return "volunteers/login";
    }
}
