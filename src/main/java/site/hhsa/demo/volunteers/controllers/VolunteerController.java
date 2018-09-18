package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.models.VolunteerDetails;
import site.hhsa.demo.volunteers.repositories.VolunteerDetailsRepo;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.Date;

@Controller
public class VolunteerController {

    VolunteerRepo volunteerRepo;
    VolunteerDetailsRepo volunteerDetailsDao;

    public VolunteerController(VolunteerRepo volunteerRepo, VolunteerDetailsRepo volunteerDetailsDao) {
        this.volunteerRepo = volunteerRepo;
        this.volunteerDetailsDao = volunteerDetailsDao;
    }

    @GetMapping("/vol/register")
    public String register(Model viewModel) {
        viewModel.addAttribute("volunteer", new Volunteer());
        return "volunteers/register";
    }

    @PostMapping("/vol/register")
    public String registerPost(@ModelAttribute Volunteer vol) {
        Date date = new Date();
        vol.setDateCreated(date.toString());
        vol.setAdmin(false);
        vol.setSuspended(false);
        volunteerRepo.save(vol);
        return "redirect:/vol/profile/" + vol.getUsername();
    }

    @GetMapping("/vol/profile/{username}")
    public String volProfile(@PathVariable String username, Model viewModel) {
        Volunteer vol = volunteerRepo.findByUsername(username);
        viewModel.addAttribute("vol", vol);
        return "volunteers/profile";
    }

    @GetMapping("/vol/login")
    public String volLogin(){
        return "volunteers/login";
    }
}
